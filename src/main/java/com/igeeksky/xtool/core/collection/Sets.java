package com.igeeksky.xtool.core.collection;

import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public abstract class Sets {

    private Sets() {
    }

    public static <E> TreeSet<? extends Comparable<E>> newTreeSet() {
        return new TreeSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(int capacity) {
        return new LinkedHashSet<>(capacity);
    }
}
