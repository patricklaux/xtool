package com.igeeksky.xtool.core.concurrent;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/4
 */
public class PlatformThreadFactory implements ThreadFactory {

    private final String prefix;
    private final ThreadFactory factory;

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
        Objects.requireNonNull(prefix, "PlatformThreadFactory: prefix must not be null");
        this.prefix = prefix;

        Thread.Builder.OfPlatform ofPlatform = Thread.ofPlatform()
                .name(prefix, 0)
                .inheritInheritableThreadLocals(inherit);
        if (ueh != null) {
            ofPlatform.uncaughtExceptionHandler(ueh);
        }
        this.factory = ofPlatform.factory();
    }

    @Override
    public Thread newThread(Runnable task) {
        Objects.requireNonNull(task, prefix + " PlatformThreadFactory: task must not be null");
        return this.factory.newThread(task);
    }

}