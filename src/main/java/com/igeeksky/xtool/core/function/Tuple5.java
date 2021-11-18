package com.igeeksky.xtool.core.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
public class Tuple5<T1, T2, T3, T4, T5> {

    final T1 t1;
    final T2 t2;
    final T3 t3;
    final T4 t4;
    final T5 t5;

    protected Tuple5(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
        this.t3 = Objects.requireNonNull(t3, "t3");
        this.t4 = Objects.requireNonNull(t4, "t4");
        this.t5 = Objects.requireNonNull(t5, "t5");
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

    public T4 getT4() {
        return t4;
    }

    public T5 getT5() {
        return t5;
    }

    public <R> Tuple5<R, T2, T3, T4, T5> mapT1(Function<T1, R> mapper) {
        return new Tuple5<>(mapper.apply(t1), t2, t3, t4, t5);
    }

    public <R> Tuple5<T1, R, T3, T4, T5> mapT2(Function<T2, R> mapper) {
        return new Tuple5<>(t1, mapper.apply(t2), t3, t4, t5);
    }

    public <R> Tuple5<T1, T2, R, T4, T5> mapT3(Function<T3, R> mapper) {
        return new Tuple5<>(t1, t2, mapper.apply(t3), t4, t5);
    }

    public <R> Tuple5<T1, T2, T3, R, T5> mapT4(Function<T4, R> mapper) {
        return new Tuple5<>(t1, t2, t3, mapper.apply(t4), t5);
    }

    public <R> Tuple5<T1, T2, T3, T4, R> mapT5(Function<T5, R> mapper) {
        return new Tuple5<>(t1, t2, t3, t4, mapper.apply(t5));
    }

    public int size() {
        return 5;
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
        return "{\"t1\":" +
                ((t1 instanceof String) ? ("\"" + t1 + "\"") : t1) +
                ", \"t2\":" +
                ((t2 instanceof String) ? ("\"" + t2 + "\"") : t2) +
                ", \"t3\":" +
                ((t3 instanceof String) ? ("\"" + t3 + "\"") : t3) +
                ", \"t4\":" +
                ((t4 instanceof String) ? ("\"" + t4 + "\"") : t4) +
                ", \"t5\":" +
                ((t5 instanceof String) ? ("\"" + t5 + "\"") : t5) +
                "}";
    }
}
