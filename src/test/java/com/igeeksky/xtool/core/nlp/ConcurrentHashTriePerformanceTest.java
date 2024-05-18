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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-15
 */
@Disabled
public class ConcurrentHashTriePerformanceTest {

    @Test
    public void get() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        long t1 = System.currentTimeMillis();
        String method = "ConcurrentArrayTrie-get\t";

        Random random = new Random();
        int size = 100000000;
        List<String> keys = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(9);
            if (length > 5) {
                char[] chars = new char[length];
                for (int j = 0; j < length; ) {
                    int index = random.nextInt(91);
                    if (index >= 65) {
                        char c = (char) index;
                        chars[j] = c;
                        ++j;
                    }
                }
                keys.add(new String(chars));
                i++;
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(method + "init-keys:\t" + (t2 - t1));

        Map<String, String> map = new HashMap<>(size);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            map.put(key, key);
            keys.set(i, String.valueOf(key.toCharArray()));
        }

        long t3 = System.currentTimeMillis();
        System.out.println(method + "  map-put:\t" + (t3 - t2));

        for (String key : keys) {
            trie.put(key, key);
        }

        long t4 = System.currentTimeMillis();
        System.out.println(method + " trie-put:\t" + (t4 - t3));

        List<String> result = new ArrayList<>(keys.size());
        for (String key : keys) {
            String value = map.get(key);
            if (null != value) {
                result.add(value);
            }
        }

        long t5 = System.currentTimeMillis();
        System.out.println(method + "  map-get:\t" + (t5 - t4));

        List<String> result2 = new ArrayList<>(keys.size());
        for (String key : keys) {
            String value = trie.get(key);
            if (null != value) {
                result2.add(value);
            }
        }

        long t6 = System.currentTimeMillis();
        System.out.println(method + " trie-get:\t" + (t6 - t5));

        System.out.println(method + "trie-size:\t" + result2.size());
        System.out.println(method + " map-size:\t" + result.size());
        Assertions.assertEquals(result.size(), result2.size());
    }

    @Test
    public void prefixMatchAll() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        long t1 = System.currentTimeMillis();
        String method = "ConcurrentArrayTrie-prefixMatchAll\t";

        Random random = new Random();
        int size = 1000000;
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(9);
            if (length > 5) {
                char[] chars = new char[length];
                for (int j = 0; j < length; ) {
                    int index = random.nextInt(90);
                    if (index >= 65) {
                        char c = (char) index;
                        chars[j] = c;
                        ++j;
                    }
                }
                String key = new String(chars);
                treeMap.put(key, key);
                i++;
            }
        }

        List<String> words = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; ) {
            int length = random.nextInt(15);
            if (length > 9) {
                char[] chars = new char[length];
                for (int j = 0; j < length; ) {
                    int index = random.nextInt(90);
                    if (index >= 65) {
                        char c = (char) index;
                        chars[j] = c;
                        ++j;
                    }
                }
                words.add(new String(chars));
                i++;
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(method + "init-keys:\t\t" + (t2 - t1));

        trie.putAll(treeMap);

        long t3 = System.currentTimeMillis();
        System.out.println(method + "putAll:\t\t\t" + (t3 - t2));

        for (String word : words) {
            trie.prefixMatchAll(word);
        }

        long t4 = System.currentTimeMillis();
        System.out.println(method + "prefixMatchAll:\t" + (t4 - t3));
    }

    @Test
    public void match() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        String method = "ConcurrentArrayTrie-match\t";
        long t1 = System.currentTimeMillis();

        Random random = new Random();
        int size = 5000000;
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(9);
            if (length > 5) {
                char[] chars = new char[length];
                for (int j = 0; j < length; ) {
                    int index = random.nextInt(256);
                    if (index >= 1) {
                        char c = (char) index;
                        chars[j] = c;
                        ++j;
                    }
                }
                String key = new String(chars);
                treeMap.put(key, key);
                i++;
            }
        }

        int charsLen = 1000000000;
        char[] chars = new char[charsLen];
        for (int i = 0; i < charsLen; ) {
            int index = random.nextInt(256);
            if (index >= 1) {
                chars[i] = (char) index;
                ++i;
            }
        }
        String text = new String(chars);

        long t2 = System.currentTimeMillis();
        System.out.println(method + "init-keys:\t" + (t2 - t1));

        trie.putAll(treeMap);

        long t3 = System.currentTimeMillis();
        System.out.println(method + "putAll:\t" + (t3 - t2));

        List<Found<String>> list = trie.match(text, false, false);
        long t4 = System.currentTimeMillis();
        System.out.println(method + "match:\t" + (t4 - t3));

        System.out.println(method + "size:\t\t" + list.size());
    }

    @Test
    public void matchAll() {
        String method = "ConcurrentArrayTrie-matchAll\t";
        long t1 = System.currentTimeMillis();

        Trie<String> trie = new ConcurrentHashTrie<>();
        Random random = new Random();
        int size = 5000000;
        TreeMap<String, String> pairs = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(9);
            if (length > 5) {
                char[] chars = new char[length];
                for (int j = 0; j < length; ) {
                    int index = random.nextInt(91);
                    if (index >= 65) {
                        char c = (char) index;
                        chars[j] = c;
                        ++j;
                    }
                }
                String key = new String(chars);
                pairs.put(key, key);
                i++;
            }
        }

        char[] chars = new char[100000000];
        for (int i = 0; i < 100000000; ) {
            int index = random.nextInt(91);
            if (index >= 65) {
                chars[i] = (char) index;
                ++i;
            }
        }
        String text = new String(chars);

        long t2 = System.currentTimeMillis();
        System.out.println(method + "init-keys:\t" + (t2 - t1));

        trie.putAll(pairs);
        long t3 = System.currentTimeMillis();
        System.out.println(method + "putAll\t\t" + (t3 - t2));

        List<Found<String>> matchAll = trie.matchAll(text, false, Integer.MAX_VALUE);
        long t4 = System.currentTimeMillis();
        System.out.println(method + "matchAll\t" + (t4 - t3));
        System.out.println(method + "size\t\t" + matchAll.size());
    }
}
