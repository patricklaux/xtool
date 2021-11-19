package com.igeeksky.xtool.core.nlp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class TrieConstantsTest {

    @Test
    public void test() {
        Assert.assertEquals(65535, TrieConstants.TABLE_MAX_MASK);
    }

}