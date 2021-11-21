package com.igeeksky.xtool.core.function.tuple;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class PairTest {

    @Test
    public void getKey() {
        Pair<String, String> pair = Pairs.of("key", "value");
        org.junit.Assert.assertEquals("key", pair.getKey());
    }

    @Test
    public void getValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assert.assertEquals("value", pair.getValue());
    }

    @Test
    public void hasValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assert.assertTrue(pair.hasValue());
    }

    @Test
    public void testHasValue() {
        Pair<String, String> pair = Pairs.emptyPair();
        Assert.assertFalse(pair.hasValue());
    }

    @Test
    public void testEquals() {
        Pair<String, String> pair = Pairs.emptyPair();
        Pair<String, String> pair2 = Pairs.emptyPair();
        Assert.assertEquals(pair, pair2);
    }

    @Test
    public void testEquals2() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<String, String> pair2 = Pairs.emptyPair();
        Assert.assertNotEquals(pair, pair2);
        Assert.assertNotEquals(pair, Tuples.of("key", "value"));
    }

    @Test
    public void testEquals3() {
        Pair<Pair<String, String>, Pair<String, String>> pair = Pairs.of(Pairs.of("key", "value"), Pairs.of("key", "value"));
        Pair<Pair<String, String>, Pair<String, String>> pair2 = Pairs.of(Pairs.of("key", "value"), Pairs.of("key", "value"));
        Assert.assertEquals(pair, pair2);
    }

    @Test
    public void hashcode() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<String, String> pair2 = Pairs.emptyPair();
        Assert.assertNotEquals(pair.hashCode(), pair2.hashCode());
    }

    @Test
    public void testToString() {
        Pair<Pair<String, String>, Pair<String, String>> pair = Pairs.of(Pairs.of("key", "value"), Pairs.of("key", "value"));
        Assert.assertEquals("{\"key\":{\"key\":\"key\", \"value\":\"value\"}, \"value\":{\"key\":\"key\", \"value\":\"value\"}}", pair.toString());
    }


}