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
