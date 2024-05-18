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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class Tuple2Test {

    @Test
    public void getT1() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assertions.assertEquals("a", tuple.getT1());
    }

    @Test
    public void getT2() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assertions.assertEquals("b", tuple.getT2());
    }

    @Test
    public void mapT1() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assertions.assertEquals("b", tuple.mapT1((t1) -> "b").getT1());
    }

    @Test
    public void mapT2() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assertions.assertEquals("c", tuple.mapT2((t2) -> "c").getT2());
    }

    @Test
    public void size() {
        Assertions.assertEquals(2, Tuples.of("a", "b").size());
    }

    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b"};
        Object[] actual = Tuples.of("a", "b").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assertions.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void equals() {
        Tuple2<String, String> tuple = Tuples.of("a", "b");
        Assertions.assertNotEquals(tuple, Tuples.of("a", "c"));
        Assertions.assertEquals(tuple, Tuples.of("a", "b"));
        Assertions.assertNotEquals(tuple, Tuples.of("b", "b"));
        Assertions.assertEquals(tuple, Tuples.of("a", "b"));
    }

    @Test
    public void hashcode() {
        Assertions.assertEquals(Tuples.of("a", "b").hashCode(), Tuples.of("a", "b").hashCode());
    }

    @Test
    public void testToString() {
        Assertions.assertEquals("[a, b]", Tuples.of("a", "b").toString());
    }
}