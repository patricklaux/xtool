package com.igeeksky.xtool.core.function.tuple;

import com.igeeksky.xtool.core.annotation.ParameterNames;

import java.util.function.Function;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/8/13
 */
public class ExpiryKeyValue<K, V> extends KeyValue<K, V> {

    private final long ttl;

    /**
     * @param key   键
     * @param value 值
     * @param ttl   存活时长
     */
    @ParameterNames({"key", "value", "ttl"})
    public ExpiryKeyValue(K key, V value, long ttl) {
        super(key, value);
        this.ttl = ttl;
    }

    /**
     * @return time to live
     */
    public long getTtl() {
        return ttl;
    }

    @Override
    public <R> ExpiryKeyValue<R, V> mapKey(Function<K, R> mapper) {
        return new ExpiryKeyValue<>(mapper.apply(getKey()), getValue(), ttl);
    }

    @Override
    public <R> ExpiryKeyValue<K, R> mapValue(Function<V, R> mapper) {
        return new ExpiryKeyValue<>(getKey(), mapper.apply(getValue()), ttl);
    }

    @Override
    public <K1, V1> ExpiryKeyValue<K1, V1> map(Function<K, K1> keyMapper, Function<V, V1> valueMapper) {
        return new ExpiryKeyValue<>(keyMapper.apply(getKey()), valueMapper.apply(getValue()), ttl);
    }

    public <K1, V1> ExpiryKeyValue<K1, V1> map(Function<K, K1> keyMapper, Function<V, V1> valueMapper, long ttl) {
        return new ExpiryKeyValue<>(keyMapper.apply(getKey()), valueMapper.apply(getValue()), ttl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpiryKeyValue<?, ?> that)) return false;
        if (!super.equals(o)) return false;

        return getTtl() == that.getTtl();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Long.hashCode(getTtl());
        return result;
    }

    @Override
    public String toString() {
        K key = getKey();
        V val = getValue();
        return "{" + "\"key\":"
                + (null != key ? ((key instanceof String ? ("\"" + key + "\"") : key)) : "")
                + ", \"value\":"
                + (null != val ? ((val instanceof String ? ("\"" + val + "\"") : val)) : "")
                + ", \"ttl\":" + ttl
                + "}";
    }

}