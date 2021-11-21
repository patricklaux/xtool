package com.igeeksky.xtool.core.function.tuple;

import com.igeeksky.xtool.core.function.tuple.Tuple1;
import com.igeeksky.xtool.core.function.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple1Test {

    @Test
    public void getT1() {
        Tuple1<String> tuple1 = Tuples.of("a");
        Assert.assertEquals("a", tuple1.getT1());
    }

    @Test
    public void mapT1() {
        Tuple1<String> tuple1 = Tuples.of("a");
        Assert.assertEquals("b", tuple1.mapT1((t1) -> "b").getT1());
    }

    @Test
    public void size() {
        Assert.assertEquals(1, Tuples.of("a").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a"};
        Object[] actual = Tuples.of("a").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple1<String> tuple1 = Tuples.of("a");
        Assert.assertEquals(tuple1, Tuples.of("a"));
        Assert.assertNotEquals(tuple1, Tuples.of("a", "b"));
        Assert.assertEquals(tuple1, tuple1);
    }

    @Test
    public void hashcode() {
        Assert.assertEquals(Tuples.of("a").hashCode(), Tuples.of("a").hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[a]", Tuples.of("a").toString());
    }
}