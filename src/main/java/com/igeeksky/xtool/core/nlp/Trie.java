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

import java.util.List;
import java.util.TreeMap;
import java.util.function.BiFunction;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-23
 */
public interface Trie<V> {

    /**
     * 添加键值对
     *
     * @param key   键：不为空且长度大于0
     * @param value 值：不能为空
     * @return 如果树中原来已存在此 key，则返回 key 对应的旧 value；否则返回空
     */
    V put(String key, V value);

    /**
     * 添加多个键值对
     *
     * @param treeMap 多个键值对（键：不为空且长度大于0；值：不能为空）
     */
    void putAll(TreeMap<String, V> treeMap);

    /**
     * 精确匹配：根据 key 获取 value
     * <p>
     * 等同于 hashmap 的 get 方法
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.get("abcd") == abcd
     * </pre>
     *
     * @param key 键（精确匹配，不为空且长度大于0）
     * @return 如果树中存在此 key，则返回 key 对应的 value；否则返回空
     */
    V get(String key);

    /**
     * 前缀匹配：使用输入字符串的前缀去匹配树中已有的 key。
     * <p>
     * 如果 key 存在，返回 key 和 value；否则，返回空
     * <p>
     * 默认返回最长匹配结果：longestMatch = {@link Boolean#TRUE}
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.prefixMatch("abcdef") == [abcd, abcd]
     *     依次会匹配到 ab, abc, abcd，默认返回最长匹配结果：abcd
     * </pre>
     *
     * @param word 待匹配词（不为空且长度大于0）
     * @return 是否匹配到 key。是：返回 key 和 value；否：返回空
     */
    Tuple2<String, V> prefixMatch(String word);

    /**
     * 前缀匹配，使用输入字符串的前缀去匹配树中已有的 key
     * <p>
     * 如果key 存在，返回 key 和 value；否则，返回空
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.prefixMatch("abcdef", true) == [abcd, abcd]
     *     trie.prefixMatch("abcdef", false) == [ab, ab]
     *     trie.prefixMatch("ccc", true) == null
     *     trie.prefixMatch("ccc", false) == null
     * </pre>
     *
     * @param word         待匹配词（不为空且长度大于0）
     * @param longestMatch 是否最长匹配
     * @return 是否匹配到 key：是，返回 key 和 value；否，返回空
     */
    Tuple2<String, V> prefixMatch(String word, boolean longestMatch);

    /**
     * 前缀匹配：使用输入的字符串的前缀去匹配树中已有的 key
     * <p>
     * 如果匹配到多个 key，那么将这些 keys 和 values 都返回
     * <p>
     * 默认返回全部结果，maximum = {@link Integer#MAX_VALUE}
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.prefixMatchAll("abcdef") == [[ab, ab], [abc, abc], [abcd, abcd]]
     * </pre>
     *
     * @param word 待匹配文本（不为空且长度大于0）
     * @return 是否匹配到 key：是，返回所有匹配的 key 和 对应的 value；否，返回空列表
     */
    List<Tuple2<String, V>> prefixMatchAll(String word);

    /**
     * 前缀匹配：使用输入的字符串的前缀去匹配树中已有的 key
     * <p>
     * 如果匹配到多个 key，那么将这些 keys 和 values 都返回
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.prefixMatchAll("abcdef", 1) == [[ab, ab]]
     *     trie.prefixMatchAll("abcdef", 2) == [[ab, ab], [abc, abc]]
     *     trie.prefixMatchAll("abcdef", 3) == [[ab, ab], [abc, abc], [abcd, abcd]]
     * </pre>
     *
     * @param word    待匹配文本（不为空且长度大于0）
     * @param maximum 最大返回结果数量（默认：{@link Integer#MAX_VALUE}）
     * @return 是否匹配到 key：是，返回所有匹配的 key 和 对应的 value；否，返回空列表
     */
    List<Tuple2<String, V>> prefixMatchAll(String word, int maximum);


    /**
     * 匹配前缀：输入前缀，返回以此前缀开头的 key 及对应 value
     * <p>
     * 默认最长匹配，longestMatch = {@link Boolean#TRUE}
     * <p>
     * 此方法等同于：trie.keyWithPrefix("ab", true)
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.keyWithPrefix("ab") == [abcd, abcd]；
     * </pre>
     *
     * @param prefix 前缀（不为空且长度大于0）
     * @return 所有包含给定前缀的 key 及对应 value
     */
    Tuple2<String, V> keyWithPrefix(String prefix);

