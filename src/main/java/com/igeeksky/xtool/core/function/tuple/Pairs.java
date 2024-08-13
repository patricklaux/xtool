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

/**
 * 键值对工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Pairs {

    private static final Pair<?, ?> EMPTY = new Pair<>(null, null);

    /**
     * 静态工厂：构建 {@link Pair} 对象
     *
     * @param key   键
     * @param value 值
     * @param <K>   键类型
     * @param <V>   值类型
     * @return {@link Pair}
     */
    public static <K, V> Pair<K, V> of(K key, V value) {
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

}
