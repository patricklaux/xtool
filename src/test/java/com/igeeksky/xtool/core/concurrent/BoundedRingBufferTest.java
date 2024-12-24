package com.igeeksky.xtool.core.concurrent;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoundedRingBufferTest {

    private BoundedRingBuffer<String> buffer;

    @BeforeEach
    public void setUp() {
        buffer = new BoundedRingBuffer<>(10);
    }

    @Test
    public void offer_BufferNotFull_ElementAdded() {
        assertTrue(buffer.offer("element1"));
        assertEquals(1, buffer.size());
    }

    @Test
    public void offer_BufferFull_ElementNotAdded() {
        for (int i = 0; i < 16; i++) {
            buffer.offer("element" + i);
        }
        assertFalse(buffer.offer("element17"));
        assertEquals(16, buffer.size());
    }

    @Test
    public void poll_BufferNotEmpty_ElementRemoved() {
        buffer.offer("element1");
        assertEquals("element1", buffer.poll());
        assertEquals(0, buffer.size());
    }

    @Test
    public void poll_BufferEmpty_ReturnsNull() {
        assertNull(buffer.poll());
    }

    @Test
    public void size_BufferEmpty_SizeZero() {
        assertEquals(0, buffer.size());
    }

    @Test
    public void size_BufferPartiallyFilled_SizeNonZero() {
        buffer.offer("element1");
        assertEquals(1, buffer.size());
    }

    @Test
    public void size_BufferFull_SizeEqualsCapacity() {
        for (int i = 0; i < 10; i++) {
            buffer.offer("element" + i);
        }
        assertEquals(10, buffer.size());
    }

    @Test
    public void reads_CounterReflectsReadOperations() {
        buffer.offer("element1");
        buffer.poll();
        assertEquals(1, buffer.reads());
    }

    @Test
    public void writes_CounterReflectsWriteOperations() {
        buffer.offer("element1");
        assertEquals(1, buffer.writes());
    }

}