    /**
     * 匹配前缀：输入前缀，返回以此前缀开头的 key 及对应 value
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.keyWithPrefix("ab") == [abcd, abcd]；
     *     trie.keyWithPrefix("ab", true) == [abcd, abcd]；
     *     trie.keyWithPrefix("ab", false) == [ab, ab]；
     * </pre>
     *
     * @param prefix       前缀（不为空且长度大于0）
     * @param longestMatch 是否最长匹配
     * @return 所有包含此前缀的 key 及对应 value
     */
    Tuple2<String, V> keyWithPrefix(String prefix, boolean longestMatch);

    /**
     * 匹配前缀：输入前缀，返回以此前缀开头的 key 及对应 value
     * <p>
     * 如果有多个 key 都以此前缀开头，将这些 keys 和对应的 values 都返回
     * <p>
     * 默认返回全部结果，maximum = {@link Integer#MAX_VALUE}
     * <p>
     * 默认最大搜索深度：depth = {@link Integer#MAX_VALUE}
     * <p>
     * 默认深度优先遍历：dfs = {@link Boolean#TRUE}
     * <p>
     * 此方法等同于：trie.keysWithPrefix("ab", Integer.MAX_VALUE, Integer.MAX_VALUE, true)
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.keysWithPrefix("ab") == [[ab, ab], [abc, abc], [abcd, abcd], [abd, abd]]
     * </pre>
     *
     * @param prefix 前缀（不为空且长度大于0）
     * @return 所有包含此前缀的 keys 及对应 values
     */
    List<Tuple2<String, V>> keysWithPrefix(String prefix);

    /**
     * 匹配前缀：输入前缀，返回以此前缀开头的 key 及对应 value
     * <p>
     * 如果有多个 key 都以此前缀开头，将这些 keys 和对应的 values 都返回
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.keysWithPrefix("ab", 1000, 0, true) == [[ab, ab]]；
     *     trie.keysWithPrefix("ab", 1000, 0, false); 结果：[[ab, ab]]
     *
     *     trie.keysWithPrefix("ab", 1000, 1, true) == [[ab, ab], [abc, abc], [abd, abd]]
     *     trie.keysWithPrefix("ab", 1000, 1, false) == [[ab, ab], [abc, abc], [abd, abd]]
     *
     *     trie.keysWithPrefix("ab", 1000, 2, true) == [[ab, ab], [abc, abc], [abcd, abcd], [abd, abd]]
     *     trie.keysWithPrefix("ab", 1000, 2, false) == [[ab, ab], [abc, abc], [abd, abd], [abcd, abcd]]
     *
     *     trie.keysWithPrefix("ab", 1000, 3, true); 结果：[[ab, ab], [abc, abc], [abcd, abcd], [abd, abd]]
     *     trie.keysWithPrefix("ab", 1000, 3, false); 结果：[[ab, ab], [abc, abc], [abd, abd], [abcd, abcd]]
     *
     *     trie.keysWithPrefix("ab", 3, 3, true); 结果：[[ab, ab], [abc, abc], [abcd, abcd]]
     *     trie.keysWithPrefix("ab", 3, 3, false); 结果：[[ab, ab], [abc, abc], [abd, abd]]
     * </pre>
     *
     * @param prefix  前缀（不为空且长度大于0）
     * @param maximum 最大返回结果数量（默认：{@link Integer#MAX_VALUE}）
     * @param depth   搜索深度（默认：{@link Integer#MAX_VALUE}）
     * @param dfs     是否深度优先遍历（true，深度优先遍历；false，广度优先遍历；默认：{@link Boolean#TRUE}）
     * @return 所有包含给定前缀的 keys 和对应的 values。
     */
    List<Tuple2<String, V>> keysWithPrefix(String prefix, int maximum, int depth, boolean dfs);

    /**
     * 包含匹配：输入一段文本，返回该文本中包含的 key 及对应 value、与及 key 的起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 keys 和 对应的 values 和 起止位置都返回
     * <p>
     * 如果文本段中的同一起始位置匹配到多个 key，默认仅返回最长的那个。
     * <p>
     * 默认最长匹配：longestMatch={@link Boolean#TRUE}
     * <p>
     * 默认逐字符查找：oneByOne = {@link Boolean#TRUE}
     * <p>
     * 此方法等同于：trie.match("xxabcdexx", true, true)
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.match("xxabcdexx") == [{"start":2, "end":5, "key":"abcd", "value":"abcd"}, {"start":3, "end":5, "key":"bcd", "value":"bcd"}]
     * </pre>
     *
     * @param text 文本段（不为空且长度大于0）
     * @return 返回该文本中包含的所有 keys 及对应 values、与及 key 的起止位置
     */
    List<Found<V>> match(String text);

