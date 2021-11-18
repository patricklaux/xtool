package com.igeeksky.xtool.core.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple<T1> {

    final T1 t1;

    protected Tuple(T1 t1) {
        this.t1 = Objects.requireNonNull(t1, "t1");
    }

    public T1 getT1() {
        return t1;
    }

    public <R> Tuple<R> mapT1(Function<T1, R> mapper) {
        return new Tuple<>(mapper.apply(t1));
    }

    public int size() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple)) {
            return false;
        }
        Tuple<?> tuple = (Tuple<?>) o;
        return getT1().equals(tuple.getT1());
    }

    @Override
    public int hashCode() {
        return getT1().hashCode();
    }

    @Override
    public String toString() {
        return "{\"t1\":" + ((t1 instanceof String) ? ("\"" + t1 + "\"") : t1) + "}";
    }
}
