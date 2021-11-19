package com.igeeksky.xtool.core.function;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple1)) {
            return false;
        }
        Tuple1<?> tuple1 = (Tuple1<?>) o;
        return getT1().equals(tuple1.getT1());
    }

    @Override
    public int hashCode() {
        return getT1().hashCode();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
