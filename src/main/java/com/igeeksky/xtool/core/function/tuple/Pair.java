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
import java.util.function.Function;

/**
 * 键值对
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Pair<K, V> implements Serializable {

    private final K key;
    private final V value;

    @ParameterNames({"key", "value"})
    public Pair(K key, V value) {
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
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (getKey() != null ? !getKey().equals(pair.getKey()) : pair.getKey() != null) {
            return false;
        }
        return getValue() != null ? getValue().equals(pair.getValue()) : pair.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = getKey() != null ? getKey().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
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