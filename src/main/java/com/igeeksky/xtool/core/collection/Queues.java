package com.igeeksky.xtool.core.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public abstract class Queues {

    private Queues() {
    }

    public <E> ArrayBlockingQueue<E> newArrayBlockingQueue(int capacity) {
        return new ArrayBlockingQueue<>(capacity);
    }

    public <E> LinkedBlockingDeque<E> newLinkedBlockingDeque() {
        return new LinkedBlockingDeque<>();
    }

    public <E> LinkedBlockingDeque<E> newLinkedBlockingDeque(int capacity) {
        return new LinkedBlockingDeque<>(capacity);
    }
}
