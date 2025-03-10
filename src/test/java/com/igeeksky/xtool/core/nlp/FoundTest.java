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


package com.igeeksky.xtool.core.nlp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class FoundTest {

    @Test
    public void getStart() {
        Found<String> found = new Found<>(0, 2, null, "ac");
        Assertions.assertEquals(0, found.begin());
    }

    @Test
    public void getEnd() {
        Found<String> found = new Found<>(0, 2, null, "ac");
        Assertions.assertEquals(2, found.end());
    }

    @Test
    public void testEquals() {
        LinkedNode<String> node = new LinkedNode<>('c', "ac", 0, null);
        LinkedNode<String> node2 = new LinkedNode<>('c', "ac", 0, null);
        Found<String> found = new Found<>(0, 1, null, "ac");
        Found<String> found2 = new Found<>(1, 2, null, "ac");
        Found<String> found3 = new Found<>(1, 2, null, "ac");
        Found<String> found4 = new Found<>(1, 3, "ac", "ac");
        Found<String> found5 = new Found<>(1, 3, "ab", "ab");
        Assertions.assertEquals(node, node2);
        Assertions.assertNotEquals(found, found2);
        Assertions.assertNotEquals(found3, found4);
        Assertions.assertNotEquals(found4, found5);
        Assertions.assertEquals(found2, found3);
    }

    @Test
    public void testHashCode() {
        Found<String> found = new Found<>(0, 1, null, "ac");
        Assertions.assertEquals(3137, found.hashCode());
    }
}