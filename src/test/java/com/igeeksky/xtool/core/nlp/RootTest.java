package com.igeeksky.xtool.core.nlp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class RootTest {

    @Test
    public void iterator() {
        Root<String> root = new Root<>('0');
        root.addChild('a', new LinkedNodeCreator<>(), null);

        Iterator<Node<String>> iterator = root.iterator();
        boolean hasNext = iterator.hasNext();
        Assert.assertTrue(hasNext);
        iterator.next();
        hasNext = iterator.hasNext();
        Assert.assertFalse(hasNext);
        Assert.assertNull(iterator.next());
    }
}