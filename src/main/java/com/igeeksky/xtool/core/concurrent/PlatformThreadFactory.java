package com.igeeksky.xtool.core.concurrent;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/4
 */
public class PlatformThreadFactory implements ThreadFactory {

    private final String prefix;
    private final boolean inherit;
    private final Thread.UncaughtExceptionHandler ueh;
    private final AtomicLong counter = new AtomicLong(0L);

    public PlatformThreadFactory(String prefix) {
        this(prefix, false, Thread.getDefaultUncaughtExceptionHandler());
    }

    public PlatformThreadFactory(String prefix, Thread.UncaughtExceptionHandler ueh) {
        this(prefix, false, ueh);
    }

    public PlatformThreadFactory(String prefix, boolean inherit) {
        this(prefix, inherit, Thread.getDefaultUncaughtExceptionHandler());
    }

    public PlatformThreadFactory(String prefix, boolean inherit, Thread.UncaughtExceptionHandler ueh) {
        Objects.requireNonNull(prefix, "VirtualThreadFactory: prefix must not be null");
        this.prefix = prefix;
        this.inherit = inherit;
        this.ueh = ueh;
    }

    @Override
    public Thread newThread(Runnable task) {
        Objects.requireNonNull(task, prefix + " PlatformThreadFactory: task must not be null");
        Thread.Builder.OfPlatform ofPlatform = Thread.ofPlatform()
                .name(prefix + counter.getAndIncrement())
                .inheritInheritableThreadLocals(inherit);
        if (ueh != null) {
            return ofPlatform.uncaughtExceptionHandler(ueh).unstarted(task);
        }
        return ofPlatform.unstarted(task);
    }

}