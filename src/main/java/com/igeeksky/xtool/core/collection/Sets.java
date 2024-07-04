package com.igeeksky.xtool.core.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/6/21
 */
public class Sets {

    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(int capacity) {
        return new HashSet<>((int) Math.ceil(capacity / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    public static <E> HashSet<E> newHashSet(int capacity, float loadFactor) {
        return new HashSet<>((int) Math.ceil(capacity / loadFactor), loadFactor);
    }

    public static <E> HashSet<E> newHashSet(Collection<? extends E> collection) {
        return new HashSet<>(collection);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(int capacity) {
        return new LinkedHashSet<>((int) Math.ceil(capacity / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(int capacity, float loadFactor) {
        return new LinkedHashSet<>((int) Math.ceil(capacity / loadFactor), loadFactor);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Collection<? extends E> collection) {
        return new LinkedHashSet<>(collection);
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet() {
        return new ConcurrentHashSet<>();
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet(int capacity) {
        return new ConcurrentHashSet<>((int) Math.ceil(capacity / DEFAULT_LOAD_FACTOR), DEFAULT_LOAD_FACTOR);
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet(int capacity, float loadFactor) {
        return new ConcurrentHashSet<>((int) Math.ceil(capacity / loadFactor), loadFactor);
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet(Collection<? extends E> collection) {
        return new ConcurrentHashSet<>(collection);
    }

}