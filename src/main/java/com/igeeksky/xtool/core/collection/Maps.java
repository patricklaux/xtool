package com.igeeksky.xtool.core.collection;

import com.igeeksky.xtool.core.lang.BooleanUtils;
import com.igeeksky.xtool.core.lang.NumberUtils;
import com.igeeksky.xtool.core.lang.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * Map 工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Maps {

    private Maps() {
    }

    /**
     * 判断 map 是否为空或不含任何元素
     *
     * @param map 待判断的 map 对象
     * @return 如果 map 为空或未含任何元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断 map 是否不为空且至少含一个元素
     *
     * @param map 待判断的 map 对象
     * @return 如果集合不为空且至少含一个元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 将源对象的键值对合并到目标对象
     *
     * <pre>
     *     if(!target.containsKey(key)) {
     *         target.put(key, value);
     *     }
     * </pre>
     *
     * @param target 目标对象（目标对象 需要能够保存元素，不能为 {@link Collections#emptyMap} || {@link Collections#singletonMap} ……等无法保存元素的容器
     * @param source 源对象
     * @param <K>    Key 类型
     * @param <V>    Value 类型
     * @return target
     */
    public static <K, V> Map<K, V> merge(Map<K, V> target, Map<K, V> source) {
        source.forEach((key, value) -> target.computeIfAbsent(key, k -> value));
        return target;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Long 并返回；否则返回空
     */
    public static <K, V> Long getLong(Map<K, V> map, K key) {
        return NumberUtils.longValue(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Long 并返回；否则返回 defaultValue
     */
    public static <K, V> Long getLong(Map<K, V> map, K key, Long defaultValue) {
        Long value = getLong(map, key);
        return null != value ? value : defaultValue;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Integer 并返回；否则返回空
     */
    public static <K, V> Integer getInteger(Map<K, V> map, K key) {
        return NumberUtils.intValue(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Integer 并返回；否则返回 defaultValue
     */
    public static <K, V> Integer getInteger(Map<K, V> map, K key, Integer defaultValue) {
        Integer value = getInteger(map, key);
        return null != value ? value : defaultValue;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Short 并返回；否则返回空
     */
    public static <K, V> Short getShort(Map<K, V> map, K key) {
        return NumberUtils.shortValue(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Short 并返回；否则返回 defaultValue
     */
    public static <K, V> Short getShort(Map<K, V> map, K key, Short defaultValue) {
        Short value = getShort(map, key);
        return null != value ? value : defaultValue;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Byte 并返回；否则返回空
     */
    public static <K, V> Byte getByte(Map<K, V> map, K key) {
        return NumberUtils.byteValue(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Byte 并返回；否则返回 defaultValue
     */
    public static <K, V> Byte getByte(Map<K, V> map, K key, Byte defaultValue) {
        Byte value = getByte(map, key);
        return null != value ? value : defaultValue;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Boolean 并返回；否则返回空
     */
    public static <K, V> Boolean getBoolean(Map<K, V> map, K key) {
        return BooleanUtils.booleanValue(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且该值存在，将该值转换为 Boolean 并返回；否则返回 defaultValue
     */
    public static <K, V> Boolean getBoolean(Map<K, V> map, K key, Boolean defaultValue) {
        Boolean value = getBoolean(map, key);
        return null != value ? value : defaultValue;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 为空或该值不存在，返回空；如果 map 不为空且该值存在，将该值转换为 String并去除空白字符，如果为空白字符串，返回空，否则返回去除空白后的字符串
     */
    public static <K, V> String getString(Map<K, V> map, K key) {
        V value = getValue(map, key);
        return (null == value) ? null : StringUtils.trimToNull(value.toString());
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 为空或该值不存在，返回 defaultValue；如果 map 不为空且该值存在，将该值转换为 String并去除空白字符，如果为空白字符串，返回 defaultValue，否则返回去除空白后的字符串
     */
    public static <K, V> String getString(Map<K, V> map, K key, String defaultValue) {
        String value = getString(map, key);
        return (null != value) ? value : defaultValue;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且该值存在则返回该值；否则返回空
     */
    public static <K, V> V getValue(Map<K, V> map, K key) {
        return (null == map) ? null : map.get(key);
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且该值存在则返回该值；否则返回 defaultValue
     */
    public static <K, V> V getValue(Map<K, V> map, K key, V defaultValue) {
        V value = getValue(map, key);
        return (null != value) ? value : defaultValue;
    }
}
