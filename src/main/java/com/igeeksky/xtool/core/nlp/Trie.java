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
     * 完全匹配：通过 Key 获取 value（给定的 key 需与树中已有的 key 完全相同，等同于 hashmap 的 get 方法）
     *
     * @param key 键(完全匹配)
     * @return 如果树中包含此 key，则 key 对应的 value；否则，空
     */
    V get(String key);

    /**
     * 前缀匹配：给定一个字符串，使用其前缀匹配树中已有的 key（默认最长匹配）
     *
     *
     * <pre>
     * 例：
     * Trie中已有：ab,abc,abd
     * 执行：trie.match("abcd")，结果：abc；
     * 注：前缀匹配依次会匹配到 ab 和 abc，默认最长匹配结果：abc
     * </pre>
     *
     * @param word 待匹配词
     * @return 是否匹配到 key，是：关键字的对应的值；否：空
     */
    V match(String word);

    /**
     * 前缀匹配：给定一个字符串，使用其前缀匹配树中已有的 key
     *
     * <pre>
     * 例：
     * Trie中已有：ab,abc,abd
     *
     * 执行：trie.match("abcd", false)，结果：ab ；
     * 执行：trie.match("abcd", true)，结果：abc ；
     *
     * 执行：trie.match("acd", false)，结果：null；
     * 执行：trie.match("acd", true)，结果：null
     * </pre>
     *
     * @param word         待匹配词
     * @param longestMatch 是否最长匹配；true，匹配到的最长的关键字对应的值；false：匹配到的最短的关键字对应的值;
     * @return 是否匹配到 key，是：关键字的对应的值；否：空
     */
    V match(String word, boolean longestMatch);

    /**
     * 前缀匹配：给定一个字符串，判断该字符串的前缀是否匹配关键字（默认全部）
     *
     * <pre>
     * 例：
     * Trie中已有：ab,abc,abd
     *
     * 执行：trie.matchAll("abcd")，结果：[ab, abc]；
     * </pre>
     *
     * @param word 待匹配文本
     * @return 是否匹配关键字，是：所有关键字的对应的值；否：空
     */
    List<V> matchAll(String word);

    /**
     * 前缀匹配：给定一个字符串，判断该字符串的前缀是否匹配关键字
     *
     * <pre>
     * 例：
     * Trie中已有：ab,abc,abd
     *
     * 执行：trie.matchAll("abcd", 1)，结果：[ab]；
     *
     * 执行：trie.matchAll("abcd", 2)，结果：[ab, abc]；
     *
     * 执行：trie.matchAll("abcd", 3)，结果：[ab, abc]
     * </pre>
     *
     * @param word    待匹配文本
     * @param maximum 匹配的最大数量
     * @return 是否匹配关键字，是：所有关键字的对应的值；否：空。
     */
    List<V> matchAll(String word, int maximum);

    /**
     * 前缀匹配：给定前缀，所有包含此前缀的关键字对应的值
     * <p>
     * 默认返回全部结果，maximum={@link Integer#MAX_VALUE}；默认最大深度：depth={@link Integer#MAX_VALUE}；默认深度优先遍历dfs={@link Boolean#TRUE}
     * <p>
     * 此方法等同于：trie.search("ab", Integer.MAX_VALUE, Integer.MAX_VALUE, true)
     *
     * <pre>
     * 例：
     * Trie中已有：ab, abc, abcd, abd, bcd
     *
     * 执行：trie.search("ab")，结果：[ab, abc, abcd, abd]；
     * </pre>
     *
     * @param prefix 前缀
     * @return 所有包含给定前缀的关键字对应的值。
     */
    List<V> search(String prefix);

    /**
     * 前缀匹配：给定前缀，所有包含此前缀的关键字对应的值
     *
     * <pre>
     * 例：
     * Trie中已有：ab, abc, abcd, abd, bcd
     *
     * 执行：trie.search("ab", 1000, 0, true); 结果：[ab]；
     * 执行：trie.search("ab", 1000, 0, false); 结果：[ab]；
     *
     * 执行：trie.search("ab", 1000, 1, true); 结果：[ab, abc, abd]；
     * 执行：trie.search("ab", 1000, 1, false); 结果：[ab, abc, abd]；
     *
     * 执行：trie.search("ab", 1000, 2, true); 结果：[ab, abc, abcd, abd]；
     * 执行：trie.search("ab", 1000, 2, false); 结果：[ab, abc, abd, abcd]；
     *
     * 执行：trie.search("ab", 1000, 3, true); 结果：[ab, abc, abcd, abd]；
     * 执行：trie.search("ab", 1000, 3, false); 结果：[ab, abc, abd, abcd]
     *
     * 执行：trie.search("ab", 3, 3, true); 结果：[ab, abc, abcd]；
     * 执行：trie.search("ab", 3, 3, false); 结果：[ab, abc, abd]
     *
     * </pre>
     *
     * @param prefix  前缀
     * @param maximum 的最大数量
     * @param depth   搜索深度
     * @param dfs     true，深度优先遍历；false，广度优先遍历
     * @return 所有包含给定前缀的关键字对应的值。
     */
    List<V> search(String prefix, int maximum, int depth, boolean dfs);

    /**
     * 包含匹配：给定一个文本段，查找文本中包含的所有键对应的值。
     * <p>
     * 默认最短匹配：longestMatch={@link Boolean#FALSE}；默认不跳过长度，逐字符查找：skipFound = {@link Boolean#FALSE}
     * <p>
     * 此方法等同于：trie.contains("abcdefg", false, false)
     * <pre>
     * 例：
     * Trie中已有：ab, bc, cd, ef, efg
     *
     * 执行：trie.contains("abcdefg");
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":1, "end":2, "value":"bc"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":5, "value":"ef"}]；
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
     * 例：
     * Trie中已有：ab, bc, cd, ef, efg
     *
     * 执行：trie.contains("abcdefg", false, false);
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":1, "end":2, "value":"bc"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":5, "value":"ef"}]
     *
     * 执行：trie.contains("abcdefg", true, false);
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":1, "end":2, "value":"bc"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":6, "value":"efg"}]
     *
     * 执行：trie.contains("abcdefg", false, true);
     * [{"start":0, "end":1, "value":"ab"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":5, "value":"ef"}]
     *
     * 执行：trie.contains("abcdefg", true, true);
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":6, "value":"efg"}]
     * </pre>
     *
     * @param text         文本段
     * @param longestMatch 是否最长匹配
     * @param skipFound    是否跳过已匹配的范围（是：从当前下标+找到词长度+1开始查找；否，从 当前下标+1开始查找）
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> contains(String text, boolean longestMatch, boolean skipFound);

    /**
     * 包含匹配：给定一个文本段，查找文本中包含的所有键对应的值
     * <p>
     * 默认不跳过长度，逐字符查找：skipFound = {@link Boolean#FALSE}；默认返回全部结果，maximum={@link Integer#MAX_VALUE}
     * <p>
     * 此方法等同于：trie.containsAll("abcdefg", false, Integer.MAX_VALUE)
     *
     * <pre>
     * 例：
     * Trie中已有：ab, bc, cd, ef, efg
     *
     * 执行：trie.containsAll("abcdefg");
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":1, "end":2, "value":"bc"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":5, "value":"ef"}, {"start":4, "end":6, "value":"efg"}]
     *
     * containsAll 与 contains 的不同：containsAll 会将所有同一起点匹配到的 value 返回。
     * 如 contains("abcdefg") 仅返回 [..., ..., {"start":4, "end":5, "value":"ef"}]；
     * 而 containsAll("abcdefg") 则会返回 [..., ..., {"start":4, "end":5, "value":"ef"}, {"start":4, "end":6, "value":"efg"}]
     * </pre>
     *
     * @param text 文本段
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> containsAll(String text);

    /**
     * 包含匹配：给定一个文本段，查找文本中包含的所有键对应的值
     *
     * <pre>
     * 例：
     * Trie中已有：ab, bc, cd, ef, efg
     *
     * 执行：trie.containsAll("abcdefg", false, Integer.MAX_VALUE);
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":1, "end":2, "value":"bc"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":5, "value":"ef"}, {"start":4, "end":6, "value":"efg"}]
     *
     * 执行：trie.containsAll("abcdefg", true, Integer.MAX_VALUE);
     * 结果：[{"start":0, "end":1, "value":"ab"}, {"start":2, "end":3, "value":"cd"}, {"start":4, "end":5, "value":"ef"}, {"start":4, "end":6, "value":"efg"}]
     *
     * </pre>
     *
     * @param text      文本段
     * @param skipFound 是否跳到已查找到词的下一个字符
     * @param maximum   最大匹配数量
     * @return 文本段包含的所有关键字对应的值的集合
     */
    List<Found<V>> containsAll(String text, boolean skipFound, int maximum);

    /**
     * 获取全部值
     * <p>
     * {@link ConcurrentHashTrie#values(int)} 采用的是深度优先遍历和字典序，具体结果执行如下：
     *
     * <pre>
     *     例：
     *     Trie中已有：a, ab, ach, aci, adjn, aek, afl, afmo, ag
     *     执行：trie.values(4)
     *     结果：[a, ab, ach, aci, adjn, aek, afl, afmo, ag]
     * </pre>
     *
     * @param depth 遍历深度
     * @return 全部值的集合
     */
    List<V> values(int depth);

    /**
     * 键值对遍历
     * <p>
     * {@link ConcurrentHashTrie#traversal(int, BiFunction)} 采用深度优先遍历和字典序
     * <p>
     * 考虑到树中包含的键值对可能数量庞大，如果提供 entrySet() 方法会消耗大量的内存，因此提供遍历扩展，用户可以采用此方法进行序列化等操作。
     * <p>
     * BiFunction 的入参：(key， value)
     *
     * @param depth    遍历深度
     * @param function 每个键值对会传入给该 function：如果该 function 返回 false，则停止遍历，否则继续遍历
     */
    void traversal(int depth, BiFunction<String, V, Boolean> function);

    /**
     * 完全匹配：移除 key 对应的 value（等同于 HashMap 的 remove 方法）
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
     * @return 如果树中包含此 key，则该 key 对应的值；否则空。
     */
    V remove(String key);

    /**
     * 当前Trie树中 key的最大长度
     *
     * @return 树的高度（最长的 key 的 length）
     */
    int height();

    /**
     * 当前Trie树中 包含的key数量
     *
     * @return 包含的key数量
     */
    int size();

    /**
     * 当前Trie树是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 清空所有键值对
     */
    void clear();
}