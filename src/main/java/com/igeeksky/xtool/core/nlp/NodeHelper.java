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


import com.igeeksky.xtool.core.tuple.Tuple2;
import com.igeeksky.xtool.core.tuple.Tuples;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-12
 */
@SuppressWarnings({"unchecked"})
class NodeHelper {

    /**
     * 保存键值对
     *
     * @param root      根节点
     * @param key       key 转换的 char 数组
     * @param newVal    新值
     * @param creator   节点创建器
     * @param convertor 节点转换器
     * @param <V>       值类型
     * @return 如果存在旧值，返回旧值；否则返回空
     */
    public static <V> V put(BaseNode<V> root, String key, V newVal, NodeCreator<V> creator, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        int last = key.length() - 1;
        BaseNode<V> parent = root;
        for (int i = 0; i < last; i++) {
            parent = parent.addChild(key.charAt(i), creator, convertor);
        }

        BaseNode<V> lastNode = parent.addChild(key.charAt(last), creator, convertor);
        V oldVal = lastNode.value;
        lastNode.value = newVal;
        return oldVal;
    }

    /**
     * 精确匹配
     *
     * @param root 根节点
     * @param word 待匹配的字符串
     * @param <V>  值类型
     * @return 匹配得到的节点
     */
    public static <V> BaseNode<V> exactlyMatch(BaseNode<V> root, String word) {
        BaseNode<V> p = root;
        int len = word.length();
        int last = len - 1;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            BaseNode<V> ch = p.findChild(c);
            if (ch == null) {
                return null;
            }
            if (i == last) {
                return ch;
            }
            p = ch;
        }
        return null;
    }

    /**
     * 匹配键（仅返回一个值）
     *
     * @param root         根节点
     * @param word         待匹配的字符串
     * @param start        char 数组的起始匹配位置
     * @param end          char 数组的结束匹配位置
     * @param longestMatch 是否最长匹配
     * @param <V>          值类型
     * @return 匹配得到的节点
     */
    public static <V> Found<V> match(BaseNode<V> root, String word, int start, int end, boolean longestMatch) {
        BaseNode<V> p = root;
        int last = end - 1;
        Found<V> f = null;
        for (int i = start; i < end; i++) {
            BaseNode<V> ch = p.findChild(word.charAt(i));
            if (ch == null) {
                return f;
            }
            if (ch.value != null) {
                // 如果当前节点的值不为空，待返回值置为当前节点的值
                String key = (start == 0 && i == last) ? word : word.substring(start, i + 1);
                f = new Found<>(start, i, key, ch.value);
                // 如为最短匹配，返回当前值；否则继续对剩余字符进行匹配，直至所有字符匹配完毕
                if (!longestMatch) {
                    return f;
                }
            }
            p = ch;
        }
        return f;
    }

    /**
     * 键匹配（返回多个匹配到的值）
     *
     * @param root    根节点
     * @param word    待匹配的字符串
     * @param start   char 数组的起始匹配位置
     * @param end     char 数组的结束匹配位置
     * @param maximum 结果集最大数量
     * @param founds  用于保存结果集
     * @param <V>     值类型
     */
    public static <V> void matchAll(BaseNode<V> root, String word, int start, int end, int maximum, List<Found<V>> founds) {
        BaseNode<V> p = root;
        int last = end - 1;
        for (int i = start; i < end; i++) {
            BaseNode<V> ch = p.findChild(word.charAt(i));
            if (ch == null) {
                return;
            }
            if (ch.value != null) {
                String key = (start == 0 && i == last) ? word : word.substring(start, i + 1);
                founds.add(new Found<>(start, i, key, ch.value));
                // 如果已经达到最大返回数量，返回当前列表；否则继续对剩余字符进行匹配，直至所有字符匹配完毕
                if (founds.size() >= maximum) {
                    return;
                }
            }
            p = ch;
        }
    }

    /**
     * <p>搜索给定节点的全部后缀节点，并将后缀节点包含的所有值返回</p>
     *
     * @param root     待搜索后缀节点的根起始节点（如果起始节点为根节点，那么返回整棵树包含的全部值）
     * @param prefix   前缀
     * @param maxDepth 搜索的最大深度
     * @param dfs      是否采用深度优先搜索
     * @param function 用户自定义操作，其返回值为false时停止遍历
     * @param <V>      值类型
     */
    public static <V> void search(BaseNode<V> root, char[] prefix, int maxDepth, boolean dfs, BiFunction<String, V, Boolean> function) {
        if (dfs) {
            traversal(root, prefix, maxDepth, true, function);
        } else {
            for (int depth = 1; depth <= maxDepth; depth++) {
                boolean remaining = traversal(root, prefix, depth, false, function);
                if (!remaining) {
                    return;
                }
            }
        }
    }

    /**
     * 搜索给定节点的全部后缀节点，并将后缀节点包含的所有值返回
     *
     * @param root  待搜索后缀节点的根起始节点（如果起始节点为根节点，那么返回整棵树包含的全部值）
     * @param depth 搜索的最大深度
     * @param dfs   是否采用深度优先搜索
     * @param <V>   值类型
     * @return 广度优先搜索，是否需要继续搜索下一层（上一次搜索的最深层是否有节点：有，继续搜索下一层；无，结束搜索）
     */
    private static <V> boolean traversal(BaseNode<V> root, char[] prefix, int depth, boolean dfs, BiFunction<String, V, Boolean> function) {
        int threshold = depth - 1, curDep = 0;
        boolean remaining = false;
        Iterator<Node<V>>[] iterators = new Iterator[depth];
        int len = prefix.length;
        char[] chars = Arrays.copyOf(prefix, len + depth);
        iterators[0] = root.iterator();
        while (curDep >= 0) {
            Iterator<Node<V>> it = iterators[curDep];
            if (curDep < threshold) {
                boolean hasChild = false;
                while (it.hasNext()) {
                    Node<V> parent = it.next();
                    int index = len + curDep;
                    chars[index] = parent.c;
                    if (dfs && parent.value != null) {
                        if (!function.apply(String.valueOf(chars, 0, index + 1), parent.value)) {
                            return false;
                        }
                    }
                    if (parent.size() > 0) {
                        iterators[++curDep] = parent.iterator();
                        hasChild = true;
                        break;
                    }
                }
                if (!hasChild) {
                    iterators[curDep--] = null;
                }
            } else {
                remaining = true;
                while (it.hasNext()) {
                    Node<V> node = it.next();
                    if (node.value != null) {
                        int index = len + curDep;
                        chars[index] = node.c;
                        if (!function.apply(String.valueOf(chars, 0, index + 1), node.value)) {
                            return false;
                        }
                    }
                }
                iterators[curDep--] = null;
            }
        }
        return remaining;
    }

    /**
     * 根据 key 删除节点。
     * <p>
     * 如果该 key 有后缀节点，则仅移除 value；如果无后缀节点，则删除距删除节点最近的无效分支。
     * <pre>
     * 如当前树中有 gran, grand, grace 三个 key。初始树结构如下：
     *     g
     *     |
     *     r
     *     |
     *     a
     *    / \
     *   c   n
     *   |   |
     *   e   d
     * 1. 删除gran，仅移除 gran 对应的 value，树结构不变。
     *     g
     *     |
     *     r
     *     |
     *     a
     *    / \
     *   c   n
     *   |   |
     *   e   d
     * 2. 删除 grace，c为无效分支，所以删除，最后仅剩下 grand：
     *     g
     *     |
     *     r
     *     |
     *     a
     *      \
     *       n
     *       |
     *       d
     * </pre>
     *
     * @param root      根节点
     * @param key       待删除的 key
     * @param <V>       值类型
     * @param convertor 节点转换器
     * @return 如果未找到与 key 相符的节点，返回空；否则返回删除的节点。
     */
    public static <V> BaseNode<V> remove(BaseNode<V> root, String key, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        int charsLen = key.length();
        int last = charsLen - 1;
        // del 待删除节点；dp 待删除节点的父节点
        BaseNode<V> p = root, dp = null;
        Node<V> del = null;
        for (int i = 0; i < charsLen; i++) {
            char c = key.charAt(i);
            Node<V> ch = p.findChild(c);
            if (ch == null) {
                return null;
            }
            if (i == last) {
                if (ch.size() == 0) {
                    if (dp == null) {
                        p.deleteChild(ch, convertor);
                    } else {
                        dp.deleteChild(del, convertor);
                    }
                }
                return ch;
            }
            if (ch.size() == 0) {
                return null;
            }
            // 记录无效分支的可能起点
            if (ch.size() == 1 && ch.value == null) {
                if (dp == null) {
                    dp = p;
                    del = ch;
                }
            } else {
                dp = null;
            }
            p = ch;
        }
        return null;
    }

    static class KeyValueCollector<V> implements BiFunction<String, V, Boolean> {

        private final boolean longestMatch;
        private String key;
        private V value;

        public KeyValueCollector(boolean longestMatch) {
            this.longestMatch = longestMatch;
        }

        public String getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public Boolean apply(String key, V value) {
            if (this.key == null) {
                this.key = key;
                this.value = value;
                return longestMatch;
            }
            if (key.length() >= this.key.length()) {
                this.key = key;
                this.value = value;
            }
            return longestMatch;
        }
    }

    static class KeyValuesCollector<V> implements BiFunction<String, V, Boolean> {

        private final int maximum;
        private final List<Tuple2<String, V>> values;

        public KeyValuesCollector(int maximum, List<Tuple2<String, V>> values) {
            this.maximum = maximum;
            this.values = values;
        }

        @Override
        public Boolean apply(String key, V value) {
            values.add(Tuples.of(key, value));
            return values.size() < maximum;
        }
    }

    static class ValuesCollector<V> implements BiFunction<String, V, Boolean> {

        private final int maximum;
        private final List<V> values;

        public ValuesCollector(int maximum, List<V> values) {
            this.maximum = maximum;
            this.values = values;
        }

        @Override
        public Boolean apply(String key, V value) {
            values.add(value);
            return values.size() < maximum;
        }
    }

    static class KeysCollector<V> implements BiFunction<String, V, Boolean> {

        private final int maximum;
        private final List<String> keys;

        public KeysCollector(int maximum, List<String> keys) {
            this.maximum = maximum;
            this.keys = keys;
        }

        @Override
        public Boolean apply(String key, V value) {
            keys.add(key);
            return keys.size() < maximum;
        }
    }
}
