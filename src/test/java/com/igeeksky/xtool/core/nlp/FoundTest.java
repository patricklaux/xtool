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