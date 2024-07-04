package com.igeeksky.xtool.core.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/6/21
 */
class SetsTest {

    @Test
    public void newHashSet() {
        HashSet<String> set = Sets.newHashSet();
        Assertions.assertEquals(0, set.size());
    }

    @Test
    public void testNewHashSet() {
        HashSet<String> set = Sets.newHashSet(8);
        Assertions.assertEquals(0, set.size());
    }

    @Test
    public void newLinkedHashSet() {
        LinkedHashSet<String> set = Sets.newLinkedHashSet();
        Assertions.assertEquals(0, set.size());
    }

    @Test
    public void testNewLinkedHashSet() {
        LinkedHashSet<String> set = Sets.newLinkedHashSet(8);
        Assertions.assertEquals(0, set.size());
    }

}