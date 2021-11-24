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
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertNull(triePut);
        Assert.assertEquals(triePut, mapPut);

        // 再次 put，旧值都为 100
        triePut = trie.put(key, value);
        mapPut = map.put(key, value);
        Assert.assertNotNull(triePut);
        Assert.assertEquals(value, mapPut);
        Assert.assertEquals(triePut, mapPut);

        // get，返回值都为 100
        Integer trieGet = trie.put(key, value);
        Integer mapGet = map.put(key, value);
        Assert.assertEquals(value, mapPut);
        Assert.assertEquals(triePut, mapPut);
    }

    @Test
    public void putAndGetAndRemove() {
        // 思维导图中的方法示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        String get = trie.get("abcd");
        Assert.assertEquals("abcd", get);

        trie.remove("abcd");
        Assert.assertEquals("[ab, abc, abd, bcd]", trie.values(5).toString());
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
            Assert.assertEquals(i, trieGet);
            Assert.assertEquals(trieGet, mapGet);
        }

        for (int i = 0; i < 8; i++) {
            String key = prefix + i;
            int trieGet = trie.remove(key);
            int mapGet = map.remove(key);
            Assert.assertEquals(i, trieGet);
            Assert.assertEquals(trieGet, mapGet);
        }
    }


    @Test
    public void match() {
        // 网址安全校验
        Trie<Boolean> trie = new ConcurrentHashTrie<>();
        trie.put("baidu.com", true);
        trie.put("qq.com", true);
        trie.put("github.com", true);
        trie.put("xxdfdfsdaxdsfdsff.com", false);

        // 安全
        Boolean match = trie.match("github.com/patricklaux/xtool");
        Assert.assertTrue(match);

        // 不安全
        match = trie.match("xxdfdfsdaxdsfdsff.com/error/wrong");
        Assert.assertFalse(match);

        // 未知
        match = trie.match("unkndfsasfdownaaaaadfdsfds.com/unknown/unknown");
        Assert.assertNull(match);
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

        // match：仅返回最长的匹配结果
        String match = trie.match("abcdef");
        Assert.assertEquals("abcd", match);

        // matchAll：返回从短到长全部匹配到的结果
        List<String> matchAll = trie.matchAll("abcdef");
        Assert.assertEquals("[ab, abc, abcd]", matchAll.toString());


        // match 的参数测试
        // match：最长匹配
        match = trie.match("abcdef", true);
        Assert.assertEquals("abcd", match);

        // match：最短匹配
        match = trie.match("abcdef", false);
        Assert.assertEquals("ab", match);


        // matchAll 的参数测试
        // matchAll：最大返回数量为 1
        matchAll = trie.matchAll("abcdef", 1);
        Assert.assertEquals("[ab]", matchAll.toString());

        // matchAll：最大返回数量为10
        matchAll = trie.matchAll("abcdef", 10);
        Assert.assertEquals("[ab, abc, abcd]", matchAll.toString());
    }


    @Test
    public void search() {
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

        List<String> ronaldo = trie.search("罗纳尔多");
        Assert.assertEquals("[罗纳尔多C罗, 罗纳尔多图片, 罗纳尔多进球集锦高清]", ronaldo.toString());

        List<String> messi = trie.search("梅西");
        Assert.assertEquals("[梅西c罗, 梅西图片, 梅西法甲首球, 梅西现在在哪个球队]", messi.toString());
    }

    @Test
    public void testSearch() {
        // 思维导图中的示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        List<String> search = trie.search("ab");
        Assert.assertEquals("[ab, abc, abcd, abd]", search.toString());

        search = trie.search("abc");
        Assert.assertEquals("[abc, abcd]", search.toString());
    }


    @Test
    public void contains() {
        // 敏感词过滤
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("敏感", "敏感");
        trie.put("敏感词", "敏感词");

        String text = "为什么不准发布？敏感词真敏感！";
        List<Found<String>> contains = trie.contains(text);
        Assert.assertEquals("[{\"start\":8, \"end\":10, \"value\":\"敏感词\"}, {\"start\":12, \"end\":13, \"value\":\"敏感\"}]", contains.toString());

        // contains 与 containsAll 对比，起始位置 8：contains只返回“敏感”；containsAll 返回了“敏感”和“敏感词”；
        List<Found<String>> containsAll = trie.containsAll(text);
        Assert.assertEquals("[{\"start\":8, \"end\":9, \"value\":\"敏感\"}, {\"start\":8, \"end\":10, \"value\":\"敏感词\"}, {\"start\":12, \"end\":13, \"value\":\"敏感\"}]", containsAll.toString());
    }


    @Test
    public void containsAndContainsAll() {
        // 思维导图中的示例
        Trie<String> trie = new ConcurrentHashTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");


        // contains 与 containsAll 对比
        List<Found<String>> contains = trie.contains("xxabcdexx");
        Assert.assertEquals("[{\"start\":2, \"end\":5, \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"value\":\"bcd\"}]", contains.toString());

        List<Found<String>> containsAll = trie.containsAll("xxabcdexx");
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}, {\"start\":2, \"end\":4, \"value\":\"abc\"}, {\"start\":2, \"end\":5, \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"value\":\"bcd\"}]", containsAll.toString());


        // contains 参数变化对比
        // 最长匹配；逐字符扫描
        contains = trie.contains("xxabcdexx", true, true);
        Assert.assertEquals("[{\"start\":2, \"end\":5, \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"value\":\"bcd\"}]", contains.toString());

        // 最长匹配；跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配
        contains = trie.contains("xxabcdexx", true, false);
        Assert.assertEquals("[{\"start\":2, \"end\":5, \"value\":\"abcd\"}]", contains.toString());

        // 最短匹配；逐字符扫描
        contains = trie.contains("xxabcdexx", false, true);
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}, {\"start\":3, \"end\":5, \"value\":\"bcd\"}]", contains.toString());

        // 最短匹配；跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配
        contains = trie.contains("xxabcdexx", false, false);
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}]", contains.toString());


        // containsAll 参数变化对比
        // 逐字符扫描；最大返回数量为Integer.MAX_VALUE
        containsAll = trie.containsAll("xxabcdexx", true, Integer.MAX_VALUE);
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}, {\"start\":2, \"end\":4, \"value\":\"abc\"}, {\"start\":2, \"end\":5, \"value\":\"abcd\"}, {\"start\":3, \"end\":5, \"value\":\"bcd\"}]", containsAll.toString());

        // 跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配；最大返回数量为Integer.MAX_VALUE
        containsAll = trie.containsAll("xxabcdexx", false, Integer.MAX_VALUE);
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}, {\"start\":2, \"end\":4, \"value\":\"abc\"}, {\"start\":2, \"end\":5, \"value\":\"abcd\"}]", containsAll.toString());

        // 逐字符扫描；最大返回数量为1
        containsAll = trie.containsAll("xxabcdexx", true, 1);
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}]", containsAll.toString());

        // 跳过已匹配到的词，跳到已匹配到的词的下标 + 1 开始匹配；最大返回数量为1
        containsAll = trie.containsAll("xxabcdexx", false, 1);
        Assert.assertEquals("[{\"start\":2, \"end\":3, \"value\":\"ab\"}]", containsAll.toString());
    }

    @Test
    public void height() {
        Trie<String> trie = new ConcurrentHashTrie<>();

        trie.put("ab", "ab");
        int height = trie.height();
        Assert.assertEquals(2, height);

        trie.put("abc", "abc");
        height = trie.height();
        Assert.assertEquals(3, height);

        trie.put("abcd", "abcd");
        height = trie.height();
        Assert.assertEquals(4, height);

        trie.put("abd", "abd");
        height = trie.height();
        Assert.assertEquals(4, height);

        trie.put("bcd", "bcd");
        height = trie.height();
        Assert.assertEquals(4, height);

        trie.remove("abcd");
        height = trie.height();
        Assert.assertEquals(3, height);
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
        Assert.assertEquals("[ab, abc, abcd, abd, bcd]", values.toString());

        // 搜索深度为3
        values = trie.values(3);
        Assert.assertEquals("[ab, abc, abd, bcd]", values.toString());
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
        Assert.assertEquals("[[ab, ab], [abc, abc], [abcd, abcd], [abd, abd], [bcd, bcd]]", entries.toString());


        entries = new ArrayList<>(5);

        // 搜索深度为3
        trie.traversal(3, new TraversalFunction(5, entries));
        Assert.assertEquals("[[ab, ab], [abc, abc], [abd, abd], [bcd, bcd]]", entries.toString());
    }

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