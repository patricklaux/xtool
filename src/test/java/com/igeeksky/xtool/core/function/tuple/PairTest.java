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
 * @since 1.0.1 2021-11-20
 */
public class PairTest {

    @Test
    public void getKey() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assertions.assertEquals("key", pair.key());
    }

    @Test
    public void getValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assertions.assertEquals("value", pair.value());
    }

    @Test
    public void hasValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assertions.assertTrue(pair.hasValue());
    }

    @Test
    public void testHasValue() {
        Pair<String, String> pair = Pairs.emptyPair();
        Assertions.assertFalse(pair.hasValue());
    }

    @Test
    public void testEquals() {
        Pair<String, String> pair = Pairs.emptyPair();
        Pair<String, String> pair2 = Pairs.emptyPair();
        Assertions.assertEquals(pair, pair2);
    }

    @Test
    public void testEquals2() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<String, String> pair2 = Pairs.emptyPair();
        Assertions.assertNotEquals(pair, pair2);
        Assertions.assertNotEquals(pair, Tuples.of("key", "value"));
    }

    @Test
    public void testEquals3() {
        Pair<Pair<String, String>, Pair<String, String>> pair = Pairs.of(Pairs.of("key", "value"), Pairs.of("key", "value"));
        Pair<Pair<String, String>, Pair<String, String>> pair2 = Pairs.of(Pairs.of("key", "value"), Pairs.of("key", "value"));
        Assertions.assertEquals(pair, pair2);
    }

    @Test
    public void hashcode() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<String, String> pair2 = Pairs.emptyPair();
        Assertions.assertNotEquals(pair.hashCode(), pair2.hashCode());
    }

    @Test
    public void testToString() {
        Pair<Pair<String, String>, Pair<String, String>> pair = Pairs.of(Pairs.of("key", "value"), Pairs.of("key", "value"));
        Assertions.assertEquals("{\"key\":{\"key\":\"key\", \"value\":\"value\"}, \"value\":{\"key\":\"key\", \"value\":\"value\"}}", pair.toString());
    }

    @Test
    public void mapKey() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<Integer, String> newPair = pair.mapKey((k) -> 1);
        Assertions.assertEquals(Integer.valueOf(1), newPair.key());
    }

    @Test
    public void mapValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<String, Integer> newPair = pair.mapValue((k) -> 1);
        Assertions.assertEquals(Integer.valueOf(1), newPair.value());
    }

    @Test
    public void hasKey() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assertions.assertTrue(pair.hasKey());
    }
}