package com.igeeksky.xtool.core.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class NumberUtilsTest {

    @Test
    public void longValue() {
        Long expected = 10000000000000L;
        Assert.assertEquals(expected, NumberUtils.toLong("10000000000000"));
        Assert.assertEquals(expected, NumberUtils.toLong(10000000000000L));
        Assert.assertNull(NumberUtils.toLong(""));
        Assert.assertNull(NumberUtils.toLong(null));
    }

    @Test
    public void intValue() {
        Integer expected = 1000000;
        Assert.assertEquals(expected, NumberUtils.toInteger("1000000"));
        Assert.assertEquals(expected, NumberUtils.toInteger(1000000));
        Assert.assertNull(NumberUtils.toInteger(""));
        Assert.assertNull(NumberUtils.toInteger(null));
    }

    @Test
    public void shortValue() {
        Short expected = 10000;
        Assert.assertEquals(expected, NumberUtils.toShort("10000"));
        Assert.assertEquals(expected, NumberUtils.toShort(10000));
        Assert.assertNull(NumberUtils.toShort(""));
        Assert.assertNull(NumberUtils.toShort(null));
    }

    @Test
    public void byteValue() {
        Byte expected = 100;
        Assert.assertEquals(expected, NumberUtils.toByte("100"));
        Assert.assertEquals(expected, NumberUtils.toByte(100));
        Assert.assertNull(NumberUtils.toByte(""));
        Assert.assertNull(NumberUtils.toByte(null));
    }

    @Test
    public void doubleValue() {
        Double expected = 100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22D;
        Assert.assertEquals(expected, NumberUtils.toDouble("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22"));
        Assert.assertEquals(expected, NumberUtils.toDouble(100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22D));
        Assert.assertNull(NumberUtils.toDouble(""));
        Assert.assertNull(NumberUtils.toDouble(null));
    }

    @Test
    public void floatValue() {
        Float expected = 100000000000000000000000000000000000000.22F;
        Assert.assertEquals(expected, NumberUtils.toFloat("100000000000000000000000000000000000000.22"));
        Assert.assertEquals(expected, NumberUtils.toFloat(100000000000000000000000000000000000000.22F));
        Assert.assertNull(NumberUtils.toFloat(""));
        Assert.assertNull(NumberUtils.toFloat(null));
    }
}