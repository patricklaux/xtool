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

import com.igeeksky.xtool.core.function.tuple.Tuple2;
import com.igeeksky.xtool.core.function.tuple.Tuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;

/**
 * @author Patrick.Lau
 * @since 1.0.4 2021-11-23
 */
public class ConcurrentHashTrie2Test {

    @Test
    public void putAndGet() {
        // 与HashMap 比较方法结果
        Trie<Integer> trie = new ConcurrentHashTrie<>();
        Map<String, Integer> map = new HashMap<>(8);

        String key = "abc";
        Integer value = 100;

        // 首次 put，旧值都为 null
        Integer triePut = trie.put(key, value);
        Integer mapPut = map.put(key, value);
        Assertions.assertNull(triePut);
        Assertions.assertEquals(triePut, mapPut);

        // 再次 put，旧值都为 100
        triePut = trie.put(key, value);
        mapPut = map.put(key, value);
        Assertions.assertNotNull(triePut);
        Assertions.assertEquals(value, mapPut);
        Assertions.assertEquals(triePut, mapPut);

        // get，返回值都为 100
        Integer trieGet = trie.put(key, value);
        Integer mapGet = map.put(key, value);
        Assertions.assertEquals(value, mapGet);
        Assertions.assertEquals(trieGet, mapGet);
    }

    @Test
    public void putAllAndRemove() {
        // 与HashMap 比较方法结果
        Trie<Integer> trie = new ConcurrentHashTrie<>();
        Map<String, Integer> map = new HashMap<>(8);

        TreeMap<String, Integer> keyValues = new TreeMap<>();
        String prefix = "abc";
        for (int i = 0; i < 8; i++) {
            String key = prefix + i;
            keyValues.put(key, i);
        }

        trie.putAll(keyValues);
        map.putAll(keyValues);

        for (int i = 0; i < 8; i++) {
            String key = prefix + i;
            int trieGet = trie.get(key);
            int mapGet = map.get(key);
            Assertions.assertEquals(i, trieGet);
            Assertions.assertEquals(trieGet, mapGet);
        }

        for (int i = 0; i < 8; i++) {
            String key = prefix + i;
            int trieGet = trie.remove(key);
            int mapGet = map.remove(key);
            Assertions.assertEquals(i, trieGet);
            Assertions.assertEquals(trieGet, mapGet);
        }
    }

    @Test
    public void mapMethod() {
        // 思维导图中的方法示例
        // 测试 put, contains, size, isEmpty, clear 方法
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        boolean contains = trie.contains("abcd");
        Assertions.assertTrue(contains);
        int size = trie.size();
        Assertions.assertEquals(5, size);
        boolean isEmpty = trie.isEmpty();
        Assertions.assertFalse(isEmpty);

        trie.remove("abcd");
        contains = trie.contains("abcd");
        Assertions.assertFalse(contains);
        size = trie.size();
        Assertions.assertEquals(4, size);
        isEmpty = trie.isEmpty();
        Assertions.assertFalse(isEmpty);

        trie.clear();
        size = trie.size();
        Assertions.assertEquals(0, size);
        isEmpty = trie.isEmpty();
        Assertions.assertTrue(isEmpty);
    }


    @Test
    public void prefixMatch() {
        // 网址安全校验
        Trie<Boolean> trie = new ConcurrentHashTrie<>();
        trie.put("baidu.com", true);
        trie.put("qq.com", true);
        trie.put("github.com", true);
        trie.put("xxdfdfsdaxdsfdsff.com", false);

        // 安全
        Tuple2<String, Boolean> prefixMatch = trie.prefixMatch("github.com/patricklaux/xtool");
        Assertions.assertTrue(prefixMatch.getT2());

        // 不安全
        prefixMatch = trie.prefixMatch("xxdfdfsdaxdsfdsff.com/error/wrong");
        Assertions.assertFalse(prefixMatch.getT2());

        // 未知
        prefixMatch = trie.prefixMatch("unkndfsasfdownaaaaadfdsfds.com/unknown/unknown");
        Assertions.assertNull(prefixMatch);
    }


