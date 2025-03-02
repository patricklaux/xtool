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


package com.igeeksky.xtool.core;

import com.igeeksky.xtool.core.lang.ObjectUtils;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 键值对
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class KeyValue<K, V> implements Serializable {

    private static final KeyValue<?, ?> EMPTY = new KeyValue<>(null, null);

    private final K key;

    private final V value;

    /**
     * 使用给定的键和值创建 {@link KeyValue} 对象
     *
     * @param key   键（可以为空）
     * @param value 值（可以为空）
     */
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取键
     *
     * @return 键
     */
    public K getKey() {
        return key;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public V getValue() {
        return value;
    }

    /**
     * 将当前 KeyValue 对象中的原键转换为新键，并返回新的 {@link KeyValue} 对象
     *
     * @param mapper 转换函数
     * @param <R>    新键类型
     * @return {@link KeyValue} – 新的 KeyValue 对象（新键 + 原值）
     */
    public <R> KeyValue<R, V> mapKey(Function<K, R> mapper) {
        return new KeyValue<>(mapper.apply(key), value);
    }

    /**
     * 将当前 {@link KeyValue} 对象中的原值转换为新值，并返回新的 {@link KeyValue} 对象
     *
     * @param mapper 转换函数
     * @param <R>    新值类型
     * @return {@link KeyValue} – 新的 KeyValue 对象（原键 + 新值）
     */
    public <R> KeyValue<K, R> mapValue(Function<V, R> mapper) {
        return new KeyValue<>(key, mapper.apply(value));
    }

    /**
     * 将当前 KeyValue 对象中的原键和原值转换为新键和新值，并返回新的 {@link KeyValue} 对象
     *
     * @param keyMapper   转换函数：用于将当前 KeyValue 对象的原键转换为新键
     * @param valueMapper 转换函数：用于将当前 KeyValue 对象的原值转换为新值
     * @param <K1>        新键类型
     * @param <V1>        新值类型
     * @return {@link KeyValue} – 新的 KeyValue 对象（新键 + 新值）
     */
    public <K1, V1> KeyValue<K1, V1> map(Function<K, K1> keyMapper, Function<V, V1> valueMapper) {
        return new KeyValue<>(keyMapper.apply(key), valueMapper.apply(value));
    }

    /**
     * 是否包含键
     *
     * @return {@code boolean} - 如果键不为空，返回 {@code true}; 否则返回 {@code false}。
     */
    public boolean hasKey() {
        return null != key;
    }

    /**
     * 是否包含值
     *
     * @return {@code boolean} - 如果值不为空，返回 {@code true}; 否则返回 {@code false}。
     */
    public boolean hasValue() {
        return null != value;
    }

    /**
     * 获取空的 {@link KeyValue} 对象
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return {@link KeyValue} – 空的 KeyValue 对象
     */
    @SuppressWarnings("unchecked")
    public static <K, V> KeyValue<K, V> empty() {
        return (KeyValue<K, V>) EMPTY;
    }

    /**
     * 创建 {@link KeyValue} 对象
     *
     * @param key   键（可以为空）
     * @param value 值（可以为空）
     * @param <K>   键类型
     * @param <V>   值类型
     * @return {@link KeyValue} – 键值对
     */
    public static <K, V> KeyValue<K, V> create(K key, V value) {
        return new KeyValue<>(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KeyValue<?, ?> kv)) {
            return false;
        }
        return ObjectUtils.deepEquals(getKey(), kv.getKey()) &&
                ObjectUtils.deepEquals(getValue(), kv.getValue());
    }

    @Override
    public int hashCode() {
        int result = ObjectUtils.deepHashCode(key);
        result = 31 * result + ObjectUtils.deepHashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "{" + "\"key\":"
                + (null != key ? ((key instanceof String ? ("\"" + key + "\"") : key)) : "")
                + ", \"value\":"
                + (null != value ? ((value instanceof String ? ("\"" + value + "\"") : value)) : "")
                + "}";
    }

}