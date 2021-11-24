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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class FoundTest {

    @Test
    public void getStart() {
        Found<String> found = new Found<>(0, 2, new LinkedNode<>('c', "ac", 0, null));
        Assert.assertEquals(0, found.getStart());
    }

    @Test
    public void getEnd() {
        Found<String> found = new Found<>(0, 2, new LinkedNode<>('c', "ac", 0, null));
        Assert.assertEquals(2, found.getEnd());
    }

    @Test
    public void getNode() {
        LinkedNode<String> node = new LinkedNode<>('c', "ac", 0, null);
        Found<String> found = new Found<>(0, 2, node);
        Assert.assertEquals(node, found.getNode());
    }

    @Test
    public void testEquals() {
        LinkedNode<String> node = new LinkedNode<>('c', "ac", 0, null);
        Found<String> found = new Found<>(0, 1, node);
        Found<String> found2 = new Found<>(1, 2, node);
        Found<String> found3 = new Found<>(1, 2, node);
        Found<String> found4 = new Found<>(1, 3, node);
        Assert.assertEquals(found, found);
        Assert.assertNotEquals(found, node);
        Assert.assertNotEquals(found, found2);
        Assert.assertNotEquals(found3, found4);
        Assert.assertEquals(found2, found3);
    }

    @Test
    public void testHashCode() {
        LinkedNode<String> node = new LinkedNode<>('c', "ac", 0, null);
        Found<String> found = new Found<>(0, 1, node);
        Assert.assertEquals(3137, found.hashCode());
    }
}