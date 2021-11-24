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
public class NumberUtilsTest {

    @Test
    public void toLong() {
        Long expected = 123456L;

        // 情形一：数值字符串转Long，toLong == 123456L
        Long toLong = NumberUtils.toLong("123456");
        Assert.assertEquals(expected, toLong);

        // 情形二：空字符串转Long，toLong == null
        toLong = NumberUtils.toLong("");
        Assert.assertNull(toLong);

        // 情形三：空白字符串转Long，toLong == null
        toLong = NumberUtils.toLong("   ");
        Assert.assertNull(toLong);

        // 情形四：含空白的数值字符串转Long，toLong == 123456L
        toLong = NumberUtils.toLong("  123456  ");
        Assert.assertEquals(expected, toLong);

        // 情形五：空对象转Long，toLong == null
        toLong = NumberUtils.toLong(nullObject());
        Assert.assertNull(toLong);

        // 情形六：非数值字符串转Long，异常
        String message = null;
        try {
            toLong = NumberUtils.toLong("error");
            Assert.assertNull(toLong);
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"error\"", message);

        // 情形七：Long转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456L);
        Assert.assertEquals(expected, toLong);

        // 情形八：Integer转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456);
        Assert.assertEquals(expected, toLong);

        // 情形九：Double转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456.1D);
        Assert.assertEquals(expected, toLong);
    }

    private Object nullObject() {
        return null;
    }

    @Test
    public void testToLong() {
        long defaultValue = 100000L;

        // 情形一：数值字符串转Long，toLong == 123456L
        long toLong = NumberUtils.toLong("123456", defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形二：空字符串转Long，toLong == defaultValue
        toLong = NumberUtils.toLong("", defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形三：空白字符串转Long，toLong == defaultValue
        toLong = NumberUtils.toLong("   ", defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形四：含空白的数值字符串转Long，toLong == 123456L
        toLong = NumberUtils.toLong("  123456  ", defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形五：空对象转Long，toLong == defaultValue
        toLong = NumberUtils.toLong(null, defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形六：非数值字符串转Long，toLong == defaultValue
        toLong = NumberUtils.toLong("error", defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形七：Long转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456L, defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形八：Integer转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456, defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形九：Double转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456.1D, defaultValue);
        Assert.assertEquals(123456L, toLong);
    }

    @Test
    public void toInteger() {
        Integer expected = 1000000;
        Assert.assertEquals(expected, NumberUtils.toInteger("1000000"));
        Assert.assertEquals(expected, NumberUtils.toInteger(1000000));
        Assert.assertNull(NumberUtils.toInteger(""));
        Assert.assertNull(NumberUtils.toInteger(null));
    }

    @Test
    public void toShort() {
        Short expected = 10000;
        Assert.assertEquals(expected, NumberUtils.toShort("10000"));
        Assert.assertEquals(expected, NumberUtils.toShort(10000));
        Assert.assertNull(NumberUtils.toShort(""));
        Assert.assertNull(NumberUtils.toShort(null));
    }

    @Test
    public void toByte() {
        Byte expected = 100;
        Assert.assertEquals(expected, NumberUtils.toByte("100"));
        Assert.assertEquals(expected, NumberUtils.toByte(100));
        Assert.assertNull(NumberUtils.toByte(""));
        Assert.assertNull(NumberUtils.toByte(null));
    }

    @Test
    public void toDouble() {
        Double expected = 100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22D;
        Assert.assertEquals(expected, NumberUtils.toDouble("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22"));
        Assert.assertEquals(expected, NumberUtils.toDouble(100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22D));
        Assert.assertNull(NumberUtils.toDouble(""));
        Assert.assertNull(NumberUtils.toDouble(null));
    }

    @Test
    public void toFloat() {
        Float expected = 100000000000000000000000000000000000000.22F;
        Assert.assertEquals(expected, NumberUtils.toFloat("100000000000000000000000000000000000000.22"));
        Assert.assertEquals(expected, NumberUtils.toFloat(100000000000000000000000000000000000000.22F));
        Assert.assertNull(NumberUtils.toFloat(""));
        Assert.assertNull(NumberUtils.toFloat(null));
    }
}