package com.igeeksky.xtool.core.function.tuple;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * 二元组
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple2<T1, T2> implements Tuple {

    final T1 t1;
    final T2 t2;

    Tuple2(T1 t1, T2 t2) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
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
     * 转换第一个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple2<R, T2> mapT1(Function<T1, R> mapper) {
        return new Tuple2<>(mapper.apply(t1), t2);
    }

    /**
     * 转换第二个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple2<T1, R> mapT2(Function<T2, R> mapper) {
        return new Tuple2<>(t1, mapper.apply(t2));
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{t1, t2};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple2)) {
            return false;
        }

        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;

        if (!getT1().equals(tuple2.getT1())) {
            return false;
        }
        return getT2().equals(tuple2.getT2());
    }

    @Override
    public int hashCode() {
        int result = getT1().hashCode();
        result = 31 * result + getT2().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
