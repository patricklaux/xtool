package com.igeeksky.xtool.core.json;

import com.igeeksky.xtool.core.collection.Maps;
import com.igeeksky.xtool.core.function.tuple.Pair;
import com.igeeksky.xtool.core.function.tuple.Pairs;
import com.igeeksky.xtool.core.lang.StringUtils;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/9/13
 */
public class MethodResolver {

    private static final String IS_PREFIX = "is";
    private static final String GET_PREFIX = "get";

    public static List<Pair<char[], Method>> resolveReadMethod(Class<?> beanClass) {
        if (beanClass == null || beanClass == Object.class) {
            return List.of();
        }

        List<Class<?>> classes = new ArrayList<>();
        addSuperClasses(beanClass, classes);

        if (classes.isEmpty()) {
            return List.of();
        }

        List<Class<?>> reversed = classes.reversed();

        Map<String, Field> fields = getDeclaredFields(reversed);
        Map<String, Method> methods = getDeclaredMethods(reversed);

        Map<String, Method> readMethods = resolveReadMethod(fields, methods);

        List<Pair<char[], Method>> properties = new ArrayList<>(readMethods.size());
        for (Map.Entry<String, Method> entry : readMethods.entrySet()) {
            properties.add(Pairs.of(entry.getKey().toCharArray(), entry.getValue()));
        }

        return properties;
    }

    private static void addSuperClasses(Class<?> clazz, List<Class<?>> classes) {
        if (clazz == Object.class) {
            return;
        }
        classes.add(clazz);
        addSuperClasses(clazz.getSuperclass(), classes);
    }

    private static Map<String, Field> getDeclaredFields(List<Class<?>> classes) {
        Map<String, Field> result = Maps.newLinkedHashMap();
        for (Class<?> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    continue;
                }
                if (Modifier.isTransient(modifiers)) {
                    continue;
                }
                result.put(field.getName(), field);
            }
        }
        return result;
    }

    private static Map<String, Method> resolveReadMethod(Map<String, Field> fields, Map<String, Method> methods) {
        if (fields.isEmpty() || methods.isEmpty()) {
            return Map.of();
        }

        Map<String, Method> readMethods = Maps.newLinkedHashMap(fields.size());

        for (Map.Entry<String, Field> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            Field field = entry.getValue();
            String capitalized = StringUtils.capitalize(fieldName);
            Class<?> type = field.getType();
            String readMethodName, nextReadMethodName;
            if (type == boolean.class) {
                readMethodName = IS_PREFIX + capitalized;
                nextReadMethodName = GET_PREFIX + capitalized;
            } else {
                readMethodName = nextReadMethodName = GET_PREFIX + capitalized;
            }

            Method readMethod = methods.get(readMethodName);
            if (readMethod == null) {
                if (readMethodName.equals(nextReadMethodName)) {
                    continue;
                }
                readMethod = methods.get(nextReadMethodName);
            }

            if (readMethod != null) {
                validateReadMethod(readMethod);
                readMethods.put(fieldName, readMethod);
            }
        }

        return readMethods;
    }

    private static Map<String, Method> getDeclaredMethods(List<Class<?>> classes) {
        Map<String, Method> methodMap = Maps.newHashMap();
        for (Class<?> clazz : classes) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                Transient annotation = method.getAnnotation(Transient.class);
                if (annotation != null && annotation.value()) {
                    continue;
                }

                int modifiers = method.getModifiers();
                boolean isPublic = Modifier.isPublic(modifiers);
                boolean isAbstract = Modifier.isAbstract(modifiers);
                boolean isTransient = Modifier.isTransient(modifiers);
                if (isPublic && !isAbstract && !isTransient) {
                    methodMap.put(method.getName(), method);
                }
            }
        }
        return methodMap;
    }

    private static void validateReadMethod(Method readMethod) {
        Class<?> returnType = readMethod.getReturnType();
        if (returnType == Void.TYPE) {
            throw new IllegalStateException("readMethod: " + readMethod + " returns void");
        }
        int parameterCount = readMethod.getParameterCount();
        if (parameterCount != 0) {
            throw new IllegalStateException("readMethod: " + readMethod + ", bad read method arg count: " + parameterCount);
        }
    }

}
