package com.igeeksky.xtool.core.nlp;

import com.igeeksky.xtool.core.annotation.Perfect;
import com.igeeksky.xtool.core.lang.Assert;
import com.igeeksky.xtool.core.lang.IntegerValue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;

/**
 * <p>使用 Hash 实现的 Trie 树</p>
 * 默认使用单链表 和 Avl来解决 hash冲突
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-23
 */
@Perfect
public class ConcurrentHashTrie<V> implements Trie<V> {

    private volatile int size = 0;
    private volatile int height = 0;
    private final TreeMap<Integer, IntegerValue> heightCache = new TreeMap<>();

    private final NodeCreator<V> creator;
    private final NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor;

    private final Root<V> root = new Root<>('0');

    private final Object lock = new Object();
    private final ReadWriteLock[] locks = new ReadWriteLock[TrieConstants.TABLE_MAX_CAPACITY];

    public ConcurrentHashTrie() {
        this(new LinkedNodeCreator<>(), new LinkedToAvlConvertor<>());
    }

    public ConcurrentHashTrie(NodeCreator<V> creator, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
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
        char[] chars = key.toCharArray();
        Lock writeLock = getWriteLock(chars[0]);
        writeLock.lock();
        try {
            V oldVal = NodeHelper.put(root, chars, value, creator, convertor);
            if (null == oldVal) {
                int charsLen = chars.length;
                synchronized (lock) {
                    ++size;
                    if (charsLen > height) {
                        height = charsLen;
                    }
                    heightCache.computeIfAbsent(charsLen, lenKey -> new IntegerValue()).increment();
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
        Found<V> found = match(key, true, true);
        return (found != null) ? found.getValue() : null;
    }

    @Override
    public V match(String word) {
        return match(word, true);
    }

    @Override
    public V match(String word, boolean longestMatch) {
        Found<V> found = match(word, false, longestMatch);
        return (found != null) ? found.getValue() : null;
    }

    private Found<V> match(String word, boolean exactlyMatch, boolean longestMatch) {
        Assert.hasLength(word, "word must not be null or blank");

        char[] chars = word.toCharArray();
        int charsLen = chars.length;

        Lock readLock = getReadLock(chars[0]);
        readLock.lock();
        try {
            return NodeHelper.match(root, chars, 0, charsLen, exactlyMatch, longestMatch);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<V> matchAll(String word) {
        return matchAll(word, Integer.MAX_VALUE);
    }

    @Override
    public List<V> matchAll(String word, int maximum) {
        Assert.hasLength(word, "word must not be null or blank");
        char[] chars = word.toCharArray();
        int charsLen = chars.length;

        List<Found<V>> founds = new LinkedList<>();

        Lock readLock = getReadLock(chars[0]);
        readLock.lock();
        try {
            NodeHelper.matchAll(root, chars, 0, charsLen, maximum, founds);
        } finally {
            readLock.unlock();
        }

        List<V> result = new LinkedList<>();
        founds.forEach(find -> result.add(find.getValue()));
        return result;
    }

    @Override
    public List<V> search(String prefix) {
        return search(prefix, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
    }

    @Override
    public List<V> search(String prefix, int maximum, int depth, boolean dfs) {
        Assert.hasLength(prefix, "prefix must not be null or empty");
        if (maximum <= 0) {
            return new LinkedList<>();
        }

        char[] chars = prefix.toCharArray();
        int charsLen = chars.length;

        depth = Math.min(height - charsLen, depth);
        if (depth < 0) {
            return new LinkedList<>();
        }

        List<V> values = new LinkedList<>();
        NodeHelper.ValuesFunction<V> function = new NodeHelper.ValuesFunction<>(maximum, values);

        Lock readLock = getReadLock(chars[0]);
        readLock.lock();
        try {
            Found<V> found = NodeHelper.match(root, chars, 0, charsLen, true, true);
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
            NodeHelper.search(root0, chars, depth, dfs, function);
        } finally {
            readLock.unlock();
        }
        return values;
    }

    @Override
    public List<Found<V>> contains(String text) {
        return contains(text, false, false);
    }

    @Override
    public List<Found<V>> contains(String text, boolean longestMatch, boolean skipFound) {
        Assert.hasLength(text, "text must not be null or empty");
        char[] chars = text.toCharArray();
        int charsLen = chars.length;
        List<Found<V>> founds = new LinkedList<>();
        for (int i = 0; i < charsLen; i++) {
            Found<V> found;
            Lock readLock = getReadLock(chars[i]);
            readLock.lock();
            try {
                found = NodeHelper.match(root, chars, i, charsLen, false, longestMatch);
            } finally {
                readLock.unlock();
            }
            if (null != found) {
                founds.add(found);
                if (skipFound) {
                    i = found.getEnd();
                }
            }
        }
        return toResult(founds);
    }

    @Override
    public List<Found<V>> containsAll(String text) {
        return containsAll(text, false, Integer.MAX_VALUE);
    }

    @Override
    public List<Found<V>> containsAll(String text, boolean skipFound, int maximum) {
        Assert.hasLength(text, "text must not be null or empty");
        char[] chars = text.toCharArray();
        int charsLen = chars.length;

        LinkedList<Found<V>> founds = new LinkedList<>();
        Found<V> last = null;
        for (int i = 0; i < charsLen; i++) {
            Lock readLock = getReadLock(chars[i]);
            readLock.lock();
            try {
                NodeHelper.matchAll(root, chars, i, charsLen, maximum, founds);
            } finally {
                readLock.unlock();
            }
            int size = founds.size();
            if (size >= maximum) {
                return toResult(founds);
            }
            if (skipFound && size > 0) {
                if (founds.getLast() != last) {
                    last = founds.getLast();
                    i = last.getEnd();
                }
            }
        }
        return toResult(founds);
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
        NodeHelper.ValuesFunction<V> function = new NodeHelper.ValuesFunction<>(Integer.MAX_VALUE, values);
        traversal(depth, function);
        return values;
    }

    /**
     * 遍历键值对：默认采用 深度优先搜索 + 字典序
     * <p>
     * 考虑到大多数的应用场景，因此采用字典序排列（排序过程会带来一些性能消耗）
     * <p>
     * 此方法比较耗时，全局锁会带来较大的性能问题，因此仅执行局部锁定，所以此方法仅支持弱一致性。
     * 具体一致性的描述同 {@link ConcurrentHashTrie#values(int)} 方法
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

    private List<Found<V>> toResult(List<Found<V>> founds) {
        founds.forEach(Found::setNodeNull);
        return founds;
    }

    @Override
    public V remove(String key) {
        Assert.hasLength(key, "key must not be null or blank");
        char[] chars = key.toCharArray();

        Lock writeLock = getWriteLock(chars[0]);
        writeLock.lock();
        try {
            BaseNode<V> found = NodeHelper.remove(root, chars, convertor);
            if (null != found) {
                V oldVal = found.value;
                if (oldVal != null) {
                    found.value = null;
                    int charsLen = chars.length;
                    synchronized (lock) {
                        --size;
                        long heightSize = heightCache.get(charsLen).decrementAndGet();
                        if (heightSize <= 0) {
                            heightCache.remove(charsLen);
                            if (charsLen == height) {
                                Integer lowHeight = heightCache.lowerKey(charsLen);
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