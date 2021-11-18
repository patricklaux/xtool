package com.igeeksky.xtool.core.collection;

import com.igeeksky.xtool.core.lang.BooleanUtils;
import com.igeeksky.xtool.core.lang.NumberUtils;
import com.igeeksky.xtool.core.lang.StringUtils;

import java.util.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Maps {

    private Maps() {
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return (map != null && !map.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * <p>将源对象的键值对合并到目标对象</p>
     *
     * <pre>
     * if(!target.containsKey(key)) {
     *     target.put(key, value);
     * }
     * </pre>
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <K>    Key 类型
     * @param <V>    Value 类型
     * @return target
     */
    public static <K, V> Map<K, V> merge(Map<K, V> source, Map<K, V> target) {
        source.forEach((key, value) -> target.computeIfAbsent(key, k -> value));
        return target;
    }

    public static <K, V> Long getLong(Map<K, V> map, K key) {
        return NumberUtils.longValue(getObject(map, key));
    }

    public static <K, V> Long getLong(Map<K, V> map, K key, Long defaultValue) {
        Long value = getLong(map, key);
        return null != value ? value : defaultValue;
    }

    public static <K, V> Integer getInteger(Map<K, V> map, K key) {
        return NumberUtils.intValue(getObject(map, key));
    }

    public static <K, V> Integer getInteger(Map<K, V> map, K key, Integer defaultValue) {
        Integer value = getInteger(map, key);
        return null != value ? value : defaultValue;
    }

    public static <K, V> Short getShort(Map<K, V> map, K key) {
        return NumberUtils.shortValue(getObject(map, key));
    }

    public static <K, V> Short getShort(Map<K, V> map, K key, Short defaultValue) {
        Short value = getShort(map, key);
        return null != value ? value : defaultValue;
    }

    public static <K, V> Byte getByte(Map<K, V> map, K key) {
        return NumberUtils.byteValue(getObject(map, key));
    }

    public static <K, V> Byte getByte(Map<K, V> map, K key, Byte defaultValue) {
        Byte value = getByte(map, key);
        return null != value ? value : defaultValue;
    }

    public static <K, V> Boolean getBoolean(Map<K, V> map, K key) {
        return BooleanUtils.booleanValue(getObject(map, key));
    }

    public static <K, V> Boolean getBoolean(Map<K, V> map, K key, Boolean defaultValue) {
        Boolean value = getBoolean(map, key);
        return null != value ? value : defaultValue;
    }

    public static <K, V> String getString(Map<K, V> map, K key) {
        return StringUtils.trimToNull((String) getObject(map, key));
    }

    public static <K, V> String getString(Map<K, V> map, K key, String defaultValue) {
        String value = getString(map, key);
        return (null != value) ? value : defaultValue;
    }

    public static <K, V> V getObject(Map<K, V> map, K key) {
        return (null == map) ? null : map.get(key);
    }

    public static <K, V> V getObject(Map<K, V> map, K key, V defaultValue) {
        V value = getObject(map, key);
        return (null != value) ? value : defaultValue;
    }
}
