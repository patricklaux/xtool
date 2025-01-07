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
public record Pair<K, V>(K key, V value) implements Serializable {

    private static final Pair<?, ?> EMPTY = new Pair<>(null, null);

    public static <K, V> Pair<K, V> create(K key, V value) {
        return new Pair<>(key, value);
    }

    /**
     * 静态工厂：返回不包含值的 {@link Pair} 单例对象
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return {@link Pair} -- singleton
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Pair<K, V> emptyPair() {
        return (Pair<K, V>) EMPTY;
    }

    public <K1, V1> Pair<K1, V1> map(Function<K, K1> keyMapper, Function<V, V1> valueMapper) {
        return new Pair<>(keyMapper.apply(key), valueMapper.apply(value));
    }

    /**
     * 转换 key，并返回新的 Pair
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的 Pair
     */
    public <R> Pair<R, V> mapKey(Function<K, R> mapper) {
        return new Pair<>(mapper.apply(key), value);
    }

    /**
     * 转换 value，并返回新的 Pair
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的 Pair
     */
    public <R> Pair<K, R> mapValue(Function<V, R> mapper) {
        return new Pair<>(key, mapper.apply(value));
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair<?, ?> pair)) {
            return false;
        }
        return ObjectUtils.deepEquals(key, pair.key()) &&
                ObjectUtils.deepEquals(value, pair.value());
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