package com.igeeksky.xtool.core.collection;

import java.io.Serial;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/1
 */
public class ConcurrentHashSet<E> extends AbstractSet<E> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8822098000974922850L;

    private final ConcurrentHashMap.KeySetView<E, Boolean> delegate;

    public ConcurrentHashSet() {
        ConcurrentHashMap<E, Boolean> map = new ConcurrentHashMap<>();
        this.delegate = map.keySet(Boolean.TRUE);
    }

    public ConcurrentHashSet(int initialCapacity) {
        ConcurrentHashMap<E, Boolean> map = new ConcurrentHashMap<>(initialCapacity);
        this.delegate = map.keySet(Boolean.TRUE);
    }

    public ConcurrentHashSet(int initialCapacity, float loadFactor) {
        ConcurrentHashMap<E, Boolean> map = new ConcurrentHashMap<>(initialCapacity, loadFactor);
        this.delegate = map.keySet(Boolean.TRUE);
    }

    public ConcurrentHashSet(int initialCapacity, float loadFactor, int concurrencyLevel) {
        ConcurrentHashMap<E, Boolean> map = new ConcurrentHashMap<>(initialCapacity, loadFactor, concurrencyLevel);
        this.delegate = map.keySet(Boolean.TRUE);
    }

    public ConcurrentHashSet(Collection<? extends E> c) {
        ConcurrentHashMap<E, Boolean> map = new ConcurrentHashMap<>((int) Math.ceil(c.size() / 0.75f));
        this.delegate = map.keySet(Boolean.TRUE);
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
        return this.delegate.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.delegate.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return this.delegate.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return this.delegate.remove(o);
    }

    @Override
    public Spliterator<E> spliterator() {
        return this.delegate.spliterator();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return delegate.retainAll(c);
    }

    @Override
    public void clear() {
        this.delegate.clear();
    }

}