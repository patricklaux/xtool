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

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-23
 */
public class ConcurrentTrieTest {


    @Test
    public void put() {
        Trie<String> trie = new ConcurrentTrie<>();
        String put = trie.put("a", "a");
        Assert.assertNull(put);

        put = trie.put("ab", "ab");
        Assert.assertNull(put);
        put = trie.put("ab", "ab");
        Assert.assertEquals("ab", put);

        put = trie.put("ach", "ach");
        Assert.assertNull(put);
        put = trie.put("ach", "ach");
        Assert.assertEquals("ach", put);

        trie.put("aci", "aci");
        trie.put("adjn", "adjn");
        trie.put("aek", "aek");
        trie.put("afl", "afl");
        trie.put("afmo", "afmo");
        trie.put("ag", "ag");

        List<String> values = trie.values(4);
        Assert.assertEquals("[a, ab, ach, aci, adjn, aek, afl, afmo, ag]", values.toString());
    }

    @Test
    public void putAll() {
        Trie<String> trie = new ConcurrentTrie<>();
        List<String> strings = Arrays.asList("aaaa", "bbbbb", "cccccc");
        TreeMap<String, String> treeMap = new TreeMap<>();
        strings.forEach(key -> treeMap.put(key, key));
        trie.putAll(treeMap);

        List<String> values = trie.values(6);
        Assert.assertEquals("[aaaa, bbbbb, cccccc]", values.toString());
    }

    @Test
    public void get() {
        Trie<String> trie = new ConcurrentTrie<>();
        List<String> strings = Arrays.asList("aaaa", "bbbbb", "cccccc");
        strings.forEach(key -> trie.put(key, key));
        trie.put("aaa", "aaa");
        trie.put("aaab", "aaab");

        List<String> result = new ArrayList<>();
        for (String key : strings) {
            result.add(trie.get(key));
        }

        Assert.assertEquals(strings, result);
    }

    @Test
    public void match() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abcd";
        String key2 = "abcde";
        String key3 = "abcdef";
        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        String longMatch = trie.match("abcdefgh");
        System.out.println("longMatch:\t\t" + longMatch);
        Assert.assertEquals(key3, longMatch);

