package com.igeeksky.xtool.core.function;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple4Test {

    @Test
    public void getT1() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("a", tuple.getT1());
    }

    @Test
    public void getT2() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("b", tuple.getT2());
    }

    @Test
    public void getT3() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("c", tuple.getT3());
    }

    @Test
    public void getT4() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("d", tuple.getT4());
    }

    @Test
    public void mapT1() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("x", tuple.mapT1((t1) -> "x").getT1());
    }

    @Test
    public void mapT2() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("x", tuple.mapT2((t2) -> "x").getT2());
    }

    @Test
    public void mapT3() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("x", tuple.mapT3((t3) -> "x").getT3());
    }

    @Test
    public void mapT4() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertEquals("x", tuple.mapT4((t4) -> "x").getT4());
    }

    @Test
    public void size() {
        Assert.assertEquals(4, Tuples.of("a", "b", "c", "d").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b", "c", "d"};
        Object[] actual = Tuples.of("a", "b", "c", "d").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple4<String, String, String, String> tuple = Tuples.of("a", "b", "c", "d");
        Assert.assertNotEquals(tuple, Tuples.of("a"));
        Assert.assertEquals(tuple, Tuples.of("a", "b", "c", "d"));
        Assert.assertNotEquals(tuple, Tuples.of("b", "b", "c", "d"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "c", "c", "d"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "b", "d", "e"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "b", "c", "e"));
        Assert.assertEquals(tuple, tuple);
    }

    @Test
    public void hashcode() {
        Assert.assertEquals(Tuples.of("a", "b", "c", "d").hashCode(), Tuples.of("a", "b", "c", "d").hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[a, b, c, d]", Tuples.of("a", "b", "c", "d").toString());
    }
}