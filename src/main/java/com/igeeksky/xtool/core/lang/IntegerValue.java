package com.igeeksky.xtool.core.lang;

/**
 * int 值包装类 ！！非原子操作
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-16
 */
public class IntegerValue {

    private int value;

    public IntegerValue() {
    }

    public IntegerValue(int initialValue) {
        value = initialValue;
    }

    public final int get() {
        return value;
    }

    public final void set(int value) {
        this.value = value;
    }

    public final int getAndSet(int newValue) {
        int val = value;
        value = newValue;
        return val;
    }

    public final void increment() {
        ++value;
    }

    public final int incrementAndGet() {
        return ++value;
    }

    public final int getAndIncrement() {
        return value++;
    }

    public final void decrement() {
        --value;
    }

    public final int decrementAndGet() {
        return --value;
    }

    public final int getAndDecrement() {
        return value--;
    }
}
