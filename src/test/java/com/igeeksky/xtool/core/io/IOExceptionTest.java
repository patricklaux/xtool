package com.igeeksky.xtool.core.io;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class IOExceptionTest {

    @Test
    public void test() {
        try {
            throw new IOException("aa");
        } catch (IOException e) {
            Assert.assertEquals("aa", e.getMessage());
        }
    }

    @Test
    public void test1() {
        try {
            throw new IOException(new java.io.IOException("aa"));
        } catch (IOException e) {
            Assert.assertEquals("java.io.IOException: aa", e.getMessage());
        }
    }

    @Test
    public void test2() {
        try {
            throw new IOException("io error", new java.io.IOException("aa"));
        } catch (IOException e) {
            Assert.assertEquals("io error", e.getMessage());
        }
    }
}