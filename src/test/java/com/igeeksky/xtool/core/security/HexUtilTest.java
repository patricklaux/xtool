package com.igeeksky.xtool.core.security;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class HexUtilTest {

    @Test
    public void encodeHexString() {
        byte[] bytes = new byte[]{110, 111, 112, 113, 114, 115, 116, 117};
        Assert.assertEquals("6e6f707172737475", HexUtil.encodeHexString(bytes, true));
    }

    @Test
    public void testEncodeHexString() {
        byte[] bytes = new byte[]{110, 111, 112, 113, 114, 115, 116, 117};
        Assert.assertEquals("6E6F707172737475", HexUtil.encodeHexString(bytes, false));
    }

    @Test
    public void testEncodeHexString1() {
        byte[] bytes = new byte[0];
        Assert.assertEquals("", HexUtil.encodeHexString(bytes, false));
    }

    @Test
    public void testEncodeHexString2() {
        Assert.assertNull(HexUtil.encodeHexString(nullBytes(), false));
    }

    private byte[] nullBytes() {
        return null;
    }
}