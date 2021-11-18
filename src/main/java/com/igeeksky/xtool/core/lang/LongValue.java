package com.igeeksky.xtool.core.lang;

/**
 * long 值包装类，！！非原子操作
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-16
 */
public class LongValue {

    private long value;

    public LongValue() {
    }

    public LongValue(long initialValue) {
        value = initialValue;
    }

    public final long get() {
        return value;
    }

    public final void set(long value) {
        this.value = value;
    }

    public final long getAndSet(long newValue) {
        long val = value;
        value = newValue;
        return val;
    }

    public final void increment() {
        ++value;
    }

    public final long incrementAndGet() {
        return ++value;
    }

    public final long getAndIncrement() {
        return value++;
    }

    public final void decrement() {
        --value;
    }

    public final long decrementAndGet() {
        return --value;
    }

    public final long getAndDecrement() {
        return value--;
    }
}
