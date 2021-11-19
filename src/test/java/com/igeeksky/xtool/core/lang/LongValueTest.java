package com.igeeksky.xtool.core.lang;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class LongValueTest {

    @Test
    public void get() {
        LongValue value = new LongValue();
        org.junit.Assert.assertEquals(0L, value.get());
    }

    @Test
    public void set() {
        LongValue value = new LongValue(100000000000L);
        value.set(100000000001L);
        org.junit.Assert.assertEquals(100000000001L, value.get());
    }

    @Test
    public void getAndSet() {
        LongValue value = new LongValue(100000000000L);
        long oldVal = value.getAndSet(100000000001L);
        org.junit.Assert.assertEquals(100000000000L, oldVal);
        org.junit.Assert.assertEquals(100000000001L, value.get());
    }

    @Test
    public void increment() {
        LongValue value = new LongValue(100000000000L);
        value.increment();
        org.junit.Assert.assertEquals(100000000001L, value.get());
        value.increment();
        org.junit.Assert.assertEquals(100000000002L, value.get());
        org.junit.Assert.assertEquals(100000000002L, value.get());
    }

    @Test
    public void incrementAndGet() {
        LongValue value = new LongValue(100000000000L);
        long newVal = value.incrementAndGet();
        org.junit.Assert.assertEquals(100000000001L, newVal);
        org.junit.Assert.assertEquals(100000000001L, value.get());
    }

    @Test
    public void getAndIncrement() {
        LongValue value = new LongValue(100000000000L);
        long oldVal = value.getAndIncrement();
        org.junit.Assert.assertEquals(100000000000L, oldVal);
        org.junit.Assert.assertEquals(100000000001L, value.get());
        oldVal = value.getAndIncrement();
        org.junit.Assert.assertEquals(100000000001L, oldVal);
        org.junit.Assert.assertEquals(100000000002L, value.get());
    }

    @Test
    public void decrement() {
        LongValue value = new LongValue(100000000000L);
        value.decrement();
        org.junit.Assert.assertEquals(99999999999L, value.get());
        value.decrement();
        org.junit.Assert.assertEquals(99999999998L, value.get());
        org.junit.Assert.assertEquals(99999999998L, value.get());
    }

    @Test
    public void decrementAndGet() {
        LongValue value = new LongValue(100000000000L);
        long newVal = value.decrementAndGet();
        org.junit.Assert.assertEquals(99999999999L, newVal);
        org.junit.Assert.assertEquals(99999999999L, value.get());
    }

    @Test
    public void getAndDecrement() {
        LongValue value = new LongValue(100000000000L);
        long oldVal = value.getAndDecrement();
        org.junit.Assert.assertEquals(100000000000L, oldVal);
        org.junit.Assert.assertEquals(99999999999L, value.get());
        oldVal = value.getAndDecrement();
        org.junit.Assert.assertEquals(99999999999L, oldVal);
        Assert.assertEquals(99999999998L, value.get());
    }
}