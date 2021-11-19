package com.igeeksky.xtool.core.function;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple2Test {

    @Test
    public void getT1() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assert.assertEquals("a", tuple.getT1());
    }

    @Test
    public void getT2() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assert.assertEquals("b", tuple.getT2());
    }

    @Test
    public void mapT1() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assert.assertEquals("b", tuple.mapT1((t1) -> "b").getT1());
    }

    @Test
    public void mapT2() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assert.assertEquals("c", tuple.mapT2((t2) -> "c").getT2());
    }

    @Test
    public void size() {
        Assert.assertEquals(2, Tuples.of("a", "b").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b"};
        Object[] actual = Tuples.of("a", "b").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assert.assertNotEquals(tuple, Tuples.of("a"));
        Assert.assertEquals(tuple, Tuples.of("a", "b"));
        Assert.assertNotEquals(tuple, Tuples.of("b", "b"));
        Assert.assertEquals(tuple, tuple);
    }

    @Test
    public void hashcode() {
        Assert.assertEquals(Tuples.of("a", "b").hashCode(), Tuples.of("a", "b").hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[a, b]", Tuples.of("a", "b").toString());
    }
}