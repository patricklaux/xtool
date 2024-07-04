package com.igeeksky.xtool.core.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/4
 */
class VirtualThreadFactoryTest {

    private static final VirtualThreadFactory threadFactory = new VirtualThreadFactory("virtual-thread-");

    @Test
    void newThread() {
        for (int i = 0; i < 10; i++) {
            threadFactory.newThread(() -> {
                Assertions.assertTrue(Thread.currentThread().isVirtual());
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
    }

    @Test
    void newThread2() {
        Throwable ex = null;
        try {
            threadFactory.newThread(null);
        } catch (NullPointerException e) {
            ex = e;
        }
        Assertions.assertNotNull(ex);
    }

    @Test
    void uncaughtException() {
        VirtualThreadFactory threadFactory = new VirtualThreadFactory("virtual-thread-", ((t, e) -> {
            Assertions.assertNotNull(e);
            System.out.println("has error");
            Assertions.assertEquals("virtual-thread-error", e.getMessage());
        }));

        threadFactory.newThread(() -> {

            throw new RuntimeException("virtual-thread-error");
        }).start();
    }

}