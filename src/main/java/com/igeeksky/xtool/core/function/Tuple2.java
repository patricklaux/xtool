package com.igeeksky.xtool.core.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple2<T1, T2> {

    final T1 t1;
    final T2 t2;

    protected Tuple2(T1 t1, T2 t2) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }

    public <R> Tuple2<R, T2> mapT1(Function<T1, R> mapper) {
        return new Tuple2<>(mapper.apply(t1), t2);
    }

    public <R> Tuple2<T1, R> mapT2(Function<T2, R> mapper) {
        return new Tuple2<>(t1, mapper.apply(t2));
    }

    public int size() {
        return 2;
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
        return "{\"t1\":" +
                ((t1 instanceof String) ? ("\"" + t1 + "\"") : t1) +
                ", \"t2\":" +
                ((t2 instanceof String) ? ("\"" + t2 + "\"") : t2) +
                "}";
    }
}
