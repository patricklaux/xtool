package com.igeeksky.xtool.core.function.tuple;

import com.igeeksky.xtool.core.function.tuple.Tuple3;
import com.igeeksky.xtool.core.function.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple3Test {

    @Test
    public void getT1() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("a", tuple.getT1());
    }

    @Test
    public void getT2() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("b", tuple.getT2());
    }

    @Test
    public void getT3() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("c", tuple.getT3());
    }

    @Test
    public void mapT1() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("x", tuple.mapT1((t1) -> "x").getT1());
    }

    @Test
    public void mapT2() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("x", tuple.mapT2((t2) -> "x").getT2());
    }

    @Test
    public void mapT3() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("x", tuple.mapT3((t3) -> "x").getT3());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, Tuples.of("a", "b", "c").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b", "c"};
        Object[] actual = Tuples.of("a", "b", "c").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertNotEquals(tuple, Tuples.of("a"));
        Assert.assertEquals(tuple, Tuples.of("a", "b", "c"));
        Assert.assertNotEquals(tuple, Tuples.of("b", "b", "c"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "c", "c"));
        Assert.assertEquals(tuple, tuple);
    }

    @Test
    public void hashcode() {
        Assert.assertEquals(Tuples.of("a", "b", "c").hashCode(), Tuples.of("a", "b", "c").hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[a, b, c]", Tuples.of("a", "b", "c").toString());
    }
}