    /**
     * 包含匹配：输入一段文本，返回该文本中包含的 key 及对应 value、与及 key 的起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 keys 和 对应的 values 和 起止位置都返回
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *
     *     trie.match("xxabcdexx", true, true) ==
     *     [{"start":2, "end":5, "key":"abcd", "value":"abcd"}, {"start":3, "end":5, "key":"bcd", "value":"bcd"}]
     *
     *     trie.match("xxabcdexx", true, false) ==
     *     [{"start":2, "end":5, "key":"abcd", "value":"abcd"}]
     *
     *     trie.match("xxabcdexx", false, true) ==
     *     [{"start":2, "end":3, "key":"ab", "value":"ab"}, {"start":3, "end":5, "key":"bcd", "value":"bcd"}]
     *
     *     trie.match("xxabcdexx", false, false) ==
     *     [{"start":2, "end":3, "key":"ab", "value":"ab"}]
     * </pre>
     *
     * @param text         文本段（不为空且长度大于0）
     * @param longestMatch 是否最长匹配（默认：true 最长匹配）
     * @param oneByOne     是否逐字符匹配（是：当前下标 + 1开始查找；否：当前下标 + 找到词长度 + 1 开始查找）
     * @return 返回该文本中包含的所有 keys 及对应 values、与及 key 的起止位置
     */
    List<Found<V>> match(String text, boolean longestMatch, boolean oneByOne);

    /**
     * 包含匹配：输入一段文本，返回文本中包含的 key 及对应的 value 和 key 的起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 keys 和 对应的 values 和 起止位置都返回
     * <p>
     * match：如果文本中的同一起始位置匹配到多个 key，仅返回最长（默认）的那个；
     * <p>
     * matchAll：如果文本中的同一起始位置匹配到多个 key，从短到长全部返回。
     * <p>
     * 默认不跳过长度，逐字符查找：oneByOne = {@link Boolean#TRUE}；
     * <p>
     * 默认返回全部结果，maximum={@link Integer#MAX_VALUE}
     * <p>
     * 等同于：trie.matchAll("xxabcdexx", true, Integer.MAX_VALUE)
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.matchAll("xxabcdexx") ==
     *     [{"start":2, "end":3, "key":"ab", "value":"ab"}, {"start":2, "end":4, "key":"abc", "value":"abc"},
     *     {"start":2, "end":5, "key":"abcd", "value":"abcd"}, {"start":3, "end":5, "key":"bcd", "value":"bcd"}]
     * </pre>
     *
     * @param text 文本段（不为空且长度大于0）
     * @return 返回该文本中包含的所有 keys 及对应 values、与及 key 的起止位置
     */
    List<Found<V>> matchAll(String text);

    /**
     * 包含匹配：输入一段文本，返回文本中包含的 key 及对应的 value 和 key 的起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 keys 和 对应的 values 和 起止位置都返回
     * <p>
     * match：如果文本中的同一起始位置匹配到多个 key，仅返回最长（默认）的那个
     * <p>
     * matchAll：如果文本中的同一起始位置匹配到多个 key，从短到长全部返回。
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *
     *     trie.matchAll("xxabcdexx", true, Integer.MAX_VALUE) ==
     *     [{"start":2, "end":3, "key":"ab", "value":"ab"}, {"start":2, "end":4, "key":"abc", "value":"abc"},
     *     {"start":2, "end":5, "key":"abcd", "value":"abcd"}, {"start":3, "end":5, "key":"bcd", "value":"bcd"}]
     *
     *     trie.matchAll("xxabcdexx", false, Integer.MAX_VALUE) ==
     *     [{"start":2, "end":3, "key":"ab", "value":"ab"}, {"start":2, "end":4, "key":"abc", "value":"abc"},
     *     {"start":2, "end":5, "key":"abcd", "value":"abcd"}]
     *
     *
     *     trie.matchAll("xxabcdexx", true, 1) == [{"start":2, "end":3, "key":"ab", "value":"ab"}]
     *     trie.matchAll("xxabcdexx", false, 1) == [{"start":2, "end":3, "key":"ab", "value":"ab"}]
     *
     * </pre>
     *
     * @param text     文本段（不为空且长度大于0）
     * @param oneByOne 是否逐字符匹配（是：当前下标 + 1开始查找；否：当前下标 + 找到词长度 + 1 开始查找）
     * @param maximum  最大返回结果数量
     * @return 返回该文本中包含的所有 key 及对应 value、与及 key 的起止位置
     */
    List<Found<V>> matchAll(String text, boolean oneByOne, int maximum);

