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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerValue that)) return false;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }

}
