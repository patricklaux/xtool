package com.igeeksky.xtool.core.function.tuple;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * 五元组
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple5<T1, T2, T3, T4, T5> implements Tuple {

    final T1 t1;
    final T2 t2;
    final T3 t3;
    final T4 t4;
    final T5 t5;

    Tuple5(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
        this.t3 = Objects.requireNonNull(t3, "t3");
        this.t4 = Objects.requireNonNull(t4, "t4");
        this.t5 = Objects.requireNonNull(t5, "t5");
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
     * 获取第五个元素
     *
     * @return 第五个元素
     */
    public T5 getT5() {
        return t5;
    }

    /**
     * 转换第一个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple5<R, T2, T3, T4, T5> mapT1(Function<T1, R> mapper) {
        return new Tuple5<>(mapper.apply(t1), t2, t3, t4, t5);
    }

    /**
     * 转换第二个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple5<T1, R, T3, T4, T5> mapT2(Function<T2, R> mapper) {
        return new Tuple5<>(t1, mapper.apply(t2), t3, t4, t5);
    }

    /**
     * 转换第三个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple5<T1, T2, R, T4, T5> mapT3(Function<T3, R> mapper) {
        return new Tuple5<>(t1, t2, mapper.apply(t3), t4, t5);
    }

    /**
     * 转换第四个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple5<T1, T2, T3, R, T5> mapT4(Function<T4, R> mapper) {
        return new Tuple5<>(t1, t2, t3, mapper.apply(t4), t5);
    }

    /**
     * 转换第五个元素，并返回新的元组
     *
     * @param mapper 转换函数
     * @param <R>    转换类型
     * @return 包含转换后的对象的新的元组
     */
    public <R> Tuple5<T1, T2, T3, T4, R> mapT5(Function<T5, R> mapper) {
        return new Tuple5<>(t1, t2, t3, t4, mapper.apply(t5));
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{t1, t2, t3, t4, t5};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple5)) {
            return false;
        }

        Tuple5<?, ?, ?, ?, ?> tuple5 = (Tuple5<?, ?, ?, ?, ?>) o;

        if (!getT1().equals(tuple5.getT1())) {
            return false;
        }
        if (!getT2().equals(tuple5.getT2())) {
            return false;
        }
        if (!getT3().equals(tuple5.getT3())) {
            return false;
        }
        if (!getT4().equals(tuple5.getT4())) {
            return false;
        }
        return getT5().equals(tuple5.getT5());
    }

    @Override
    public int hashCode() {
        int result = getT1().hashCode();
        result = 31 * result + getT2().hashCode();
        result = 31 * result + getT3().hashCode();
        result = 31 * result + getT4().hashCode();
        result = 31 * result + getT5().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