    @Test
    public void prefixMatchAndPrefixMatchAll() {
        // 思维导图中的示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        // prefixMatch：仅返回最长的匹配结果
        Tuple2<String, String> prefixMatch = trie.prefixMatch("abcdef");
        Assertions.assertEquals("[abcd, abcd]", prefixMatch.toString());

        // prefixMatchAll：返回从短到长全部匹配到的结果
        List<Tuple2<String, String>> prefixMatchAll = trie.prefixMatchAll("abcdef");
        Assertions.assertEquals("[[ab, ab], [abc, abc], [abcd, abcd]]", prefixMatchAll.toString());


        // prefixMatch 的参数测试
        // prefixMatch：最长匹配
        prefixMatch = trie.prefixMatch("abcdef", true);
        Assertions.assertEquals("[abcd, abcd]", prefixMatch.toString());

        // prefixMatch：最短匹配
        prefixMatch = trie.prefixMatch("abcdef", false);
        Assertions.assertEquals("[ab, ab]", prefixMatch.toString());


        // prefixMatchAll 的参数测试
        // prefixMatchAll：最大返回数量为 1
        prefixMatchAll = trie.prefixMatchAll("abcdef", 1);
        Assertions.assertEquals("[[ab, ab]]", prefixMatchAll.toString());

        // prefixMatchAll：最大返回数量为10
        prefixMatchAll = trie.prefixMatchAll("abcdef", 10);
        Assertions.assertEquals("[[ab, ab], [abc, abc], [abcd, abcd]]", prefixMatchAll.toString());
    }

    @Test
    public void keyWithPrefix() {
        // 搜索引擎输入框提示列表
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("罗纳尔多C罗", "罗纳尔多C罗");
        trie.put("罗纳尔多进球集锦高清", "罗纳尔多进球集锦高清");
        trie.put("罗纳尔多图片", "罗纳尔多图片");
        trie.put("梅西法甲首球", "梅西法甲首球");
        trie.put("梅西现在在哪个球队", "梅西现在在哪个球队");
        trie.put("梅西图片", "梅西图片");
        trie.put("c罗梅西", "c罗梅西");
        trie.put("梅西c罗", "梅西c罗");

        Tuple2<String, String> ronaldo = trie.keyWithPrefix("罗纳尔多");
        Assertions.assertEquals("[罗纳尔多进球集锦高清, 罗纳尔多进球集锦高清]", ronaldo.toString());

        Tuple2<String, String> messi = trie.keyWithPrefix("梅西");
        Assertions.assertEquals("[梅西现在在哪个球队, 梅西现在在哪个球队]", messi.toString());
    }

    @Test
    public void testKeyWithPrefix() {
        // 思维导图中的示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        Tuple2<String, String> keyWithPrefix = trie.keyWithPrefix("ab");
        Assertions.assertEquals("[abcd, abcd]", keyWithPrefix.toString());

        keyWithPrefix = trie.keyWithPrefix("ab", true);
        Assertions.assertEquals("[abcd, abcd]", keyWithPrefix.toString());

        keyWithPrefix = trie.keyWithPrefix("ab", false);
        Assertions.assertEquals("[ab, ab]", keyWithPrefix.toString());
    }


