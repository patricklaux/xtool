package com.igeeksky.xtool.core.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/5
 */
class PlatformThreadFactoryTest {

    private static final PlatformThreadFactory threadFactory = new PlatformThreadFactory("platform-thread-");

    /**
     * 创建新线程
     */
    @Test
    void newThread() {
        for (int i = 0; i < 10; i++) {
            threadFactory.newThread(() -> {
                Assertions.assertFalse(Thread.currentThread().isVirtual());
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
        PlatformThreadFactory threadFactory = new PlatformThreadFactory("platform-thread-", ((t, e) -> {
            Assertions.assertNotNull(e);
            System.out.println("has error");
            Assertions.assertEquals("platform-thread-error", e.getMessage());
        }));

        threadFactory.newThread(() -> {
            throw new RuntimeException("platform-thread-error");
        }).start();
    }
}