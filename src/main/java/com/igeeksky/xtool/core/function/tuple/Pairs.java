package com.igeeksky.xtool.core.function.tuple;

/**
 * 键值对工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Pairs {

    private static final Pair<?, ?> EMPTY = new Pair<>(null, null);

    /**
     * 静态工厂：构建 {@link Pair} 对象
     *
     * @param key   键
     * @param value 值
     * @param <K>   键类型
     * @param <V>   值类型
     * @return {@link Pair}
     */
    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }

    /**
     * 静态工厂：返回不包含值的 {@link Pair} 单例对象
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return {@link Pair} -- singleton
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Pair<K, V> emptyPair() {
        return (Pair<K, V>) EMPTY;
    }
}
