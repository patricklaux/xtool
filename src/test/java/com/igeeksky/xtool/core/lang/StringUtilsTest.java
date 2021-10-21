package com.igeeksky.xtool.core.lang;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class StringUtilsTest {

    @Test
    public void isEmpty() {
        Assert.assertTrue(StringUtils.isEmpty("  "));
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertFalse(StringUtils.isEmpty("  a  "));
    }

    @Test
    public void isNotEmpty() {
    }

    @Test
    public void trim() {
    }

    @Test
    public void toUpperCase() {
    }

    @Test
    public void toLowerCase() {
    }

    @Test
    public void replaceFirstToLowerCase() {
    }
}