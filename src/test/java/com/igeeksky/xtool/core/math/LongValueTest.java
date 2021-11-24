/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.math;


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