    /**
     * 遍历键（！！慎用：如果树中包含大量的键值对，可能会导致内存溢出！！）
     * <p>
     * 调用此方法需慎重，此方法会将所有遍历得到的 Key 添加到返回的 List中；如果树中存在的键值对数量很多，可能会导致内存溢出，因此一定要限制深度。
     * 当无法判断是否会导致内存溢出时，请使用{@link Trie#traversal}方法
     * <p>
     * {@link ConcurrentArrayTrie#values} 默认采用深度优先遍历和字典序，具体结果执行如下：
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     执行：trie.keys(4)
     *     结果：[ab, abc, abcd, abd, bcd]
     * </pre>
     *
     * @param depth 遍历深度
     * @return 遍历得到的键的集合
     */
    List<String> keys(int depth);


    /**
     * 遍历值（！！慎用：如果树中包含大量的键值对，可能会导致内存溢出！！）
     * <p>
     * 调用此方法需慎重，此方法会将所有遍历得到的 value 添加到返回的 List中；如果树中存在的键值对数量很多，可能会导致内存溢出，因此一定要限制深度。
     * 当无法判断是否会导致内存溢出时，请使用{@link Trie#traversal}方法
     * <p>
     * {@link ConcurrentArrayTrie#values} 默认采用深度优先遍历和字典序，具体结果执行如下：
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     执行：trie.values(4)
     *     结果：[ab, abc, abcd, abd, bcd]
     * </pre>
     *
     * @param depth 遍历深度
     * @return 遍历得到的值的集合
     */
    List<V> values(int depth);

    /**
     * 遍历键值对
     * <p>
     * {@link ConcurrentArrayTrie#traversal(int, BiFunction)} 采用深度优先遍历和字典序
     * <p>
     * 考虑到树中包含的键值对可能数量庞大，因此采用用户提供的函数来处理每一个遍历结果，用户可以自定义函数实现序列化等操作。
     * <p>
     * BiFunction 的入参：(key， value)
     *
     * @param depth    遍历深度
     * @param function 遍历得到的每个键值对会传入给此 function：如果此 function 返回 false，则停止遍历，否则继续遍历。（不能返回空）
     */
    void traversal(int depth, BiFunction<String, V, Boolean> function);

    /**
     * 精确匹配：判断树中是否存在该 Key
     * <p>
     * 等同于 hashmap 的 contains 方法
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.contains("abcd") == true
     * </pre>
     *
     * @param key 键（不为空且长度大于0）
     * @return {@link Boolean#TRUE}：树中存在此 key；{@link Boolean#FALSE}：树中不存在此 key
     */
    boolean contains(String key);

    /**
     * 精确匹配：根据 key 删除 value
     * <p>
     * 等同于 HashMap 的 remove 方法
     * <p>
     * 没有采用惰性删除的方式，不仅仅删除value。如果该 key 有后缀节点，则仅移除 value；如无后缀节点，则删除整个无效分支。
     * <pre>
     * 如当前树中有 gran, grand, grace 三个 key。初始树结构如下：
     *            g
     *            |
     *            r
     *            |
     *            a
     *           / \
     *          c   n(gran)
     *          |   |
     *   (grace)e   d(grand)
     * 1. 删除 gran，gran有后缀节点，因此仅移除 gran 对应的 value，树结构不变。
     *            g
     *            |
     *            r
     *            |
     *            a
     *           / \
     *          c   n
     *          |   |
     *   (grace)e   d(grand)
     * 2. 删除 grace，同时删除无效分支 c，最后仅剩下 grand：
     *            g
     *            |
     *            r
     *            |
     *            a
     *             \
     *              n
     *              |
     *              d(grand)
     * </pre>
     *
     * @param key 键（不为空且长度大于0）
     * @return 如果 key 存在于树中，则删除该 key 并返回该 key 对应的 value；否则，返回空
     */
    V remove(String key);

    /**
     * 树的高度（树中最长的 key 的长度）
     *
     * @return 最长的 key 的长度
     */
    int height();

    /**
     * 树中已有键值对的数量
     *
     * @return 已有键值对数量
     */
    int size();

    /**
     * 树是否为空
     *
     * @return {@link Boolean#TRUE}：没有任何键值对；{@link Boolean#FALSE}：至少有一对键值对
     */
    boolean isEmpty();

    /**
     * 清空所有键值对
     */
    void clear();
}