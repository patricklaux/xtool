package com.igeeksky.xtool.core.collection;

import java.util.Stack;
import java.util.Vector;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Vectors {

    public <E> Stack<E> newStack() {
        return new Stack<>();
    }

    public <E> Vector<E> newVector() {
        return new Vector<>();
    }

    public <E> Vector<E> newVector(int capacity) {
        return new Vector<>(capacity);
    }

    public <E> Vector<E> newVector(int capacity, int capacityIncrement) {
        return new Vector<>(capacity, capacityIncrement);
    }
}
