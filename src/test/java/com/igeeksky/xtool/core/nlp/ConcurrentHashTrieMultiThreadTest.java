package com.igeeksky.xtool.core.nlp;

import org.junit.Assert;

/**
 * 多线程测试
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-18
 */
public class ConcurrentHashTrieMultiThreadTest {

    private static final Trie<String> TRIE = new ConcurrentHashTrie<>();
    private static final String[] ARRAY = new String[]{"aaaa", "bbbbb", "ccccc"};
    private static volatile boolean empty = true;
    private static final int MAX_LOOP = 1000000;

    public static void main(String[] args) {
        new Thread(() -> {
            int i = 0;
            while (true) {
                if (empty) {
                    String oldVal = TRIE.put(ARRAY[0], ARRAY[0]);
                    empty = false;
                    Assert.assertNull(oldVal);
                    if (i++ > MAX_LOOP) {
                        break;
                    }
                }

            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                if (!empty) {
                    int size = TRIE.size();
                    Assert.assertEquals(1, size);
                    String oldVal = TRIE.remove(ARRAY[0]);
                    empty = true;
                    Assert.assertNotNull(oldVal);
                    if (i++ > MAX_LOOP) {
                        break;
                    }
                }
            }
        }).start();
    }
}
