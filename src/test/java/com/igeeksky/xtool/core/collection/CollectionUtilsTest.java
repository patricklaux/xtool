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


package com.igeeksky.xtool.core.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class CollectionUtilsTest {

    @Test
    public void isEmpty() {
        // 场景一：集合不含元素，isEmpty == true
        boolean isEmpty = CollectionUtils.isEmpty(Collections.emptyList());
        Assert.assertTrue(isEmpty);

        // 场景二：集合为空对象，isEmpty == true
        isEmpty = CollectionUtils.isEmpty(nullList());
        Assert.assertTrue(isEmpty);

        // 场景三：集合含有至少一个元素，isEmpty == false
        isEmpty = CollectionUtils.isEmpty(Collections.singletonList("a"));
        Assert.assertFalse(isEmpty);
    }

    @Test
    public void isNotEmpty() {
        // 场景一：集合不含元素，isNotEmpty == false
        boolean isNotEmpty = CollectionUtils.isNotEmpty(Collections.emptyList());
        Assert.assertFalse(isNotEmpty);

        // 场景二：集合为空对象，isNotEmpty == false
        isNotEmpty = CollectionUtils.isNotEmpty(nullList());
        Assert.assertFalse(isNotEmpty);

        // 场景三：集合含有至少一个元素，isNotEmpty == true
        isNotEmpty = CollectionUtils.isNotEmpty(Collections.singletonList("a"));
        Assert.assertTrue(isNotEmpty);
    }

    @Test
    public void concat() {
        Collection<String> concat = CollectionUtils.concat(new ArrayList<>(2), singletonList(), singletonList());
        Assert.assertEquals("[a, a]", concat.toString());
    }

    @Test
    public void testConcat() {
        Collection<String> concat = CollectionUtils.concat(new ArrayList<>(2));
        Assert.assertEquals("[]", concat.toString());
    }

    private static List<String> nullList() {
        return null;
    }

    private static List<String> singletonList() {
        return Collections.singletonList("a");
    }

    @Test
    public void newHashSet() {
        HashSet<String> set = CollectionUtils.newHashSet();
        Assert.assertEquals(0, set.size());
    }

    @Test
    public void testNewHashSet() {
        HashSet<String> set = CollectionUtils.newHashSet(8);
        Assert.assertEquals(0, set.size());
    }

    @Test
    public void newLinkedHashSet() {
        LinkedHashSet<String> set = CollectionUtils.newLinkedHashSet();
        Assert.assertEquals(0, set.size());
    }

    @Test
    public void testNewLinkedHashSet() {
        LinkedHashSet<String> set = CollectionUtils.newLinkedHashSet(8);
        Assert.assertEquals(0, set.size());
    }
}