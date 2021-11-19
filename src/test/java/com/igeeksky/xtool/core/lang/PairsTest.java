package com.igeeksky.xtool.core.lang;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class PairsTest {

    @Test
    public void of() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assert.assertEquals("key", pair.getKey());
        Assert.assertEquals("value", pair.getValue());
    }

    @Test
    public void emptyPair() {
        Pair<String, String> pair = Pairs.emptyPair();
        Assert.assertNull(pair.getKey());
        Assert.assertNull(pair.getValue());
    }
}