        String shortMatch = trie.match("abcdefgh", false);
        System.out.println("shortMatch1:\t" + shortMatch);
        Assert.assertEquals(key1, shortMatch);
    }

    @Test
    public void match2() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "ab";
        String key2 = "abc";
        String key3 = "abd";
        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);

        String longMatch = trie.match("abcdefg");
        System.out.println("longMatch:\t\t" + longMatch);
        Assert.assertEquals(key2, longMatch);

        String shortMatch = trie.match("abcdefgh", false);
        System.out.println("shortMatch1:\t" + shortMatch);
        Assert.assertEquals(key1, shortMatch);
    }

    @Test
    public void matchAll() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "1234";
        String key2 = "12345";
        String key3 = "123456";
        String key4 = "123456";
        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);

        List<String> matches = trie.matchAll("123456789");
        System.out.println(matches);
        Assert.assertEquals(key3, matches.get(2));

        List<String> limitMatches = trie.matchAll("123456789", 2);
        System.out.println(limitMatches);
        Assert.assertEquals(2, limitMatches.size());
    }

    @Test
    public void search() {
        Trie<String> trie = new ConcurrentTrie<>();
        trie.put("a", "a");
        trie.put("ab", "ab");
        trie.put("ac", "ac");
        trie.put("ach", "ach");
        trie.put("aci", "aci");
        trie.put("adjn", "adjn");
        trie.put("aek", "aek");
        trie.put("afl", "afl");
        trie.put("afmo", "afmo");
        trie.put("ag", "ag");
        trie.put("agfff", "agfff");
        trie.put("ajfff", "ajfff");
        trie.put("ahfff", "ahfff");
        trie.put("aifff", "aifff");

        // DFS
        // 不存在该前缀
        List<String> dfs = trie.search("b", 1, 1, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[]", dfs.toString());

        // 仅返回前缀的值
        dfs = trie.search("a", 1, 1, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[a]", dfs.toString());

        dfs = trie.search("a", 10, 10, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[a, ab, ac, ach, aci, adjn, aek, afl, afmo, ag]", dfs.toString());

        // DFS
        dfs = trie.search("a");
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[a, ab, ac, ach, aci, adjn, aek, afl, afmo, ag, agfff, ahfff, aifff, ajfff]", dfs.toString());

        // DFS
        List<String> dfs0 = trie.search("a", Integer.MAX_VALUE, 0, false);
        System.out.println("DFS0:\t" + dfs0);
        Assert.assertEquals("[a]", dfs0.toString());

        // BFS
        List<String> bfs0 = trie.search("a", Integer.MAX_VALUE, 0, true);
        System.out.println("BFS0:\t" + bfs0);
        Assert.assertEquals("[a]", bfs0.toString());

        // DFS
        List<String> dfs1 = trie.search("a", Integer.MAX_VALUE, 1, false);
        System.out.println("DFS1:\t" + dfs1);
        Assert.assertEquals("[a, ab, ac, ag]", dfs1.toString());

        // BFS
        List<String> bfs1 = trie.search("a", Integer.MAX_VALUE, 1, false);
        System.out.println("BFS1:\t" + bfs1);
        Assert.assertEquals("[a, ab, ac, ag]", bfs1.toString());

        // DFS
        List<String> dfs2 = trie.search("a", Integer.MAX_VALUE, 2, true);
        System.out.println("DFS2:\t" + dfs2);
        Assert.assertEquals("[a, ab, ac, ach, aci, aek, afl, ag]", dfs2.toString());

        // BFS
        List<String> bfs2 = trie.search("a", Integer.MAX_VALUE, 2, false);
        System.out.println("BFS2:\t" + bfs2);
        Assert.assertEquals("[a, ab, ac, ag, ach, aci, aek, afl]", bfs2.toString());

        // DFS
        List<String> dfs3 = trie.search("a", Integer.MAX_VALUE, 3, true);
        System.out.println("DFS3:\t" + dfs3);
        Assert.assertEquals("[a, ab, ac, ach, aci, adjn, aek, afl, afmo, ag]", dfs3.toString());

        // BFS
        List<String> bfs3 = trie.search("a", Integer.MAX_VALUE, 3, false);
        System.out.println("BFS3:\t" + bfs3);
        Assert.assertEquals("[a, ab, ac, ag, ach, aci, aek, afl, adjn, afmo]", bfs3.toString());

        // DFS
        List<String> dfs4 = trie.search("a", Integer.MAX_VALUE, 4, true);
        System.out.println("DFS4:\t" + dfs4);
        Assert.assertEquals("[a, ab, ac, ach, aci, adjn, aek, afl, afmo, ag, agfff, ahfff, aifff, ajfff]", dfs4.toString());

        // BFS
        List<String> bfs4 = trie.search("a", Integer.MAX_VALUE, 4, false);
        System.out.println("BFS4:\t" + bfs4);
        Assert.assertEquals("[a, ab, ac, ag, ach, aci, aek, afl, adjn, afmo, agfff, ahfff, aifff, ajfff]", bfs4.toString());

        // DFS（测试不存在的前缀）
        List<String> dfs5 = trie.search("b", Integer.MAX_VALUE, 4, true);
        System.out.println("DFS5:\t" + dfs5);
        Assert.assertEquals("[]", dfs5.toString());

        // BFS（测试不存在的前缀）
        List<String> bfs5 = trie.search("b", Integer.MAX_VALUE, 4, false);
        System.out.println("BFS5:\t" + bfs5);
        Assert.assertEquals("[]", bfs5.toString());
    }

    @Test
    public void search1() {
        Trie<String> trie = new ConcurrentTrie<>();
        trie.put("ab", "ab");
        trie.put("abc", "abc");
        trie.put("abcd", "abcd");
        trie.put("abd", "abd");
        trie.put("bcd", "bcd");

        // DFS
        List<String> dfs = trie.search("ab");
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[ab, abc, abcd, abd]", dfs.toString());

        dfs = trie.search("ab", 0, 1, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[]", dfs.toString());

        dfs = trie.search("ab", 1000, -1, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[]", dfs.toString());

        dfs = trie.search("ab", 1000, 0, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[ab]", dfs.toString());

        List<String> bfs = trie.search("ab", 1000, 0, false);
        System.out.println("BFS:\t" + bfs);
        Assert.assertEquals("[ab]", bfs.toString());


        dfs = trie.search("ab", 1000, 1, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[ab, abc, abd]", dfs.toString());

        bfs = trie.search("ab", 1000, 1, false);
        System.out.println("BFS:\t" + bfs);
        Assert.assertEquals("[ab, abc, abd]", bfs.toString());


        dfs = trie.search("ab", 1000, 2, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[ab, abc, abcd, abd]", dfs.toString());

        bfs = trie.search("ab", 1000, 2, false);
        System.out.println("BFS:\t" + bfs);
        Assert.assertEquals("[ab, abc, abd, abcd]", bfs.toString());

        dfs = trie.search("ab", 1000, 3, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[ab, abc, abcd, abd]", dfs.toString());

        bfs = trie.search("ab", 1000, 3, false);
        System.out.println("BFS:\t" + bfs);
        Assert.assertEquals("[ab, abc, abd, abcd]", bfs.toString());

        // 限定搜索数量
        dfs = trie.search("ab", 3, 3, true);
        System.out.println("DFS:\t" + dfs);
        Assert.assertEquals("[ab, abc, abcd]", dfs.toString());

        // 限定搜索数量
        bfs = trie.search("ab", 3, 3, false);
        System.out.println("BFS:\t" + bfs);
        Assert.assertEquals("[ab, abc, abd]", bfs.toString());
    }

    @Test
    public void contains() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "ab";
        String key2 = "bc";
        String key3 = "cd";
        String key4 = "ef";
        String key5 = "efg";
        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);
        trie.put(key5, key5);


        List<Found<String>> shortMatches = trie.contains("abcdefg");
        System.out.println(shortMatches);
        String expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}, {\"start\":1, \"end\":2, \"value\":\"bc\"}, {\"start\":2, \"end\":3, \"value\":\"cd\"}, {\"start\":4, \"end\":6, \"value\":\"efg\"}]";
        Assert.assertEquals(expected, shortMatches.toString());


        List<Found<String>> shortMatches2 = trie.contains("abcdefg", true, true);
        System.out.println(shortMatches2);
        Assert.assertEquals(expected, shortMatches2.toString());


        List<Found<String>> longMatches = trie.contains("abcdefg", true, true);
        System.out.println(longMatches);
        expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}, {\"start\":1, \"end\":2, \"value\":\"bc\"}, {\"start\":2, \"end\":3, \"value\":\"cd\"}, {\"start\":4, \"end\":6, \"value\":\"efg\"}]";
        Assert.assertEquals(expected, longMatches.toString());


        List<Found<String>> skipMatches2 = trie.contains("abcdefg", false, false);
        System.out.println(skipMatches2);
        expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}, {\"start\":2, \"end\":3, \"value\":\"cd\"}, {\"start\":4, \"end\":5, \"value\":\"ef\"}]";
        Assert.assertEquals(expected, skipMatches2.toString());


        List<Found<String>> skipMatches = trie.contains("abcdefg", true, false);
        System.out.println(skipMatches);
        expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}, {\"start\":2, \"end\":3, \"value\":\"cd\"}, {\"start\":4, \"end\":6, \"value\":\"efg\"}]";
        Assert.assertEquals(expected, skipMatches.toString());
    }

    @Test
    public void containsAll() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "ab";
        String key2 = "bc";
        String key3 = "cd";
        String key4 = "ef";
        String key5 = "efg";
        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);
        trie.put(key5, key5);

        List<Found<String>> shortMatches = trie.containsAll("abcdefg");
        System.out.println(shortMatches);
        String expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}, {\"start\":1, \"end\":2, \"value\":\"bc\"}, {\"start\":2, \"end\":3, \"value\":\"cd\"}, {\"start\":4, \"end\":5, \"value\":\"ef\"}, {\"start\":4, \"end\":6, \"value\":\"efg\"}]";
        Assert.assertEquals(expected, shortMatches.toString());


        List<Found<String>> shortMatches1 = trie.containsAll("abcdefg", true, Integer.MAX_VALUE);
        System.out.println(shortMatches1);
        Assert.assertEquals(expected, shortMatches1.toString());


        List<Found<String>> shortMatches2 = trie.containsAll("abcdefg", false, Integer.MAX_VALUE);
        System.out.println(shortMatches2);
        expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}, {\"start\":2, \"end\":3, \"value\":\"cd\"}, {\"start\":4, \"end\":5, \"value\":\"ef\"}, {\"start\":4, \"end\":6, \"value\":\"efg\"}]";
        Assert.assertEquals(expected, shortMatches2.toString());

        List<Found<String>> shortMatches3 = trie.containsAll("abcdefg", false, 1);
        System.out.println(shortMatches3);
        expected = "[{\"start\":0, \"end\":1, \"value\":\"ab\"}]";
        Assert.assertEquals(expected, shortMatches3.toString());
    }

    @Test
    public void values() {
        Trie<String> trie = new ConcurrentTrie<>();
        trie.put("a", "a");
        trie.put("ab", "ab");
        trie.put("ach", "ach");
        trie.put("aci", "aci");
        trie.put("adjn", "adjn");
        trie.put("aek", "aek");
        trie.put("afl", "afl");
        trie.put("afmo", "afmo");
        trie.put("ag", "ag");
        List<String> values = trie.values(5);
        Assert.assertEquals("[a, ab, ach, aci, adjn, aek, afl, afmo, ag]", values.toString());
    }

    @Test
    public void traversal() {
        Trie<String> trie = new ConcurrentTrie<>();
        trie.put("a", "a");
        trie.put("ab", "ab");
        trie.put("ach", "ach");
        trie.put("aci", "aci");
        trie.put("adjn", "adjn");
        trie.put("aek", "aek");
        trie.put("afl", "afl");
        trie.put("afmo", "afmo");
        trie.put("ag", "ag");

        List<Tuple2<String, String>> list1 = new LinkedList<>();
        trie.traversal(1, (key, value) -> {
            list1.add(Tuples.of(key, value));
            return true;
        });
        System.out.println(list1);
        String expected = "[[a, a]]";
        Assert.assertEquals(expected, list1.toString());


        List<Tuple2<String, String>> list2 = new LinkedList<>();
        trie.traversal(2, (key, value) -> {
            list2.add(Tuples.of(key, value));
            return true;
        });
        System.out.println(list2);
        expected = "[[a, a], [ab, ab], [ag, ag]]";

        Assert.assertEquals(expected, list2.toString());


        List<Tuple2<String, String>> list3 = new LinkedList<>();
        trie.traversal(3, (key, value) -> {
            list3.add(Tuples.of(key, value));
            return true;
        });
        System.out.println(list3);
        expected = "[[a, a], [ab, ab], [ach, ach], [aci, aci], [aek, aek], [afl, afl], [ag, ag]]";
        Assert.assertEquals(expected, list3.toString());


        List<Tuple2<String, String>> list4 = new LinkedList<>();
        trie.traversal(4, (key, value) -> {
            list4.add(Tuples.of(key, value));
            return true;
        });
        System.out.println(list4);
        expected = "[[a, a], [ab, ab], [ach, ach], [aci, aci], [adjn, adjn], [aek, aek], [afl, afl], [afmo, afmo], [ag, ag]]";
        Assert.assertEquals(expected, list4.toString());


        List<Tuple2<String, String>> list5 = new LinkedList<>();
        trie.traversal(Integer.MAX_VALUE, (key, value) -> {
            list5.add(Tuples.of(key, value));
            return true;
        });
        System.out.println(list5);
        expected = "[[a, a], [ab, ab], [ach, ach], [aci, aci], [adjn, adjn], [aek, aek], [afl, afl], [afmo, afmo], [ag, ag]]";
        Assert.assertEquals(expected, list5.toString());
    }

    @Test
    public void remove() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abcd";
        String key2 = "abcde";
        String key3 = "abcdef";
        String key4 = "abcdefg";
        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);

        String longMatch1 = trie.match("abcdefghi");
        System.out.println("longMatch1:\t\t" + longMatch1);
        Assert.assertEquals(key4, longMatch1);

        String remove = trie.remove(key4);
        System.out.println("remove:\t\t\t" + remove);
        Assert.assertEquals(key4, remove);

        String longMatch2 = trie.match("abcdefghi");
        System.out.println("longMatch2:\t\t" + longMatch2);
        Assert.assertEquals(key3, longMatch2);

        String shortMatch1 = trie.match("abcdefghi", false);
        System.out.println("shortMatch1:\t" + shortMatch1);
        Assert.assertEquals(key1, shortMatch1);

        String remove2 = trie.remove(key1);
        System.out.println("remove2:\t\t" + remove2);
        Assert.assertEquals(key1, remove2);

        String shortMatch2 = trie.match("abcdefghi", false);
        System.out.println("shortMatch2:\t" + shortMatch2);
        Assert.assertEquals(key2, shortMatch2);
    }

    /**
     * <p>找到删除路径中需要删除的第一个节点，并删除该分支</p>
     * abc 有两个后缀： "def" 和 "fgh"，其中 'f' 为 待删除节点 abcfgh 的起始删除字符，此次测试需要删除且仅删除 "fgh" 后缀分支。
     */
    @Test
    public void remove2() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abcdef";
        String key3 = "abcfgh";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);

        String remove = trie.remove(key3);
        Assert.assertEquals(key3, remove);

        List<String> values = trie.values(6);
        System.out.println(values);
        Assert.assertEquals("[abc, abcdef]", values.toString());
    }

    /**
     * <p>测试仅删除最后一个字符的情况</p>
     * abc 后接两个字符： 'd' 和 'f'，其中 'f' 为 待删除节点 abcf 的最后一个字符，此次测试需要删除且仅删除 'f'。
     */
    @Test
    public void remove3() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abcd";
        String key3 = "abcf";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);

        String remove = trie.remove(key3);
        Assert.assertEquals(key3, remove);

        List<String> values = trie.values(4);
        System.out.println(values);
        Assert.assertEquals("[abc, abcd]", values.toString());
    }

    /**
     * <p>测试 value 不为空的情况</p>
     * 如果 abc 不存在，那么删除 abcdef 时，将删除全部节点，仅剩 root节点；
     * 因为 abc 存在，因此会留下 abc，删除 def 节点。
     */
    @Test
    public void remove4() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abcde";
        String key3 = "abcd";
        String key4 = "abce";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key4, key4);

        List<String> values = trie.values(10);
        System.out.println(values);
        Assert.assertEquals("[abc, abcde, abce]", values.toString());

        String remove3 = trie.remove(key3);
        Assert.assertNull(key2, remove3);

        values = trie.values(10);
        System.out.println(values);
        Assert.assertEquals("[abc, abcde, abce]", values.toString());
    }

    @Test
    public void height() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abd";
        String key3 = "abcd";
        String key4 = "abcde";
        String key5 = "abcdf";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);
        trie.put(key5, key5);

        Assert.assertEquals(5, trie.height());

        trie.remove(key5);
        Assert.assertEquals(5, trie.height());

        trie.remove(key4);
        Assert.assertEquals(4, trie.height());

        trie.remove(key3);
        Assert.assertEquals(3, trie.height());

        trie.remove(key2);
        Assert.assertEquals(3, trie.height());

        trie.remove(key2);
        Assert.assertEquals(3, trie.height());

        trie.remove(key1);
        Assert.assertEquals(0, trie.height());

        trie.remove(key1);
        Assert.assertEquals(0, trie.height());
    }

    @Test
    public void size() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abd";
        String key3 = "abcd";
        String key4 = "abcde";
        String key5 = "abcdf";
        String key6 = "abcdf";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);
        trie.put(key5, key5);
        trie.put(key6, key6);

        Assert.assertEquals(5, trie.size());

        trie.remove(key6);
        Assert.assertEquals(4, trie.size());

        trie.remove(key5);
        Assert.assertEquals(4, trie.size());

        trie.remove(key4);
        Assert.assertEquals(3, trie.size());

        trie.remove(key4);
        Assert.assertEquals(3, trie.size());

        trie.remove(key3);
        Assert.assertEquals(2, trie.size());

        trie.remove(key2);
        Assert.assertEquals(1, trie.size());

        trie.remove(key1);
        Assert.assertEquals(0, trie.size());

        trie.put(key6, key6);

        trie.remove(key1);
        Assert.assertEquals(1, trie.size());
    }

    @Test
    public void isEmpty() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abd";
        String key3 = "abcd";
        String key4 = "abcde";
        String key5 = "abcdf";
        String key6 = "abcdf";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);
        trie.put(key5, key5);
        trie.put(key6, key6);

        Assert.assertFalse(trie.isEmpty());

        trie.remove(key6);
        Assert.assertFalse(trie.isEmpty());

        trie.remove(key5);
        Assert.assertFalse(trie.isEmpty());

        trie.remove(key4);
        Assert.assertFalse(trie.isEmpty());

        trie.remove(key4);
        Assert.assertFalse(trie.isEmpty());

        trie.remove(key3);
        Assert.assertFalse(trie.isEmpty());

        trie.remove(key2);
        Assert.assertFalse(trie.isEmpty());

        trie.remove(key1);
        Assert.assertTrue(trie.isEmpty());
    }

    @Test
    public void clear() {
        Trie<String> trie = new ConcurrentTrie<>();
        String key1 = "abc";
        String key2 = "abd";
        String key3 = "abcd";
        String key4 = "abcde";
        String key5 = "abcdf";
        String key6 = "abcdf";

        trie.put(key1, key1);
        trie.put(key2, key2);
        trie.put(key3, key3);
        trie.put(key4, key4);
        trie.put(key5, key5);
        trie.put(key6, key6);

        Assert.assertFalse(trie.isEmpty());
        trie.clear();
        Assert.assertTrue(trie.isEmpty());
    }



}