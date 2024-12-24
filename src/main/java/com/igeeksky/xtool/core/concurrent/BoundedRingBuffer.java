package com.igeeksky.xtool.core.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 环形缓冲区
 *
 * @author Patrick.Lau
 * @since 1.0.0 2024/12/16
 */
public class BoundedRingBuffer<E> {
    private static final int MAX_CAPACITY = 1 << 30;
    private static final int MIN_CAPACITY = 1 << 4;

    private final E[] table;
    private final int capacity;
    private final int mask;

    private final AtomicLong readCounter = new AtomicLong(0);
    private final AtomicLong writeCounter = new AtomicLong(0);

    /**
     * 创建一个指定容量的环形缓冲区
     *
     * @param cap 容量
     */
    @SuppressWarnings("unchecked")
    public BoundedRingBuffer(int cap) {
        this.capacity = tableSizeFor(cap);
        this.mask = this.capacity - 1;
        table = (E[]) new Object[this.capacity];
    }

    /**
     * 添加元素，如果缓冲区已满，则返回 false
     *
     * @param element 元素
     * @return 是否添加成功
     */
    public boolean offer(E element) {
        long head = readCounter.get();
        long tail = writeCounter.get();
        if (tail - head >= capacity) {
            return false;
        }
        while (!writeCounter.compareAndSet(tail, tail + 1)) {
            head = readCounter.get();
            tail = writeCounter.get();
            if (tail - head >= capacity) {
                return false;
            }
        }
        int index = (int) (tail & mask);
        table[index] = element;
        return true;
    }

    /**
     * 获取并移除缓冲区头部元素，如果缓冲区为空，则返回 null
     *
     * @return 元素
     */
    public E poll() {
        long head = readCounter.get();
        long tail = writeCounter.get();
        if (head == tail) {
            return null;
        }
        while (!readCounter.compareAndSet(head, head + 1)) {
            head = readCounter.get();
            tail = writeCounter.get();
            if (head == tail) {
                return null;
            }
        }
        int index = (int) (head & mask);
        E element = table[index];
        table[index] = null;
        return element;
    }

    /**
     * 获取缓冲区元素数量
     *
     * @return 元素数量
     */
    public int size() {
        return (int) (writeCounter.get() - readCounter.get());
    }

    /**
     * 获取缓冲区读取计数器
     *
     * @return 读取计数器
     */
    public long reads() {
        return readCounter.get();
    }

    /**
     * 获取缓冲区写入计数器
     *
     * @return 写入计数器
     */
    public long writes() {
        return writeCounter.get();
    }

    /**
     * 计算最接近2的次幂的容量
     *
     * @param cap 容量
     * @return 最接近2的次幂的容量
     */
    private static int tableSizeFor(int cap) {
        if (cap <= MIN_CAPACITY) {
            return MIN_CAPACITY;
        }
        if (cap >= MAX_CAPACITY) {
            return MAX_CAPACITY;
        }
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n <= MIN_CAPACITY) ? MIN_CAPACITY : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
    }

}
