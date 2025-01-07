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


package com.igeeksky.xtool.core.tuple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple3Test {

    @Test
    public void getT1() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals("a", tuple.getT1());
    }

    @Test
    public void getT2() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals("b", tuple.getT2());
    }

    @Test
    public void getT3() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals("c", tuple.getT3());
    }

    @Test
    public void mapT1() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals("x", tuple.mapT1((t1) -> "x").getT1());
    }

    @Test
    public void mapT2() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals("x", tuple.mapT2((t2) -> "x").getT2());
    }

    @Test
    public void mapT3() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals("x", tuple.mapT3((t3) -> "x").getT3());
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, Tuples.of("a", "b", "c").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b", "c"};
        Object[] actual = Tuples.of("a", "b", "c").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assertions.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assertions.assertEquals(tuple, Tuples.of("a", "b", "c"));
        Assertions.assertNotEquals(tuple, Tuples.of("b", "b", "c"));
        Assertions.assertNotEquals(tuple, Tuples.of("a", "c", "c"));
    }

    @Test
    public void hashcode() {
        Assertions.assertEquals(Tuples.of("a", "b", "c").hashCode(), Tuples.of("a", "b", "c").hashCode());
    }

    @Test
    public void testToString() {
        Assertions.assertEquals("[a, b, c]", Tuples.of("a", "b", "c").toString());
    }
}