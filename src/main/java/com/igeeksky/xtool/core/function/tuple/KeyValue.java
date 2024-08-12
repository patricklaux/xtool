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


package com.igeeksky.xtool.core.function.tuple;

import com.igeeksky.xtool.core.annotation.ParameterNames;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

/**
 * 键值对
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class KeyValue<K, V> implements Serializable {

    private final K key;

    private final V value;

    public KeyValue() {
        this(null, null);
    }

    @ParameterNames({"key", "value"})
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取 key
     *
     * @return 键
     */
    public K getKey() {
        return key;
    }

    /**
     * 获取 value
     *
     * @return 值
     */
    public V getValue() {
        return value;
    }

    /**
     * 转换 key，并返回新的 Pair
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的 Pair
     */
    public <R> KeyValue<R, V> mapKey(Function<K, R> mapper) {
        return new KeyValue<>(mapper.apply(key), value);
    }

    /**
     * 转换 value，并返回新的 Pair
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的 Pair
     */
    public <R> KeyValue<K, R> mapValue(Function<V, R> mapper) {
        return new KeyValue<>(key, mapper.apply(value));
    }

    /**
     * 将当前 KeyValue 对象中的键和值映射为新的键和值
     *
     * @param keyMapper 转换函数：用于将当前 KeyValue 对象的键转换为新键
     * @param valueMapper 转换函数：用于将当前 KeyValue 对象的值转换为新值
     * @param <K1> 新键的类型
     * @param <V1> 新值的类型
     * @return 包含映射后键值对的新的KeyValue对象
     */
    public <K1, V1> KeyValue<K1, V1> map(Function<K, K1> keyMapper, Function<V, V1> valueMapper) {
        return new KeyValue<>(keyMapper.apply(key), valueMapper.apply(value));
    }


    /**
     * 是否包含键
     *
     * @return boolean
     */
    public boolean hasKey() {
        return null != key;
    }

    /**
     * 是否包含值
     *
     * @return boolean
     */
    public boolean hasValue() {
        return null != value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyValue<?, ?> keyValue)) return false;

        return Objects.equals(getKey(), keyValue.getKey()) && Objects.equals(getValue(), keyValue.getValue());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getKey());
        result = 31 * result + Objects.hashCode(getValue());
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