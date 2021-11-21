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
        Assert.assertTrue(BooleanUtils.toBoolean(Boolean.TRUE));
        Assert.assertNull(BooleanUtils.toBoolean(null));
        Assert.assertTrue(BooleanUtils.toBoolean("true"));
        Assert.assertNull(BooleanUtils.toBoolean(""));
    }

    @Test
    public void testBooleanValue() {
        Assert.assertFalse(BooleanUtils.toBoolean("false"));
    }
}