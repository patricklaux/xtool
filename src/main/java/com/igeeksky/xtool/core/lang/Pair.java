package com.igeeksky.xtool.core.lang;

import com.igeeksky.xtool.core.annotation.ParameterNames;

import java.io.Serializable;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Pair<K, V> implements Serializable {

    private final K key;
    private final V value;

    @ParameterNames({"key", "value"})
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean hasValue() {
        return null != value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (getKey() != null ? !getKey().equals(pair.getKey()) : pair.getKey() != null) {
            return false;
        }
        return getValue() != null ? getValue().equals(pair.getValue()) : pair.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = getKey() != null ? getKey().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"key\":\"" + key + "\", \"value\":\"" + value + "\"}";
    }
}
