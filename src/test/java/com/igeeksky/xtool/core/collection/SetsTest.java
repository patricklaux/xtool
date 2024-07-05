package com.igeeksky.xtool.core.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    public void testNewHashSet1() {
        int size = 5;
        ArrayList<Integer> list = getArrayList(size);
        HashSet<Integer> set = Sets.newHashSet(list);
        Assertions.assertEquals(size, set.size());
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

    @Test
    public void testNewLinkedHashSet1() {
        int size = 5;
        ArrayList<Integer> list = getArrayList(size);
        LinkedHashSet<Integer> set = Sets.newLinkedHashSet(list);
        Assertions.assertEquals(size, set.size());
    }

    @Test
    void newConcurrentHashSet() {
        ConcurrentHashSet<String> set = Sets.newConcurrentHashSet();
        Assertions.assertEquals(0, set.size());
    }

    @Test
    void testNewConcurrentHashSet() {
        ConcurrentHashSet<String> set = Sets.newConcurrentHashSet(1);
        Assertions.assertEquals(0, set.size());
    }

    @Test
    void testNewConcurrentHashSet1() {
        int size = 5;
        ArrayList<Integer> list = getArrayList(size);
        ConcurrentHashSet<Integer> set = Sets.newConcurrentHashSet(list);
        Assertions.assertEquals(size, set.size());
    }

    private static ArrayList<Integer> getArrayList(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

}