package com.igeeksky.xtool.core.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple3<T1, T2, T3> {

    final T1 t1;
    final T2 t2;
    final T3 t3;

    protected Tuple3(T1 t1, T2 t2, T3 t3) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
        this.t3 = Objects.requireNonNull(t3, "t3");
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }

    public T3 getT3() {
        return t3;
    }

    public <R> Tuple3<R, T2, T3> mapT1(Function<T1, R> mapper) {
        return new Tuple3<>(mapper.apply(t1), t2, t3);
    }

    public <R> Tuple3<T1, R, T3> mapT2(Function<T2, R> mapper) {
        return new Tuple3<>(t1, mapper.apply(t2), t3);
    }

    public <R> Tuple3<T1, T2, R> mapT3(Function<T3, R> mapper) {
        return new Tuple3<>(t1, t2, mapper.apply(t3));
    }

    public int size() {
        return 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple3)) {
            return false;
        }

        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;

        if (!getT1().equals(tuple3.getT1())) {
            return false;
        }
        if (!getT2().equals(tuple3.getT2())) {
            return false;
        }
        return getT3().equals(tuple3.getT3());
    }

    @Override
    public int hashCode() {
        int result = getT1().hashCode();
        result = 31 * result + getT2().hashCode();
        result = 31 * result + getT3().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{\"t1\":" +
                ((t1 instanceof String) ? ("\"" + t1 + "\"") : t1) +
                ", \"t2\":" +
                ((t2 instanceof String) ? ("\"" + t2 + "\"") : t2) +
                ", \"t3\":" +
                ((t3 instanceof String) ? ("\"" + t3 + "\"") : t3) +
                "}";
    }
}
