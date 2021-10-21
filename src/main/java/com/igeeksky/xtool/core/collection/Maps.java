package com.igeeksky.xtool.core.collection;

import com.igeeksky.xtool.core.lang.BooleanUtils;
import com.igeeksky.xtool.core.lang.NumberUtils;
import com.igeeksky.xtool.core.lang.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public abstract class Maps {

    private Maps() {
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return (map != null && !map.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMap(int capacity) {
        return new HashMap<>(capacity);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int capacity) {
        return new LinkedHashMap<>(capacity);
    }

    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<>();
    }

    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap(int capacity) {
        return new ConcurrentHashMap<>(capacity);
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> keyType) {
        return new EnumMap<>(keyType);
    }

    public static <K, V> TreeMap<? extends Comparable<K>, V> newTreeMap() {
        return new TreeMap<>();
    }

    public static <K, V> TreeMap<K, V> newTreeMap(Comparator<? super K> comparator) {
        return new TreeMap<>(comparator);
    }

    public static <K, V> ConcurrentSkipListMap<? extends Comparable<K>, V> newConcurrentSkipListMap() {
        return new ConcurrentSkipListMap<>();
    }

    /**
     * <p>将源对象的键值对合并到目标对象</p>
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
        return StringUtils.trim((String) getObject(map, key));
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
