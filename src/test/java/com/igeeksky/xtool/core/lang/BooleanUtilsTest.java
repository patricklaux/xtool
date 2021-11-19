package com.igeeksky.xtool.core.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class BooleanUtilsTest {

    @Test
    public void booleanValue() {
        Assert.assertTrue(BooleanUtils.booleanValue(Boolean.TRUE));
        Assert.assertNull(BooleanUtils.booleanValue(null));
        Assert.assertTrue(BooleanUtils.booleanValue("true"));
        Assert.assertNull(BooleanUtils.booleanValue(""));
    }

    @Test
    public void testBooleanValue() {
        Assert.assertFalse(BooleanUtils.booleanValue("false"));
    }
}