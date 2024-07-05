/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.collection;

import com.igeeksky.xtool.core.lang.Assert;
import com.igeeksky.xtool.core.lang.BooleanUtils;
import com.igeeksky.xtool.core.math.NumberUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map 工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public final class Maps {

    private static final double DEFAULT_LOAD_FACTOR = 0.75D;

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
     * 合并两个 map，sourceMap 的 key-value 合并到 targetMap
     *
     * <pre>
     *     if (!targetMap.containsKey(key)) {
     *         targetMap.put(key, value);
     *     }
     * </pre>
     *
     * @param targetMap 目标对象（目标对象 需要能够保存元素，不能为 {@link Collections#emptyMap} || {@link Collections#singletonMap} ……等无法保存元素的容器
     * @param sourceMap 源对象
     * @param <K>       Key 类型
     * @param <V>       Value 类型
     * @return target 合并后的 Map
     */
    public static <K, V> Map<K, V> merge(Map<K, V> targetMap, Map<K, V> sourceMap) {
        Set<Map.Entry<K, V>> entrySet = sourceMap.entrySet();
        for (Map.Entry<K, V> entry : entrySet) {
            K key = entry.getKey();
            if (!targetMap.containsKey(key)) {
                targetMap.put(key, entry.getValue());
            }
        }
        return targetMap;
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Long}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Long}并返回；否则返回空（转换异常，则抛出异常信息）
     */
    public static <K, V> Long getLong(Map<K, V> map, K key) {
        return NumberUtils.toLong(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Long}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Long}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> long getLong(Map<K, V> map, K key, long defaultValue) {
        return NumberUtils.toLong(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Integer}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Integer}并返回；否则返回空（转换异常，则抛出异常信息）
     */
    public static <K, V> Integer getInteger(Map<K, V> map, K key) {
        return NumberUtils.toInteger(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Integer}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Integer}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> int getInteger(Map<K, V> map, K key, int defaultValue) {
        return NumberUtils.toInteger(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Short}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Short}并返回；否则返回空（转换异常，则抛出异常信息）
     */
    public static <K, V> Short getShort(Map<K, V> map, K key) {
        return NumberUtils.toShort(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Short}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Short}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> short getShort(Map<K, V> map, K key, short defaultValue) {
        return NumberUtils.toShort(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Byte}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Byte}并返回；否则返回空（转换异常，则抛出异常信息）
     */
    public static <K, V> Byte getByte(Map<K, V> map, K key) {
        return NumberUtils.toByte(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Byte}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Byte}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> byte getByte(Map<K, V> map, K key, byte defaultValue) {
        return NumberUtils.toByte(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Double}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Double}并返回；否则返回空（转换异常，则抛出异常信息）
     */
    public static <K, V> Double getDouble(Map<K, V> map, K key) {
        return NumberUtils.toDouble(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Double}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Double}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> double getDouble(Map<K, V> map, K key, double defaultValue) {
        return NumberUtils.toDouble(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Float}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Float}并返回；否则返回空（转换异常，则抛出异常信息）
     */
    public static <K, V> Float getFloat(Map<K, V> map, K key) {
        return NumberUtils.toFloat(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Float}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Float}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> float getFloat(Map<K, V> map, K key, float defaultValue) {
        return NumberUtils.toFloat(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Boolean}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为 {@link Boolean} 并返回；否则返回空（转换异常，抛出异常信息）
     */
    public static <K, V> Boolean getBoolean(Map<K, V> map, K key) {
        return BooleanUtils.toBoolean(getValue(map, key));
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link Boolean}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将 value 转换为{@link Boolean}并返回；否则返回 defaultValue（转换异常，返回 defaultValue）
     */
    public static <K, V> boolean getBoolean(Map<K, V> map, K key, boolean defaultValue) {
        return BooleanUtils.toBoolean(getValue(map, key), defaultValue);
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link String}
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将该 value 转换为{@link String}并返回；否则返回空
     */
    public static <K, V> String getString(Map<K, V> map, K key) {
        V value = getValue(map, key);
        return (value == null) ? null : value.toString();
    }

    /**
     * 获取 Map 中 key 对应的值，并转换为 {@link String}
     *
     * @param map          map
     * @param key          键
     * @param defaultValue 默认值
     * @param <K>          键类型
     * @param <V>          值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，将该 value 转换为{@link String}并返回；否则返回 defaultValue
     */
    public static <K, V> String getString(Map<K, V> map, K key, String defaultValue) {
        Assert.notNull(defaultValue, "defaultValue must not be null");
        String value = getString(map, key);
        return (value == null) ? defaultValue : value;
    }

    /**
     * 获取 Map 中 key 对应的值
     *
     * @param map map
     * @param key 键
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 如果 map 不为空且 key 对应的 value 存在，返回 value；否则返回空
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
     * @return 如果 map 不为空且 key 对应的 value 存在，返回 value；否则返回 defaultValue
     */
    public static <K, V> V getValue(Map<K, V> map, K key, V defaultValue) {
        Assert.notNull(defaultValue, "defaultValue must not be null");
        V value = getValue(map, key);
        return (value == null) ? defaultValue : value;
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMap(int expectedSize) {
        return new HashMap<>(calculateCapacity(expectedSize));
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int expectedSize) {
        return new LinkedHashMap<>(calculateCapacity(expectedSize));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<>();
    }

    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap(int expectedSize) {
        return new ConcurrentHashMap<>(calculateCapacity(expectedSize));
    }

    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap(Map<? extends K, ? extends V> map) {
        return new ConcurrentHashMap<>(map);
    }

    private static int calculateCapacity(int expectedSize) {
        return (int) Math.ceil(expectedSize / DEFAULT_LOAD_FACTOR);
    }

}