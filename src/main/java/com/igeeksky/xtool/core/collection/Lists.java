package com.igeeksky.xtool.core.collection;

import java.util.ArrayList;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public abstract class Lists {

    private Lists() {
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> newArrayList(int capacity) {
        return new ArrayList<>(capacity);
    }
}
