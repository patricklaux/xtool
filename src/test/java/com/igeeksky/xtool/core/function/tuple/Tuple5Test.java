/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.function.tuple;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple5Test {

    @Test
    public void getT1() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("a", tuple.getT1());
    }

    @Test
    public void getT2() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("b", tuple.getT2());
    }

    @Test
    public void getT3() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("c", tuple.getT3());
    }

    @Test
    public void getT4() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("d", tuple.getT4());
    }

    @Test
    public void getT5() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");

    }

    @Test
    public void mapT1() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("x", tuple.mapT1((t1) -> "x").getT1());
    }

    @Test
    public void mapT2() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("x", tuple.mapT2((t2) -> "x").getT2());
    }

    @Test
    public void mapT3() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("x", tuple.mapT3((t3) -> "x").getT3());
    }

    @Test
    public void mapT4() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("x", tuple.mapT4((t4) -> "x").getT4());
    }

    @Test
    public void mapT5() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertEquals("x", tuple.mapT5((t4) -> "x").getT5());
    }

    @Test
    public void size() {
        Assert.assertEquals(5, Tuples.of("a", "b", "c", "d", "e").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b", "c", "d", "e"};
        Object[] actual = Tuples.of("a", "b", "c", "d", "e").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple5<String, String, String, String, String> tuple = Tuples.of("a", "b", "c", "d", "e");
        Assert.assertNotEquals(tuple, Tuples.of("a"));
        Assert.assertEquals(tuple, Tuples.of("a", "b", "c", "d", "e"));
        Assert.assertNotEquals(tuple, Tuples.of("x", "b", "c", "d", "e"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "e", "c", "d", "e"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "b", "x", "d", "e"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "b", "c", "x", "e"));
        Assert.assertNotEquals(tuple, Tuples.of("a", "b", "c", "d", "x"));
        Assert.assertEquals(tuple, tuple);
    }

    @Test
    public void hashcode() {
        Assert.assertEquals(Tuples.of("a", "b", "c", "d", "e").hashCode(),
                Tuples.of("a", "b", "c", "d", "e").hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[a, b, c, d, e]", Tuples.of("a", "b", "c", "d", "e").toString());
    }
}