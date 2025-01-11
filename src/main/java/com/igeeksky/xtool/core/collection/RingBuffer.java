package com.igeeksky.xtool.core.collection;

import com.igeeksky.xtool.core.lang.Assert;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Consumer;

/**
 * 环形缓冲区
 *
 * @author Patrick.Lau
 * @since 1.1.1
 */
public class RingBuffer<E> {

    private static final int MAXIMUM_SIZE = 1 << 30;
    private static final int MINIMUM_SIZE = 1 << 4;

    private final int cap;
    private final int mask;

    private final AtomicReferenceArray<E> buffer;
    private final AtomicLong readCounter = new AtomicLong(0);
    private final AtomicLong writeCounter = new AtomicLong(0);

    /**
     * 创建一个固定容量的环形缓冲区
     *
     * @param capacity 固定容量（如非 2的幂，则自动调整为最接近的2的幂）
     */
    public RingBuffer(int capacity) {
        this.cap = tableSizeFor(capacity);
        this.mask = this.cap - 1;
        this.buffer = new AtomicReferenceArray<>(this.cap);
    }

    /**
     * 添加元素，如果缓冲区已满，则返回 false
     *
     * @param element 元素
     * @return 是否添加成功
     */
    public boolean offer(E element) {
        Assert.notNull(element, "element must not be null");
        long head = readCounter.get();
        long tail = writeCounter.getOpaque();
        while (tail - head < cap) {
            if (writeCounter.weakCompareAndSetVolatile(tail, tail + 1)) {
                int index = (int) (tail & mask);
                buffer.setRelease(index, element);
                return true;
            }
            head = readCounter.get();
            tail = writeCounter.get();
        }
        return false;
    }

    /**
     * 获取并移除缓冲区头部元素，如果缓冲区为空，则返回 null
     *
     * @return 元素
     */
    public E poll() {
        long head = readCounter.getOpaque();
        long tail = writeCounter.get();
        if (head < tail) {
            int index = (int) (head & mask);
            E element = buffer.getAcquire(index);
            if (element == null) {
                return null;
            }
            // 使用CAS操作尝试将读取位置向前移动，以确保线程安全
            if (readCounter.weakCompareAndSetVolatile(head, head + 1)) {
                buffer.weakCompareAndSetVolatile(index, element, null);
                return element;
            }
        }
        return null;
    }

    /**
     * 将队列中的所有元素传递给指定的消费者，直到队列为空
     * 此方法通过比较读取和写入计数器来确定队列是否为空，并使用CAS操作来确保线程安全
     *
     * @param consumer 消费者接口，用于处理队列中的元素
     */
    public void drainTo(Consumer<E> consumer) {
        // 获取当前读取位置
        long head = readCounter.getOpaque();
        // 获取当前写入位置
        long tail = writeCounter.get();
        // 当读取位置小于写入位置时，表示队列不为空
        while (head < tail) {
            int index = (int) (head & mask);
            E element = buffer.getAcquire(index);
            if (element == null) {
                return;
            }
            // 使用CAS操作尝试将读取位置向前移动，以确保线程安全
            if (readCounter.weakCompareAndSetVolatile(head, head + 1)) {
                buffer.weakCompareAndSetVolatile(index, element, null);
                consumer.accept(element);
            }
            // 重新获取当前读取和写入位置，以准备下一轮循环
            head = readCounter.get();
            tail = writeCounter.get();
        }
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
     * 判断缓冲区是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断缓冲区是否已满
     *
     * @return 是否已满
     */
    public boolean isFull() {
        return size() == cap;
    }

    /**
     * 获取缓冲区容量
     *
     * @return 容量
     */
    public int capacity() {
        return cap;
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
     * 计算最接近2的幂的容量
     *
     * @param size 容量
     * @return 最接近2的幂的容量
     */
    private static int tableSizeFor(int size) {
        if (size <= MINIMUM_SIZE) {
            return MINIMUM_SIZE;
        }
        if (size >= MAXIMUM_SIZE) {
            return MAXIMUM_SIZE;
        }
        int n = -1 >>> Integer.numberOfLeadingZeros(size - 1);
        return (n <= MINIMUM_SIZE) ? MINIMUM_SIZE : (n >= MAXIMUM_SIZE) ? MAXIMUM_SIZE : n + 1;
    }

}
