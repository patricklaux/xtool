package com.igeeksky.xtool.core.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-19
 */
public class IOUtilsTest {

    @Test
    public void close() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        InputStream in = new ByteArrayInputStream(bytes);
        IOUtils.close(in);
    }

    @Test
    public void testClose() {
        IOUtils.close(null);
    }

    @Test
    public void testClose2() {
        try {
            IOUtils.close(new InputStream() {
                @Override
                public int read() {
                    return 0;
                }

                @Override
                public void close() throws IOException {
                    throw new IOException();
                }
            });
        } catch (Exception e) {
            Assert.assertTrue(e instanceof com.igeeksky.xtool.core.io.IOException);
        }
    }

    @Test
    public void copy() {
        byte[] expected = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        InputStream in = new ByteArrayInputStream(expected);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        long num = IOUtils.copy(in, out);
        Assert.assertEquals(8, num);

        byte[] actual = out.toByteArray();
        int maxLen = Math.max(expected.length, actual.length);
        for (int i = 0; i < maxLen; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testCopy() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(new InputStream() {
                @Override
                public int read() throws IOException {
                    throw new IOException();
                }
            }, out);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof com.igeeksky.xtool.core.io.IOException);
        }
    }
}