package com.igeeksky.xtool.core.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/1
 */
public class ConcurrentHashSet<E> extends AbstractSet<E> {

    private final ConcurrentMap<E, Boolean> delegate;

    public ConcurrentHashSet() {
        this.delegate = new ConcurrentHashMap<>();
    }

    public ConcurrentHashSet(int initialCapacity) {
        this.delegate = new ConcurrentHashMap<>(initialCapacity);
    }

    public ConcurrentHashSet(int initialCapacity, float loadFactor) {
        this.delegate = new ConcurrentHashMap<>(initialCapacity, loadFactor);
    }

    public ConcurrentHashSet(int initialCapacity, float loadFactor, int concurrencyLevel) {
        this.delegate = new ConcurrentHashMap<>(initialCapacity, loadFactor, concurrencyLevel);
    }

    public ConcurrentHashSet(Collection<? extends E> c) {
        this.delegate = new ConcurrentHashMap<>((int) Math.ceil(c.size() / 0.75f));
        addAll(c);
    }

    @Override
    public int size() {
        return this.delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.delegate.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return this.delegate.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.delegate.keySet().toArray(a);
    }

    @Override
    public boolean add(E e) {
        return this.delegate.put(e, Boolean.TRUE) == null;
    }

    @Override
    public boolean remove(Object o) {
        return this.delegate.remove(o) != null;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<Map.Entry<E, Boolean>> it = this.delegate.entrySet().iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next().getKey())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        this.delegate.clear();
    }

}