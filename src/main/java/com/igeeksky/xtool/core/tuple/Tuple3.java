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
 * 三元组
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple3<T1, T2, T3> implements Tuple {

    final T1 t1;
    final T2 t2;
    final T3 t3;

    Tuple3(T1 t1, T2 t2, T3 t3) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
        this.t3 = Objects.requireNonNull(t3, "t3");
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
     * 获取第二个元素
     *
     * @return 第二个元素
     */
    public T2 getT2() {
        return t2;
    }

    /**
     * 获取第三个元素
     *
     * @return 第三个元素
     */
    public T3 getT3() {
        return t3;
    }

    /**
     * 转换第一个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple3<R, T2, T3> mapT1(Function<T1, R> mapper) {
        return new Tuple3<>(mapper.apply(t1), t2, t3);
    }

    /**
     * 转换第二个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple3<T1, R, T3> mapT2(Function<T2, R> mapper) {
        return new Tuple3<>(t1, mapper.apply(t2), t3);
    }

    /**
     * 转换第三个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple3<T1, T2, R> mapT3(Function<T3, R> mapper) {
        return new Tuple3<>(t1, t2, mapper.apply(t3));
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{t1, t2, t3};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple3<?, ?, ?> tuple3)) {
            return false;
        }
        return ObjectUtils.deepEquals(t1, tuple3.t1)
                && ObjectUtils.deepEquals(t2, tuple3.t2)
                && ObjectUtils.deepEquals(t3, tuple3.t3);
    }

    @Override
    public int hashCode() {
        int result = ObjectUtils.deepHashCode(t1);
        result = 31 * result + ObjectUtils.deepHashCode(t2);
        result = 31 * result + ObjectUtils.deepHashCode(t3);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
