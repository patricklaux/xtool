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


package com.igeeksky.xtool.core.tuple;

import com.igeeksky.xtool.core.lang.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * 一元组
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple1<T1> implements Tuple {

    final T1 t1;

    Tuple1(T1 t1) {
        this.t1 = Objects.requireNonNull(t1, "t1");
    }

    /**
     * 获取第一个元素
     *
     * @return 第一个元素
     */
    public T1 getT1() {
        return t1;
    }

    /**
     * 转换第一个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple1<R> mapT1(Function<T1, R> mapper) {
        return new Tuple1<>(mapper.apply(t1));
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{t1};
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple1<?> tuple1)) {
            return false;
        }
        return ObjectUtils.deepEquals(t1, tuple1.t1);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.deepHashCode(t1);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
