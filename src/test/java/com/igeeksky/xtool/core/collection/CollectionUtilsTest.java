package com.igeeksky.xtool.core.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class CollectionUtilsTest {

    @Test
    public void isEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(nullList()));
        Assert.assertTrue(CollectionUtils.isEmpty(emptyList()));
        Assert.assertFalse(CollectionUtils.isEmpty(singletonList()));
    }

    @Test
    public void isNotEmpty() {
        Assert.assertFalse(CollectionUtils.isNotEmpty(nullList()));
        Assert.assertFalse(CollectionUtils.isNotEmpty(emptyList()));
        Assert.assertTrue(CollectionUtils.isNotEmpty(singletonList()));
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

    private static List<String> emptyList() {
        return Collections.emptyList();
    }

    private static List<String> singletonList() {
        return Collections.singletonList("a");
    }
}