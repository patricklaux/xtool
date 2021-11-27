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

import com.igeeksky.xtool.core.annotation.Perfect;
import com.igeeksky.xtool.core.function.tuple.Tuple2;
import com.igeeksky.xtool.core.function.tuple.Tuples;
import com.igeeksky.xtool.core.lang.Assert;
import com.igeeksky.xtool.core.math.IntegerValue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;

/**
 * <p>使用可变数组实现的 Trie </p>
 * 默认使用单链表 和 Avl来解决 hash冲突
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-23
 */
@Perfect
public class ConcurrentArrayTrie<V> implements Trie<V> {

    private volatile int size = 0;
    private volatile int height = 0;
    private final TreeMap<Integer, IntegerValue> heightCache = new TreeMap<>();

    private final NodeCreator<V> creator;
    private final NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor;

    private final Root<V> root = new Root<>('0');

    private final Object lock = new Object();
    private final ReadWriteLock[] locks = new ReadWriteLock[TrieConstants.TABLE_MAX_CAPACITY];

    public ConcurrentArrayTrie() {
        this(new LinkedNodeCreator<>(), new LinkedToAvlConvertor<>());
    }

    public ConcurrentArrayTrie(NodeCreator<V> creator, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        this.creator = creator;
        this.convertor = convertor;
        Arrays.fill(locks, new ReentrantReadWriteLock());
    }

    private Lock getWriteLock(char c) {
        return locks[c & TrieConstants.TABLE_MAX_MASK].writeLock();
    }

    private Lock getReadLock(char c) {
        return locks[c & TrieConstants.TABLE_MAX_MASK].readLock();
    }

