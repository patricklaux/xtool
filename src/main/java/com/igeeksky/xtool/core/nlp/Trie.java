package com.igeeksky.xtool.core.nlp;

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
     * @param key   键：不能为空或空白
     * @param value 值：不能为空
     * @return 如果树中包含此 key，则旧值；否则空。
     */
    V put(String key, V value);

    /**
     * 添加多个键值对
     *
     * @param treeMap 键值对集合（键：不能为空或空白；值：不能为空）
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
     * @param key 键(精确匹配)
     * @return 如果树中包含此 key，则 key 对应的 value；否则，空
     */
    V get(String key);

    /**
     * 前缀匹配：使用输入的字符串的前缀去匹配树中已有的 key：如果 key 存在，则返回 key 对应的 value
     * <p>
     * 默认返回最长匹配结果：longestMatch = {@link Boolean#TRUE}
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.match("abcdef") == abcd
     *     依次会匹配到 ab, abc, abcd，默认返回最长匹配结果：abcd
     * </pre>
     *
     * @param word 待匹配词
     * @return 是否匹配到 key，是：返回 key对应的值；否，返回空
     */
    V match(String word);

    /**
     * 前缀匹配：使用输入的字符串的前缀去匹配树中已有的 key
     * <p>
     * 如果 key 存在，则返回 key 对应的 value
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.match("abcdef", true) == abcd
     *     trie.match("abcdef", false) == ab
     *     trie.match("ccc", true) == null
     *     trie.match("ccc", false) == null
     * </pre>
     *
     * @param word         待匹配词
     * @param longestMatch 是否最长匹配；true，匹配到的最长的 Key 对应的 value；false：匹配到的最短的 key 对应的值（默认：{@link Boolean#TRUE}）
     * @return 是否匹配到 key，是：关键字的对应的值；否：空
     */
    V match(String word, boolean longestMatch);

    /**
     * 前缀匹配：使用输入的字符串的前缀去匹配树中已有的 key
     * <p>
     * 默认返回全部结果，maximum = {@link Integer#MAX_VALUE}
     * <p>
     * 如果 key 存在，则返回 key 对应的 value；如果匹配到多个key，那么将这些 key 对应的 value 都返回
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.matchAll("abcdef") == [ab, abc, abcd]
     * </pre>
     *
     * @param word 待匹配文本
     * @return 是否匹配到 key：是，返回这些 key 对应的 values；否，返回空列表
     */
    List<V> matchAll(String word);

    /**
     * 前缀匹配：给定一个字符串，判断该字符串的前缀是否匹配关键字
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.matchAll("abcdef", 1) == [ab]
     *     trie.matchAll("abcdef", 2) == [ab, abc]
     *     trie.matchAll("abcdef", 3) == [ab, abc, abcd]
     * </pre>
     *
     * @param word    待匹配文本
     * @param maximum 最大返回结果数量（默认：{@link Integer#MAX_VALUE}）
     * @return 是否匹配到 key：是，返回这些 key 对应的 values；否，返回空列表
     */
    List<V> matchAll(String word, int maximum);

    /**
     * 前缀匹配：输入前缀，返回以有此前缀的 key 对应的 value，如果有多个 key 都有此前缀，将这些 key 对应的 value 都返回
     * <p>
     * 默认返回全部结果，maximum = {@link Integer#MAX_VALUE}
     * <p>
     * 默认最大搜索深度：depth = {@link Integer#MAX_VALUE}
     * <p>
     * 默认深度优先遍历：dfs = {@link Boolean#TRUE}
     * <p>
     * 此方法等同于：trie.search("ab", Integer.MAX_VALUE, Integer.MAX_VALUE, true)
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.search("ab")，结果：[ab, abc, abcd, abd]；
     * </pre>
     *
     * @param prefix 前缀
     * @return 所有包含给定前缀的关键字对应的值。
     */
    List<V> search(String prefix);

    /**
     * 前缀匹配：输入前缀，返回以此前缀开头的 key 对应的 value，如果有多个 key都包含此前缀，将这些 key 对应的 value 都返回。
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.search("ab", 1000, 0, true); 结果：[ab]；
     *     trie.search("ab", 1000, 0, false); 结果：[ab]
     *
     *     trie.search("ab", 1000, 1, true); 结果：[ab, abc, abd]
     *     trie.search("ab", 1000, 1, false); 结果：[ab, abc, abd]
     *
     *     trie.search("ab", 1000, 2, true); 结果：[ab, abc, abcd, abd]
     *     trie.search("ab", 1000, 2, false); 结果：[ab, abc, abd, abcd]
     *
     *     trie.search("ab", 1000, 3, true); 结果：[ab, abc, abcd, abd]
     *     trie.search("ab", 1000, 3, false); 结果：[ab, abc, abd, abcd]
     *
     *     trie.search("ab", 3, 3, true); 结果：[ab, abc, abcd]
     *     trie.search("ab", 3, 3, false); 结果：[ab, abc, abd]
     * </pre>
     *
     * @param prefix  前缀
     * @param maximum 最大返回结果数量（默认：{@link Integer#MAX_VALUE}）
     * @param depth   搜索深度（默认：{@link Integer#MAX_VALUE}）
     * @param dfs     是否深度优先遍历（true，深度优先遍历；false，广度优先遍历；默认：{@link Boolean#TRUE}）
     * @return 所有包含给定前缀的关键字对应的值。
     */
    List<V> search(String prefix, int maximum, int depth, boolean dfs);

    /**
     * 包含匹配：输入一段文本，返回该文本中包含的 key 对应的 value 和 key 的起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 key 对应的所有 value 和 起止位置都返回；
     * <p>
     * 如果文本段中的同一起始位置匹配到多个 key，默认仅返回最长的那个。
     * <p>
     * 默认最长匹配：longestMatch={@link Boolean#TRUE}
     * <p>
     * 默认逐字符查找：oneByOne = {@link Boolean#TRUE}
     * <p>
     * 等同于：trie.contains("xxabcdexx", true, true)
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.contains("xxabcdexx") == [{"start":2, "end":5, "value":"abcd"}, {"start":3, "end":5, "value":"bcd"}]
     * </pre>
     *
     * @param text 文本段
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> contains(String text);

    /**
     * 包含匹配：给定一个文本段，查找文本中包含的所有键对应的值
     *
     * <pre>
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *
     *     trie.contains("xxabcdexx", true, true) ==
     *     [{"start":2, "end":5, "value":"abcd"}, {"start":3, "end":5, "value":"bcd"}]
     *
     *     trie.contains("xxabcdexx", true, false) ==
     *     [{"start":2, "end":5, "value":"abcd"}]
     *
     *     trie.contains("xxabcdexx", false, true) ==
     *     [{"start":2, "end":3, "value":"ab"}, {"start":3, "end":5, "value":"bcd"}]
     *
     *     trie.contains("xxabcdexx", false, false) ==
     *     [{"start":2, "end":3, "value":"ab"}]
     * </pre>
     *
     * @param text         文本段
     * @param longestMatch 是否最长匹配（默认：true 最长匹配）
     * @param oneByOne     是否逐字符匹配（是：当前下标 + 1开始查找；否：当前下标 + 找到词长度 + 1 开始查找）
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> contains(String text, boolean longestMatch, boolean oneByOne);

    /**
     * 包含匹配：输入一段文本，返回文本中包含的 key 对应的 value 和起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 key 对应的所有 value 和 起止位置都返回；
     * <p>
     * contains：如果文本中的同一起始位置匹配到多个 key，仅返回最长（默认）的那个；
     * <p>
     * containsAll：如果文本中的同一起始位置匹配到多个 key，从短到长全部返回。
     * <p>
     * 默认不跳过长度，逐字符查找：oneByOne = {@link Boolean#TRUE}；
     * <p>
     * 默认返回全部结果，maximum={@link Integer#MAX_VALUE}
     * <p>
     * 等同于：trie.containsAll("xxabcdexx", true, Integer.MAX_VALUE)
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     trie.containsAll("xxabcdexx") ==
     *     [{"start":2, "end":3, "value":"ab"}, {"start":2, "end":4, "value":"abc"},
     *     {"start":2, "end":5, "value":"abcd"}, {"start":3, "end":5, "value":"bcd"}]
     * </pre>
     *
     * @param text 文本段
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> containsAll(String text);

    /**
     * 包含匹配：输入一段文本，返回文本中包含的 key 对应的 value 和起止位置
     * <p>
     * 如果文本中包含有多个 key，那么将这些 key 对应的所有 value 和 起止位置都返回
     * <p>
     * contains：如果文本中的同一起始位置匹配到多个 key，仅返回最长（默认）的那个
     * <p>
     * containsAll：如果文本中的同一起始位置匹配到多个 key，从短到长全部返回。
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *
     *     trie.containsAll("xxabcdexx", true, Integer.MAX_VALUE) ==
     *     [{"start":2, "end":3, "value":"ab"}, {"start":2, "end":4, "value":"abc"},
     *     {"start":2, "end":5, "value":"abcd"}, {"start":3, "end":5, "value":"bcd"}]
     *
     *     trie.containsAll("xxabcdexx", false, Integer.MAX_VALUE) ==
     *     [{"start":2, "end":3, "value":"ab"}, {"start":2, "end":4, "value":"abc"},
     *     {"start":2, "end":5, "value":"abcd"}]
     *
     *
     *     trie.containsAll("xxabcdexx", true, 1) == [{"start":2, "end":3, "value":"ab"}]
     *     trie.containsAll("xxabcdexx", false, 1) == [{"start":2, "end":3, "value":"ab"}]
     *
     * </pre>
     *
     * @param text     文本段
     * @param oneByOne 是否逐字符匹配（是：当前下标 + 1开始查找；否：当前下标 + 找到词长度 + 1 开始查找）
     * @param maximum  最大返回结果数量
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> containsAll(String text, boolean oneByOne, int maximum);

    /**
     * 遍历值
     * <p>
     * {@link ConcurrentHashTrie#values} 默认采用深度优先遍历和字典序，具体结果执行如下：
     *
     * <pre>
     *     例：
     *     Trie中已有：ab, abc, abcd, abd, bcd
     *     执行：trie.values(4)
     *     结果：[ab, abc, abcd, abd, bcd]
     * </pre>
     *
     * @param depth 遍历深度
     * @return 全部值的集合
     */
    List<V> values(int depth);

    /**
     * 遍历键值对
     * <p>
     * {@link ConcurrentHashTrie#traversal(int, BiFunction)} 采用深度优先遍历和字典序
     * <p>
     * 考虑到树中包含的键值对可能数量庞大，如果提供 entrySet()方法会消耗大量的内存，甚至会导致内存溢出，
     * 因此采用用户提供的函数来处理遍历结果，用户可以自定义函数实现序列化等操作。
     * <p>
     * BiFunction 的入参：(key， value)
     *
     * @param depth    遍历深度
     * @param function 每个键值对会传入给该 function：如果该 function 返回 false，则停止遍历，否则继续遍历
     */
    void traversal(int depth, BiFunction<String, V, Boolean> function);

    /**
     * 完全匹配：根据 key 删除 value
     * <p>
     * 等同于 HashMap 的 remove 方法
     * <p>
     * 如果该 key 有后缀节点，则仅移除 value；如无后缀节点，则删除无效分支。
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
     *          c   n()
     *          |   |
     *   (grace)e   d(grand)
     * 2. 删除 grace，同时删除无效分支 c，最后仅剩下 grand：
     *            g
     *            |
     *            r
     *            |
     *            a
     *             \
     *              n()
     *              |
     *              d(grand)
     * </pre>
     *
     * @param key 键：完全匹配的字符串
     * @return 如果 key 存在于树中，则返回该 key 对应的 value；否则，返回空。
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
     * @return true：没有任何键值对；false：至少有一对键值对
     */
    boolean isEmpty();

    /**
     * 清空所有键值对
     */
    void clear();
}