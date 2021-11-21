package com.igeeksky.xtool.core.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class IntegerValueTest {


    @Test
    public void get() {
        IntegerValue integer = new IntegerValue();
        Assert.assertEquals(0, integer.get());
    }

    @Test
    public void set() {
        IntegerValue integer = new IntegerValue(100);
        integer.set(1);
        Assert.assertEquals(1, integer.get());
    }

    @Test
    public void getAndSet() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndSet(1);
        Assert.assertEquals(100, oldVal);
        Assert.assertEquals(1, integer.get());
    }

    @Test
    public void increment() {
        IntegerValue integer = new IntegerValue(100);
        integer.increment();
        Assert.assertEquals(101, integer.get());
        integer.increment();
        Assert.assertEquals(102, integer.get());
        Assert.assertEquals(102, integer.get());
    }

    @Test
    public void incrementAndGet() {
        IntegerValue integer = new IntegerValue(100);
        int newVal = integer.incrementAndGet();
        Assert.assertEquals(101, newVal);
        Assert.assertEquals(101, integer.get());
    }

    @Test
    public void getAndIncrement() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndIncrement();
        Assert.assertEquals(100, oldVal);
        Assert.assertEquals(101, integer.get());
        oldVal = integer.getAndIncrement();
        Assert.assertEquals(101, oldVal);
        Assert.assertEquals(102, integer.get());
    }

    @Test
    public void decrement() {
        IntegerValue integer = new IntegerValue(100);
        integer.decrement();
        Assert.assertEquals(99, integer.get());
        integer.decrement();
        Assert.assertEquals(98, integer.get());
        Assert.assertEquals(98, integer.get());
    }

    @Test
    public void decrementAndGet() {
        IntegerValue integer = new IntegerValue(100);
        int newVal = integer.decrementAndGet();
        Assert.assertEquals(99, newVal);
        Assert.assertEquals(99, integer.get());
    }

    @Test
    public void getAndDecrement() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndDecrement();
        Assert.assertEquals(100, oldVal);
        Assert.assertEquals(99, integer.get());
        oldVal = integer.getAndDecrement();
        Assert.assertEquals(99, oldVal);
        Assert.assertEquals(98, integer.get());
    }
}