package com.igeeksky.xtool.core.nlp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-15
 */
public class ConcurrentHashTriePerformanceTest {

    @Test
    public void get() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        long t1 = System.currentTimeMillis();
        System.out.println("get\tt1:\t" + t1);

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
        System.out.println("get\tt2:\t" + (t2 - t1));

        Map<String, String> map = new HashMap<>(size);
        for (String key : keys) {
            map.put(key, key);
        }

        long t3 = System.currentTimeMillis();
        System.out.println("get\tt3:\t" + (t3 - t2));


        for (String key : keys) {
            trie.put(key, key);
        }

        long t4 = System.currentTimeMillis();
        System.out.println("get\tt4:\t" + (t4 - t3));

        List<String> result = new ArrayList<>(size);
        for (String key : keys) {
            String value = map.get(key);
            if (null != value) {
                result.add(value);
            }
        }

        System.out.println("map-size\t\t\t\t" + result.size());

        long t5 = System.currentTimeMillis();
        System.out.println("get\tt5:\t" + (t5 - t4));

        List<String> result2 = new ArrayList<>(size);
        for (String key : keys) {
            String value = trie.get(key);
            if (null != value) {
                result2.add(value);
            }
        }

        System.out.println("trie-size\t\t\t\t" + result2.size());

        long t6 = System.currentTimeMillis();
        System.out.println("get\tt6:\t" + (t6 - t5));
        Assert.assertEquals(result.size(), result2.size());
    }

    @Test
    public void matchAll() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        long t1 = System.currentTimeMillis();
        System.out.println("matchAll\tt1:" + t1);

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

        long t2 = System.currentTimeMillis();
        System.out.println("matchAll\tt2:" + (t2 - t1));

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

        long t3 = System.currentTimeMillis();
        System.out.println("matchAll\tt3:" + (t3 - t2));

        trie.putAll(treeMap);

        long t4 = System.currentTimeMillis();
        System.out.println("matchAll\tt4:" + (t4 - t3));

        for (String word : words) {
            trie.matchAll(word);
        }

        long t5 = System.currentTimeMillis();
        System.out.println("matchAll\tt5:" + (t5 - t4));
    }

    @Test
    public void contains() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        long t1 = System.currentTimeMillis();
        System.out.println("contains\tt1:" + t1);

        Random random = new Random();
        int size = 5000000;
        TreeMap<String, String> pairs = new TreeMap<>();
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
                pairs.put(key, key);
                i++;
            }
        }

        trie.putAll(pairs);
        long t2 = System.currentTimeMillis();
        System.out.println("contains\tt2:" + (t2 - t1));

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
        long t3 = System.currentTimeMillis();
        System.out.println("contains\tt3:" + (t3 - t2));

        List<Found<String>> list = trie.contains(text, false, false);
        System.out.println(list.size());
        long t4 = System.currentTimeMillis();
        System.out.println("contains\tt4:" + (t4 - t3));
    }

    @Test
    public void containsAll() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        long t1 = System.currentTimeMillis();
        System.out.println("containsAll\tt1:\t" + t1);

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

        long t2 = System.currentTimeMillis();
        System.out.println("containsAll\tt2:\t" + (t2 - t1));

        char[] chars = new char[100000000];
        for (int i = 0; i < 100000000; ) {
            int index = random.nextInt(91);
            if (index >= 65) {
                chars[i] = (char) index;
                ++i;
            }
        }
        String text = new String(chars);

        long t3 = System.currentTimeMillis();
        System.out.println("containsAll\tt3:\t" + (t3 - t2));

        trie.putAll(pairs);

        long t4 = System.currentTimeMillis();
        System.out.println("containsAll\tt4:\t" + (t4 - t3));

        List<Found<String>> list2 = trie.containsAll(text, false, Integer.MAX_VALUE);
        System.out.println("size\t\t\t\t" + list2.size());

        long t5 = System.currentTimeMillis();
        System.out.println("containsAll\tt5:\t" + (t5 - t4));
    }
}
