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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class IntegerValueTest {


    @Test
    public void get() {
        IntegerValue integer = new IntegerValue();
        Assertions.assertEquals(0, integer.get());
    }

    @Test
    public void set() {
        IntegerValue integer = new IntegerValue(100);
        integer.set(1);
        Assertions.assertEquals(1, integer.get());
    }

    @Test
    public void getAndSet() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndSet(1);
        Assertions.assertEquals(100, oldVal);
        Assertions.assertEquals(1, integer.get());
    }

    @Test
    public void increment() {
        IntegerValue integer = new IntegerValue(100);
        integer.increment();
        Assertions.assertEquals(101, integer.get());
        integer.increment();
        Assertions.assertEquals(102, integer.get());
        Assertions.assertEquals(102, integer.get());
    }

    @Test
    public void incrementAndGet() {
        IntegerValue integer = new IntegerValue(100);
        int newVal = integer.incrementAndGet();
        Assertions.assertEquals(101, newVal);
        Assertions.assertEquals(101, integer.get());
    }

    @Test
    public void getAndIncrement() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndIncrement();
        Assertions.assertEquals(100, oldVal);
        Assertions.assertEquals(101, integer.get());
        oldVal = integer.getAndIncrement();
        Assertions.assertEquals(101, oldVal);
        Assertions.assertEquals(102, integer.get());
    }

    @Test
    public void decrement() {
        IntegerValue integer = new IntegerValue(100);
        integer.decrement();
        Assertions.assertEquals(99, integer.get());
        integer.decrement();
        Assertions.assertEquals(98, integer.get());
        Assertions.assertEquals(98, integer.get());
    }

    @Test
    public void decrementAndGet() {
        IntegerValue integer = new IntegerValue(100);
        int newVal = integer.decrementAndGet();
        Assertions.assertEquals(99, newVal);
        Assertions.assertEquals(99, integer.get());
    }

    @Test
    public void getAndDecrement() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndDecrement();
        Assertions.assertEquals(100, oldVal);
        Assertions.assertEquals(99, integer.get());
        oldVal = integer.getAndDecrement();
        Assertions.assertEquals(99, oldVal);
        Assertions.assertEquals(98, integer.get());
    }
}