    @Test
    public void keysWithPrefix() {
        // 搜索引擎输入框提示列表
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("罗纳尔多C罗", "罗纳尔多C罗");
        trie.put("罗纳尔多进球集锦高清", "罗纳尔多进球集锦高清");
        trie.put("罗纳尔多图片", "罗纳尔多图片");
        trie.put("梅西法甲首球", "梅西法甲首球");
        trie.put("梅西现在在哪个球队", "梅西现在在哪个球队");
        trie.put("梅西图片", "梅西图片");
        trie.put("c罗梅西", "c罗梅西");
        trie.put("梅西c罗", "梅西c罗");

        List<Tuple2<String, String>> ronaldo = trie.keysWithPrefix("罗纳尔多");
        Assertions.assertEquals("[[罗纳尔多C罗, 罗纳尔多C罗], [罗纳尔多图片, 罗纳尔多图片], [罗纳尔多进球集锦高清, 罗纳尔多进球集锦高清]]", ronaldo.toString());

        List<Tuple2<String, String>> messi = trie.keysWithPrefix("梅西");
        Assertions.assertEquals("[[梅西c罗, 梅西c罗], [梅西图片, 梅西图片], [梅西法甲首球, 梅西法甲首球], [梅西现在在哪个球队, 梅西现在在哪个球队]]", messi.toString());
    }

    @Test
    public void testKeysWithPrefix() {
        // 思维导图中的示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        List<Tuple2<String, String>> keysWithPrefix = trie.keysWithPrefix("ab");
        Assertions.assertEquals("[[ab, ab], [abc, abc], [abcd, abcd], [abd, abd]]", keysWithPrefix.toString());

        keysWithPrefix = trie.keysWithPrefix("abc");
        Assertions.assertEquals("[[abc, abc], [abcd, abcd]]", keysWithPrefix.toString());
    }


    @Test
    public void match() {
        // 敏感词过滤
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("敏感", "敏感");
        trie.put("敏感词", "敏感词");

        String text = "为什么不准发布？敏感词真敏感！";
        List<Found<String>> match = trie.match(text);
        Assertions.assertEquals("[{\"start\":8, \"end\":10, \"key\":\"敏感词\", \"value\":\"敏感词\"}, {\"start\":12, \"end\":13, \"key\":\"敏感\", \"value\":\"敏感\"}]", match.toString());

        // match 与 matchAll 对比，起始位置 8：contains只返回“敏感”；matchAll 返回了“敏感”和“敏感词”；
        List<Found<String>> matchAll = trie.matchAll(text);
        Assertions.assertEquals("[{\"start\":8, \"end\":9, \"key\":\"敏感\", \"value\":\"敏感\"}, {\"start\":8, \"end\":10, \"key\":\"敏感词\", \"value\":\"敏感词\"}, {\"start\":12, \"end\":13, \"key\":\"敏感\", \"value\":\"敏感\"}]", matchAll.toString());
    }


    @Test
    public void matchAndMatchAll() {
        // 思维导图中的示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");


        // match 与 matchAll 对比
        List<Found<String>> match = trie.match("xxabcdexx");
        Assertions.assertEquals("[{\"start\":2, \"end\":5, \"key\":\"abcd\", \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"key\":\"bcd\", \"value\":\"bcd\"}]", match.toString());

        List<Found<String>> matchAll = trie.matchAll("xxabcdexx");
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}, {\"start\":2, \"end\":4, \"key\":\"abc\", \"value\":\"abc\"}, {\"start\":2, \"end\":5, \"key\":\"abcd\", \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"key\":\"bcd\", \"value\":\"bcd\"}]", matchAll.toString());


        // match 参数变化对比
        // 最长匹配；逐字符扫描
        match = trie.match("xxabcdexx", true, true);
        Assertions.assertEquals("[{\"start\":2, \"end\":5, \"key\":\"abcd\", \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"key\":\"bcd\", \"value\":\"bcd\"}]", match.toString());

        // 最长匹配；跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配
        match = trie.match("xxabcdexx", true, false);
        Assertions.assertEquals("[{\"start\":2, \"end\":5, \"key\":\"abcd\", \"value\":\"abcd\"}]", match.toString());

