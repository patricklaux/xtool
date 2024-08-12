package com.igeeksky.xtool.core.json;

import com.igeeksky.xtool.core.collection.Maps;
import com.igeeksky.xtool.core.function.tuple.Pair;
import com.igeeksky.xtool.core.function.tuple.Pairs;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象 转 JSON字符串
 * <p>
 * 待优化：
 * 1. 实现字符数组缓冲区池
 * 2. 循环引用问题
 *
 * @author patrick
 * @since 1.0.10 2024/6/2
 */
public class SimpleJSON {

    private static final Map<Class<?>, JavaType> TYPE_MAP = new ConcurrentHashMap<>(128);

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return "null";
        }

        // 1. 处理简单对象（基本数据类型或其它一些数值类型）
        if (obj instanceof CharSequence || obj instanceof Enum<?> || obj instanceof Character || obj instanceof Boolean || obj instanceof Number) {
            return obj.toString();
        }

        // 2. 处理复杂对象
        StringBuilder builder = new StringBuilder(512);
        process(obj, builder);
        return builder.toString();
    }

    /**
     * 处理复杂类型对象
     *
     * @param obj 复杂对象
     */
    private static void process(Object obj, StringBuilder builder) {
        if (obj == null) {
            return;
        }

        // 处理 Map 类型
        if (obj instanceof Map<?, ?> map) {
            processMap(map, builder);
            return;
        }

        // 处理 Iterable 类型
        if (obj instanceof Iterable<?> it) {
            processIterable(it, builder);
            return;
        }

        // 处理基本类型数组
        Class<?> clazz = obj.getClass();
        if (clazz.isArray() && clazz.getComponentType().isPrimitive()) {
            processPrimitiveArray(obj, builder);
            return;
        }

        // 处理复杂对象数组
        if (obj instanceof Object[] objs) {
            processArray(objs, builder);
            return;
        }

        try {
            // 处理自定义对象类型
            processBean(obj, builder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Map 类型 转换为 String
     */
    private static void processMap(Map<?, ?> map, StringBuilder builder) {
        if (Maps.isEmpty(map)) {
            builder.append("{}");
            return;
        }

        builder.append("{");

        int i = 0, max = map.size() - 1;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            builder.append("\"");
            processKey(entry.getKey(), builder);
            builder.append("\":");
            processValue(entry.getValue(), builder);
            if (i < max) {
                builder.append(",");
            }
            i++;
        }

        builder.append("}");
    }

    /**
     * Iterable 类型 转换为 String
     */
    private static void processIterable(Iterable<?> iterable, StringBuilder builder) {
        Iterator<?> it = iterable.iterator();
        if (!it.hasNext()) {
            builder.append("[]");
            return;
        }

        builder.append("[");
        for (; ; ) {
            Object object = it.next();
            processValue(object, builder);
            if (it.hasNext()) {
                builder.append(',');
            } else {
                builder.append(']');
                return;
            }
        }
    }

    /**
     * 对象数组 转换为 String
     */
    private static void processArray(Object[] objects, StringBuilder builder) {
        int length = objects.length;
        if (length == 0) {
            builder.append("[]");
            return;
        }

        builder.append("[");
        for (int i = 0, max = length - 1; i < length; i++) {
            Object object = objects[i];
            if (object != null) {
                processValue(object, builder);
            }
            if (i < max) {
                builder.append(",");
            }
        }
        builder.append("]");
    }

    /**
     * 基本类型数组 转换为 String
     */
    private static void processPrimitiveArray(Object obj, StringBuilder builder) {
        int length = Array.getLength(obj);
        if (length == 0) {
            builder.append("[]");
            return;
        }

        Object base = Array.get(obj, 0);
        if (base instanceof Character) {
            builder.append((char[]) obj);
            return;
        }

        builder.append("[");
        for (int i = 0, max = length - 1; i < length; i++) {
            Object o = Array.get(obj, i);
            if (o != null) {
                builder.append(o);
            }
            if (i < max) {
                builder.append(',');
            }
        }
        builder.append(']');
    }

    /**
     * 自定义对象类型 转换为 String
     */
    private static void processBean(Object obj, StringBuilder builder) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = obj.getClass();

        List<Pair<char[], Method>> properties = getType(clazz).getProperties();

        int size = properties.size();
        if (size == 0) {
            builder.append("{}");
            return;
        }

        builder.append('{');
        for (Pair<char[], Method> property : properties) {
            Method method = property.value();
            if (method.canAccess(obj)) {
                Object value = method.invoke(obj);
                if (value != null) {
                    builder.append('\"').append(property.key()).append("\":");
                    processValue(value, builder);
                    builder.append(',');
                }
            }
        }

        int last = builder.length() - 1;
        if (builder.charAt(last) == ',') {
            builder.setCharAt(last, '}');
        } else {
            builder.append('}');
        }
    }

    private static JavaType getType(Class<?> rawClass) throws IntrospectionException {
        JavaType type = TYPE_MAP.get(rawClass);
        if (type != null) {
            return type;
        }

        type = new JavaType();

        Class<?> superclass = rawClass.getSuperclass();
        Field[] fields = rawClass.getDeclaredFields();
        Field[] superFields = superclass.getDeclaredFields();

        List<Pair<char[], Method>> pairs = new ArrayList<>(fields.length + superFields.length);
        readProperties(fields, rawClass, pairs);
        readProperties(superFields, rawClass, pairs);

        type.setProperties(pairs);

        TYPE_MAP.put(rawClass, type);
        return type;
    }

    private static void readProperties(Field[] fields, Class<?> clazz, List<Pair<char[], Method>> pairs) throws IntrospectionException {
        for (Field field : fields) {
            String name = field.getName();
            PropertyDescriptor descriptor = new PropertyDescriptor(name, clazz);
            Method readMethod = descriptor.getReadMethod();
            if (readMethod != null) {
                pairs.add(Pairs.of(name.toCharArray(), readMethod));
            }
        }
    }

    /**
     * 处理 键
     * <p>
     * 如果键是复杂对象类型，转换成 String 后有双引号 ""，这些双引号要再进行转义，否则无法再转换为有效的 json 对象。
     */
    private static void processKey(Object key, StringBuilder builder) {
        if (key == null) {
            return;
        }
        if (key instanceof CharSequence || key instanceof Enum<?> || key instanceof Character || key instanceof Boolean || key instanceof Number) {
            builder.append(key);
            return;
        }

        StringBuilder subBuilder = new StringBuilder();
        process(key, subBuilder);
        builder.append(subBuilder.toString().replaceAll("\"", "\\\\\""));
    }

    /**
     * 处理 值，根据数据类型判断是否添加双引号
     */
    private static void processValue(Object val, StringBuilder builder) {
        if (val == null) {
            return;
        }

        if (val instanceof CharSequence || val instanceof Enum<?> || val instanceof Character) {
            builder.append("\"").append(val).append("\"");
            return;
        }

        if (val instanceof Boolean || val instanceof Number) {
            builder.append(val);
            return;
        }

        process(val, builder);
    }

    private static class JavaType {

        private List<Pair<char[], Method>> properties;

        public List<Pair<char[], Method>> getProperties() {
            return properties;
        }

        public void setProperties(List<Pair<char[], Method>> properties) {
            this.properties = properties;
        }
    }

}