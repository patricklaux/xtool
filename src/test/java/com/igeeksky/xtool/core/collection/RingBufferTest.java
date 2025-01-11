package com.igeeksky.xtool.core.collection;


import com.igeeksky.xtool.core.lang.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RingBuffer 测试用例
 *
 * @author Patrick.Lau
 * @since 1.1.1
 */
public class RingBufferTest {

    @Test
    void size_maxCapacity_ReturnsMaxCapacity() {
        RingBuffer<String> buffer = new RingBuffer<>((1 << 30) + 1);
        assertEquals(1 << 30, buffer.capacity());
        assertEquals(0, buffer.size());
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void size_minCapacity_ReturnsMaxCapacity() {
        RingBuffer<String> buffer = new RingBuffer<>(1);
        assertEquals(16, buffer.capacity());
        assertEquals(0, buffer.size());
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());

        for (int i = 0; i < 16; i++) {
            buffer.offer("element" + i);
        }

        assertTrue(buffer.isFull());
    }

    @Test
    void offer_BufferNotFull_ElementAdded() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        assertTrue(buffer.offer("element1"));
        assertEquals(1, buffer.size());
    }

    @Test
    void offer_BufferFull_ElementNotAdded() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        for (int i = 0; i < 16; i++) {
            buffer.offer("element" + i);
        }
        assertFalse(buffer.offer("element17"));
        assertEquals(16, buffer.size());
    }

    @Test
    void poll_BufferNotEmpty_ElementRemoved() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        buffer.offer("element1");
        assertEquals("element1", buffer.poll());
        assertEquals(0, buffer.size());
    }

    @Test
    void poll_BufferEmpty_ReturnsNull() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        assertNull(buffer.poll());
    }

    @Test
    void size_BufferEmpty_SizeZero() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        assertEquals(0, buffer.size());
    }

    @Test
    void size_BufferPartiallyFilled_SizeNonZero() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        buffer.offer("element1");
        assertEquals(1, buffer.size());
    }

    @Test
    void size_BufferFull_SizeEqualsCapacity() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        for (int i = 0; i < 10; i++) {
            buffer.offer("element" + i);
        }
        assertEquals(10, buffer.size());
    }

    @Test
    void reads_CounterReflectsReadOperations() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        buffer.offer("element1");
        buffer.poll();
        assertEquals(1, buffer.reads());
    }

    @Test
    void writes_CounterReflectsWriteOperations() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        buffer.offer("element1");
        assertEquals(1, buffer.writes());
    }

    @Test
    void drainTo_EmptyBuffer_ConsumerNotCalled() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        StringConsumer consumer = new StringConsumer();

        buffer.drainTo(consumer);
        assertEquals(0, consumer.getCount());
    }

    @Test
    void drainTo_NonEmptyBuffer_AllElementsConsumed() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        StringConsumer consumer = new StringConsumer();

        buffer.offer("element1");
        buffer.offer("element2");
        buffer.offer("element3");

        buffer.drainTo(consumer);

        assertEquals(3, consumer.getCount());
    }

    @Test
    void test_TwoOffer_Concurrently() throws InterruptedException {
        RingBuffer<String> buffer = new RingBuffer<>(2048);
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });

        thread1.start();
        thread2.start();

        latch.await();

        assertEquals(2000, buffer.size());
        assertEquals(2000, buffer.writes());
        assertEquals(0, buffer.reads());
    }

    @Test
    void test_TwoPoll_Concurrently() throws InterruptedException {
        RingBuffer<String> buffer = new RingBuffer<>(2048);
        StringConsumer consumer = new StringConsumer();
        CountDownLatch latch = new CountDownLatch(2);

        for (int i = 0; i < 2000; ) {
            if (buffer.offer("element" + i)) {
                i++;
            }
        }

        Thread thread1 = new Thread(() -> {
            String polled;
            while ((polled = buffer.poll()) != null) {
                consumer.accept(polled);
            }
            latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            String polled;
            while ((polled = buffer.poll()) != null) {
                consumer.accept(polled);
            }
            latch.countDown();
        });

        thread1.start();
        thread2.start();

        latch.await();

        Set<String> values = consumer.getValues();
        for (int i = 0; i < 2000; i++) {
            String element = "element" + i;
            if (!values.contains(element)) {
                System.out.println(element + " is not exist!");
            }
        }

        assertEquals(2000, consumer.getCount());
        assertEquals(2000, buffer.writes());
        assertEquals(2000, buffer.reads());
    }

    @Test
    void test_TwoOffer_OneConsumer_Concurrently() {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        StringConsumer consumer = new StringConsumer(2000000);
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1000000; i < 2000000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });

        long startTime = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        while (latch.getCount() != 0) {
            buffer.drainTo(consumer);
        }

        buffer.drainTo(consumer);

        long endTime = System.currentTimeMillis();

        System.out.println("Time cost: " + (endTime - startTime) + "ms");

        Set<String> values = consumer.getValues();
        for (int i = 0; i < 2000000; i++) {
            String element = "element" + i;
            if (!values.contains(element)) {
                System.out.println(element + " is not exist!");
            }
        }

        assertEquals(2000000, consumer.getCount());
        assertEquals(2000000, buffer.writes());
        assertEquals(2000000, buffer.reads());
    }

    @Test
    void test_TwoOffer_OneConsumer_Concurrently_ArrayBlockingQueue() {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(16);
        ConcurrentHashSet<String> consumer = Sets.newConcurrentHashSet(2000000);
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1000000; i < 2000000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });

        long startTime = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        while (latch.getCount() != 0) {
            buffer.drainTo(consumer);
        }

        buffer.drainTo(consumer);

        long endTime = System.currentTimeMillis();

        System.out.println("Time cost: " + (endTime - startTime) + "ms");

        assertEquals(2000000, consumer.size());
    }

    @Test
    void test_TwoOffer_TwoConsumer_Concurrently() throws InterruptedException {
        RingBuffer<String> buffer = new RingBuffer<>(16);
        StringConsumer consumer1 = new StringConsumer();
        StringConsumer consumer2 = new StringConsumer();
        CountDownLatch offerLatch = new CountDownLatch(2);
        CountDownLatch consumerLatch = new CountDownLatch(2);

        Thread offerThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            offerLatch.countDown();
        });

        Thread offerThread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            offerLatch.countDown();
        });

        Thread consumerThread1 = new Thread(() -> {
            while (offerLatch.getCount() != 0) {
                buffer.drainTo(consumer1);
                // LockSupport.parkNanos(10);
            }

            buffer.drainTo(consumer1);
            consumerLatch.countDown();
        });

        Thread consumerThread2 = new Thread(() -> {
            while (offerLatch.getCount() != 0) {
                buffer.drainTo(consumer2);
            }
            buffer.drainTo(consumer2);
            consumerLatch.countDown();
        });

        offerThread1.start();
        offerThread2.start();
        consumerThread1.start();
        consumerThread2.start();

        consumerLatch.await();

        Set<String> values1 = consumer1.getValues();
        Set<String> values2 = consumer2.getValues();
        for (int i = 0; i < 2000; i++) {
            String element = "element" + i;
            if (!values1.contains(element) && !values2.contains(element)) {
                System.out.println(element + " is not exist!");
            }
        }

        assertEquals(2000, consumer1.getCount() + consumer2.getCount());
        assertEquals(2000, buffer.writes());
        assertEquals(2000, buffer.reads());
    }

    @Test
    void test_TwoOffer_OnePoll_Concurrently() {
        RingBuffer<String> buffer = new RingBuffer<>(2048);
        StringConsumer consumer = new StringConsumer();
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; ) {
                if (buffer.offer("element" + i)) {
                    i++;
                }
            }
            latch.countDown();
        });

        thread1.start();
        thread2.start();

        for (int i = 0; i < 2001; i++) {
            String polled = buffer.poll();
            if (polled != null) {
                consumer.accept(polled);
                continue;
            }
            assertEquals(consumer.getCount(), buffer.reads());
            LockSupport.parkNanos(100);
        }

        Set<String> values = consumer.getValues();
        for (int i = 0; i < 2000; i++) {
            String element = "element" + i;
            if (!values.contains(element)) {
                System.out.println(element + " is not exist!");
            }
        }

        assertEquals(2000, consumer.getCount());
    }

    private static class StringConsumer implements Consumer<String> {

        private final AtomicInteger count = new AtomicInteger();
        private final Set<String> values;

        public StringConsumer() {
            this(2048);
        }

        public StringConsumer(int capacity) {
            values = Sets.newConcurrentHashSet(capacity);
        }

        @Override
        public void accept(String s) {
            Assert.notNull(s, "element must not be null");
            count.incrementAndGet();
            values.add(s);
        }

        int getCount() {
            return count.get();
        }

        Set<String> getValues() {
            return values;
        }

    }

}
