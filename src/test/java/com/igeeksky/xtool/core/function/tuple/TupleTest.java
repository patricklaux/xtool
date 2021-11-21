package com.igeeksky.xtool.core.function.tuple;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class TupleTest {

    @Test
    public void iterator() {
        String[] expected = new String[]{"a", "b", "c", "d", "e"};
        Iterator<Object> iterator = Tuples.of("a", "b", "c", "d", "e").iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(expected[i++], iterator.next());
        }
    }
}