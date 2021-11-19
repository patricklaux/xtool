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
        Assert.assertEquals(expected, NumberUtils.longValue("10000000000000"));
        Assert.assertEquals(expected, NumberUtils.longValue(10000000000000L));
        Assert.assertNull(NumberUtils.longValue(""));
        Assert.assertNull(NumberUtils.longValue(null));
    }

    @Test
    public void intValue() {
        Integer expected = 1000000;
        Assert.assertEquals(expected, NumberUtils.intValue("1000000"));
        Assert.assertEquals(expected, NumberUtils.intValue(1000000));
        Assert.assertNull(NumberUtils.intValue(""));
        Assert.assertNull(NumberUtils.intValue(null));
    }

    @Test
    public void shortValue() {
        Short expected = 10000;
        Assert.assertEquals(expected, NumberUtils.shortValue("10000"));
        Assert.assertEquals(expected, NumberUtils.shortValue(10000));
        Assert.assertNull(NumberUtils.shortValue(""));
        Assert.assertNull(NumberUtils.shortValue(null));
    }

    @Test
    public void byteValue() {
        Byte expected = 100;
        Assert.assertEquals(expected, NumberUtils.byteValue("100"));
        Assert.assertEquals(expected, NumberUtils.byteValue(100));
        Assert.assertNull(NumberUtils.byteValue(""));
        Assert.assertNull(NumberUtils.byteValue(null));
    }

    @Test
    public void doubleValue() {
        Double expected = 100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22D;
        Assert.assertEquals(expected, NumberUtils.doubleValue("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22"));
        Assert.assertEquals(expected, NumberUtils.doubleValue(100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.22D));
        Assert.assertNull(NumberUtils.doubleValue(""));
        Assert.assertNull(NumberUtils.doubleValue(null));
    }

    @Test
    public void floatValue() {
        Float expected = 100000000000000000000000000000000000000.22F;
        Assert.assertEquals(expected, NumberUtils.floatValue("100000000000000000000000000000000000000.22"));
        Assert.assertEquals(expected, NumberUtils.floatValue(100000000000000000000000000000000000000.22F));
        Assert.assertNull(NumberUtils.floatValue(""));
        Assert.assertNull(NumberUtils.floatValue(null));
    }
}