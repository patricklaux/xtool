package com.igeeksky.xtool.core.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/6/21
 */
public final class Sets {

    private static final double DEFAULT_LOAD_FACTOR = 0.75D;

    private Sets() {
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(int expectedSize) {
        return new HashSet<>(calculateCapacity(expectedSize));
    }

    public static <E> HashSet<E> newHashSet(Collection<? extends E> c) {
        return new HashSet<>(c);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(int expectedSize) {
        return new LinkedHashSet<>(calculateCapacity(expectedSize));
    }

    private static int calculateCapacity(int expectedSize) {
        return (int) Math.ceil(expectedSize / DEFAULT_LOAD_FACTOR);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Collection<? extends E> c) {
        return new LinkedHashSet<>(c);
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet() {
        return new ConcurrentHashSet<>();
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet(int expectedSize) {
        return new ConcurrentHashSet<>(calculateCapacity(expectedSize));
    }

    public static <E> ConcurrentHashSet<E> newConcurrentHashSet(Collection<? extends E> c) {
        return new ConcurrentHashSet<>(c);
    }

}