        // 最短匹配；逐字符扫描
        match = trie.match("xxabcdexx", false, true);
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}, {\"start\":3, \"end\":5, \"key\":\"bcd\", \"value\":\"bcd\"}]", match.toString());

        // 最短匹配；跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配
        match = trie.match("xxabcdexx", false, false);
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}]", match.toString());


        // matchAll 参数变化对比
        // 逐字符扫描；最大返回数量为Integer.MAX_VALUE
        matchAll = trie.matchAll("xxabcdexx", true, Integer.MAX_VALUE);
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}, {\"start\":2, \"end\":4, \"key\":\"abc\", \"value\":\"abc\"}, {\"start\":2, \"end\":5, \"key\":\"abcd\", \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"key\":\"bcd\", \"value\":\"bcd\"}]", matchAll.toString());

        // 跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配；最大返回数量为Integer.MAX_VALUE
        matchAll = trie.matchAll("xxabcdexx", false, Integer.MAX_VALUE);
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}, {\"start\":2, \"end\":4, \"key\":\"abc\", \"value\":\"abc\"}, {\"start\":2, \"end\":5, \"key\":\"abcd\", \"value\":\"abcd\"}]", matchAll.toString());

        // 逐字符扫描；最大返回数量为1
        matchAll = trie.matchAll("xxabcdexx", true, 1);
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}]", matchAll.toString());

        // 跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配；最大返回数量为1
        matchAll = trie.matchAll("xxabcdexx", false, 1);
        Assertions.assertEquals("[{\"start\":2, \"end\":3, \"key\":\"ab\", \"value\":\"ab\"}]", matchAll.toString());
    }

    @Test
    public void height() {
        Trie<String> trie = new ConcurrentHashTrie<>();

        trie.put("ab", "ab");
        int height = trie.height();
        Assertions.assertEquals(2, height);

        trie.put("abc", "abc");
        height = trie.height();
        Assertions.assertEquals(3, height);

        trie.put("abcd", "abcd");
        height = trie.height();
        Assertions.assertEquals(4, height);

        trie.put("abd", "abd");
        height = trie.height();
        Assertions.assertEquals(4, height);

        trie.put("bcd", "bcd");
        height = trie.height();
        Assertions.assertEquals(4, height);

        trie.remove("abcd");
        height = trie.height();
        Assertions.assertEquals(3, height);
    }

    // 遍历键
    @Test
    public void keys() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        // 搜索深度为4
        List<String> keys = trie.keys(4);
        Assertions.assertEquals("[ab, abc, abcd, abd, bcd]", keys.toString());

        // 搜索深度为3
        keys = trie.keys(3);
        Assertions.assertEquals("[ab, abc, abd, bcd]", keys.toString());
    }

    // 遍历值
    @Test
    public void values() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        // 搜索深度为4
        List<String> values = trie.values(4);
        Assertions.assertEquals("[ab, abc, abcd, abd, bcd]", values.toString());

        // 搜索深度为3
        values = trie.values(3);
        Assertions.assertEquals("[ab, abc, abd, bcd]", values.toString());
    }

    // 遍历键值对
    @Test
    public void traversal() {
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        List<Tuple2<String, String>> entries = new ArrayList<>(5);

        // 搜索深度为4
        trie.traversal(4, new TraversalFunction(5, entries));
        Assertions.assertEquals("[[ab, ab], [abc, abc], [abcd, abcd], [abd, abd], [bcd, bcd]]", entries.toString());


        entries = new ArrayList<>(5);

        // 搜索深度为3
        trie.traversal(3, new TraversalFunction(5, entries));
        Assertions.assertEquals("[[ab, ab], [abc, abc], [abd, abd], [bcd, bcd]]", entries.toString());
    }

    // 示例：键值对的遍历函数（！！生产环境不建议使用容器来保存键值对，应该每一个分别处理，否则可能内存溢出！！）
    private static class TraversalFunction implements BiFunction<String, String, Boolean> {
        private final int maximum;
        private final List<Tuple2<String, String>> entries;

        public TraversalFunction(int maximum, List<Tuple2<String, String>> entries) {
            this.maximum = maximum;
            this.entries = entries;
        }

        @Override
        public Boolean apply(String key, String value) {
            entries.add(Tuples.of(key, value));
            return entries.size() < maximum;
        }
    }
}