    @Override
    public V put(String key, V value) {
        Assert.notNull(value, "value must not be null");
        Assert.hasLength(key, "key must not be null or blank");
        Lock writeLock = getWriteLock(key.charAt(0));
        writeLock.lock();
        try {
            V oldVal = NodeHelper.put(root, key, value, creator, convertor);
            if (null == oldVal) {
                int length = key.length();
                synchronized (lock) {
                    ++size;
                    if (length > height) {
                        height = length;
                    }
                    heightCache.computeIfAbsent(length, lenKey -> new IntegerValue()).increment();
                }
            }
            return oldVal;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void putAll(TreeMap<String, V> map) {
        Assert.notNull(map, "map must not be null");
        map.forEach(this::put);
    }

    @Override
    public V get(String key) {
        Found<V> found = prefixMatch(key, true, true);
        return (found != null) ? found.getValue() : null;
    }

    @Override
    public Tuple2<String, V> prefixMatch(String word) {
        return prefixMatch(word, true);
    }

    @Override
    public Tuple2<String, V> prefixMatch(String word, boolean longestMatch) {
        Found<V> found = prefixMatch(word, false, longestMatch);
        if (found != null) {
            return Tuples.of(found.getKey(), found.getValue());
        }
        return null;
    }

    private Found<V> prefixMatch(String word, boolean exactlyMatch, boolean longestMatch) {
        Assert.hasLength(word, "word must not be null or blank");

        int length = word.length();
        Lock readLock = getReadLock(word.charAt(0));
        readLock.lock();
        try {
            return NodeHelper.match(root, word, 0, length, exactlyMatch, longestMatch);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Tuple2<String, V>> prefixMatchAll(String word) {
        return prefixMatchAll(word, Integer.MAX_VALUE);
    }

    @Override
    public List<Tuple2<String, V>> prefixMatchAll(String word, int maximum) {
        Assert.hasLength(word, "word must not be null or blank");
        int charsLen = word.length();
        List<Found<V>> founds = new LinkedList<>();

        Lock readLock = getReadLock(word.charAt(0));
        readLock.lock();
        try {
            NodeHelper.matchAll(root, word, 0, charsLen, maximum, founds);
        } finally {
            readLock.unlock();
        }

        List<Tuple2<String, V>> result = new LinkedList<>();
        founds.forEach(find -> result.add(Tuples.of(find.getKey(), find.getValue())));
        return result;
    }

    @Override
    public Tuple2<String, V> keyWithPrefix(String prefix) {
        return keyWithPrefix(prefix, true);
    }

    @Override
    public Tuple2<String, V> keyWithPrefix(String prefix, boolean longestMatch) {
        Assert.hasLength(prefix, "prefix must not be null or empty");
        int length = prefix.length();
        int depth = height - length;
        if (depth < 0) {
            return null;
        }
        NodeHelper.KeyValueCollector<V> function = new NodeHelper.KeyValueCollector<>(longestMatch);

        Lock readLock = getReadLock(prefix.charAt(0));
        readLock.lock();
        try {
            Found<V> found = NodeHelper.match(root, prefix, 0, length, true, true);
            if (null == found) {
                return null;
            }
            BaseNode<V> root0 = found.getNode();
            V val = root0.getValue();
            if (val != null) {
                if (!function.apply(prefix, val)) {
                    return Tuples.of(function.getKey(), function.getValue());
                }
            }
            NodeHelper.search(root0, prefix.toCharArray(), depth, false, function);
            String key = function.getKey();
            return (key == null) ? null : Tuples.of(key, function.getValue());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Tuple2<String, V>> keysWithPrefix(String prefix) {
        return keysWithPrefix(prefix, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
    }

    @Override
    public List<Tuple2<String, V>> keysWithPrefix(String prefix, int maximum, int depth, boolean dfs) {
        Assert.hasLength(prefix, "prefix must not be null or empty");
        if (maximum <= 0) {
            return new LinkedList<>();
        }

        int length = prefix.length();
        depth = Math.min(height - length, depth);
        if (depth < 0) {
            return new LinkedList<>();
        }

        List<Tuple2<String, V>> values = new LinkedList<>();
        NodeHelper.KeyValuesCollector<V> function = new NodeHelper.KeyValuesCollector<>(maximum, values);

        Lock readLock = getReadLock(prefix.charAt(0));
        readLock.lock();
        try {
            Found<V> found = NodeHelper.match(root, prefix, 0, length, true, true);
            if (null == found) {
                return values;
            }
            BaseNode<V> root0 = found.getNode();
            V val = root0.getValue();
            if (val != null) {
                if (!function.apply(prefix, val)) {
                    return values;
                }
            }
            if (depth == 0) {
                return values;
            }
            NodeHelper.search(root0, prefix.toCharArray(), depth, dfs, function);
        } finally {
            readLock.unlock();
        }
        return values;
    }

    @Override
    public List<Found<V>> match(String text) {
        return match(text, true, true);
    }

    @Override
    public List<Found<V>> match(String text, boolean longestMatch, boolean oneByOne) {
        Assert.hasLength(text, "text must not be null or empty");

        int length = text.length();
        List<Found<V>> founds = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            Found<V> found;
            Lock readLock = getReadLock(text.charAt(i));
            readLock.lock();
            try {
                found = NodeHelper.match(root, text, i, length, false, longestMatch);
            } finally {
                readLock.unlock();
            }
            if (null != found) {
                founds.add(found);
                if (!oneByOne) {
                    i = found.getEnd();
                }
            }
        }
        return toResult(founds);
    }

    @Override
    public List<Found<V>> matchAll(String text) {
        return matchAll(text, true, Integer.MAX_VALUE);
    }

    @Override
    public List<Found<V>> matchAll(String text, boolean oneByOne, int maximum) {
        Assert.hasLength(text, "text must not be null or empty");

        int charsLen = text.length();
        LinkedList<Found<V>> founds = new LinkedList<>();
        Found<V> last = null;
        for (int i = 0; i < charsLen; i++) {
            Lock readLock = getReadLock(text.charAt(i));
            readLock.lock();
            try {
                NodeHelper.matchAll(root, text, i, charsLen, maximum, founds);
            } finally {
                readLock.unlock();
            }
            int size = founds.size();
            if (size >= maximum) {
                return toResult(founds);
            }
            if (!oneByOne && size > 0) {
                if (founds.getLast() != last) {
                    last = founds.getLast();
                    i = last.getEnd();
                }
            }
        }
        return toResult(founds);
    }

    @Override
    public List<String> keys(int depth) {
        List<String> keys = new LinkedList<>();
        NodeHelper.KeysCollector<V> function = new NodeHelper.KeysCollector<>(Integer.MAX_VALUE, keys);
        traversal(depth, function);
        return keys;
    }

    /**
     * 遍历值：默认采用 深度优先搜索 + 字典序
     * <p>
     * 考虑到大多数的应用场景，因此采用字典序排列（排序过程会带来一些性能消耗）
     * <p>
     * 此方法比较耗时，全局锁会带来较大的性能问题，因此仅执行局部锁定，所以此方法仅支持弱一致性。
     * <pre>
     *     例：树中当前有 a,aa, b,bb,f,ff 六个 key，遍历时会依次顺序锁定首字符 a,b,f。
     *     当遍历 a 及其后缀时，锁定 a，新增或删除 b 为前缀的 key 可以被感知，新增或删除 c,d,e,f…… 等为前缀的 key 可以被感知；
     *     当遍历 b 及其后缀时，释放 a，锁定 b，新增或删除 a 为前缀的 key 无法感知，新增或删除 c,d,e,f…… 等为前缀的 key 可以被感知；
     *     当遍历 f 及其后缀时，释放 b，锁定 f，新增或删除 g,h,i,j……等为前缀的 key 可以被感知。
     * </pre>
     *
     * @return 当前 trie 包含的全部值的集合
     */
    @Override
    public List<V> values(int depth) {
        List<V> values = new LinkedList<>();
        NodeHelper.ValuesCollector<V> function = new NodeHelper.ValuesCollector<>(Integer.MAX_VALUE, values);
        traversal(depth, function);
        return values;
    }

    /**
     * 遍历键值对：默认采用 深度优先搜索 + 字典序
     * <p>
     * 考虑到大多数的应用场景，因此采用字典序排列（排序过程会带来一些性能消耗）
     * <p>
     * 此方法比较耗时，全局锁会带来较大的性能问题，因此仅执行局部锁定，所以此方法仅支持弱一致性。
     * 具体一致性的描述同 {@link ConcurrentArrayTrie#values(int)} 方法
     *
     * @param depth    遍历深度
     * @param function 每个键值对会作为参数调用 function的 apply 方法，如果此 apply 方法返回 false，则停止遍历，否则继续遍历
     */
    @Override
    public void traversal(int depth, BiFunction<String, V, Boolean> function) {
        if (depth < 1 || height < 1) {
            return;
        }
        for (Node<V> next : root) {
            Lock readLock = getReadLock(next.c);
            readLock.lock();
            try {
                depth = Math.min(depth, height);
                if (depth < 1) {
                    return;
                }
                V val = next.getValue();
                if (val != null) {
                    if (!function.apply(String.valueOf(next.c), val)) {
                        return;
                    }
                }
                if (depth < 2) {
                    continue;
                }
                NodeHelper.search(next, new char[]{next.c}, depth - 1, true, function);
            } finally {
                readLock.unlock();
            }
        }
    }

    @Override
    public boolean contains(String key) {
        Found<V> found = prefixMatch(key, true, true);
        if (found != null) {
            return found.getValue() != null;
        }
        return false;
    }

    private List<Found<V>> toResult(List<Found<V>> founds) {
        founds.forEach(Found::setNodeNull);
        return founds;
    }

    @Override
    public V remove(String key) {
        Assert.hasLength(key, "key must not be null or blank");
        Lock writeLock = getWriteLock(key.charAt(0));
        writeLock.lock();
        try {
            BaseNode<V> found = NodeHelper.remove(root, key, convertor);
            if (null != found) {
                V oldVal = found.value;
                if (oldVal != null) {
                    found.value = null;
                    int length = key.length();
                    synchronized (lock) {
                        --size;
                        long heightSize = heightCache.get(length).decrementAndGet();
                        if (heightSize <= 0) {
                            heightCache.remove(length);
                            if (length == height) {
                                Integer lowHeight = heightCache.lowerKey(length);
                                height = (null == lowHeight ? 0 : lowHeight);
                            }
                        }
                    }
                }
                return oldVal;
            }
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public void clear() {
        synchronized (lock) {
            size = 0;
            height = 0;
            heightCache.clear();
            root.reset();
        }
    }
}