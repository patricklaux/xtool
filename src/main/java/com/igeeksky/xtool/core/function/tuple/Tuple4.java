package com.igeeksky.xtool.core.function.tuple;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * 四元组
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple4<T1, T2, T3, T4> implements Tuple {

    final T1 t1;
    final T2 t2;
    final T3 t3;
    final T4 t4;

    Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
        this.t3 = Objects.requireNonNull(t3, "t3");
        this.t4 = Objects.requireNonNull(t4, "t4");
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
     * 获取第四个元素
     *
     * @return 第四个元素
     */
    public T4 getT4() {
        return t4;
    }

    /**
     * 转换第一个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple4<R, T2, T3, T4> mapT1(Function<T1, R> mapper) {
        return new Tuple4<>(mapper.apply(t1), t2, t3, t4);
    }

    /**
     * 转换第二个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple4<T1, R, T3, T4> mapT2(Function<T2, R> mapper) {
        return new Tuple4<>(t1, mapper.apply(t2), t3, t4);
    }

    /**
     * 转换第三个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple4<T1, T2, R, T4> mapT3(Function<T3, R> mapper) {
        return new Tuple4<>(t1, t2, mapper.apply(t3), t4);
    }

    /**
     * 转换第四个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple4<T1, T2, T3, R> mapT4(Function<T4, R> mapper) {
        return new Tuple4<>(t1, t2, t3, mapper.apply(t4));
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{t1, t2, t3, t4};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple4)) {
            return false;
        }

        Tuple4<?, ?, ?, ?> tuple4 = (Tuple4<?, ?, ?, ?>) o;

        if (!getT1().equals(tuple4.getT1())) {
            return false;
        }
        if (!getT2().equals(tuple4.getT2())) {
            return false;
        }
        if (!getT3().equals(tuple4.getT3())) {
            return false;
        }
        return t4.equals(tuple4.t4);
    }

    @Override
    public int hashCode() {
        int result = getT1().hashCode();
        result = 31 * result + getT2().hashCode();
        result = 31 * result + getT3().hashCode();
        result = 31 * result + t4.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
