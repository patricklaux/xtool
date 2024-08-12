package com.igeeksky.xtool.core.concurrent;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/4
 */
public class VirtualThreadFactory implements ThreadFactory {

    private final String prefix;
    private final ThreadFactory factory;

    public VirtualThreadFactory(String prefix) {
        this(prefix, false, Thread.getDefaultUncaughtExceptionHandler());
    }

    public VirtualThreadFactory(String prefix, Thread.UncaughtExceptionHandler ueh) {
        this(prefix, false, ueh);
    }

    public VirtualThreadFactory(String prefix, boolean inherit) {
        this(prefix, inherit, Thread.getDefaultUncaughtExceptionHandler());
    }

    public VirtualThreadFactory(String prefix, boolean inherit, Thread.UncaughtExceptionHandler ueh) {
        Objects.requireNonNull(prefix, "VirtualThreadFactory: prefix must not be null");
        Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual()
                .name(prefix, 0)
                .inheritInheritableThreadLocals(inherit);
        if (ueh != null) {
            ofVirtual.uncaughtExceptionHandler(ueh);
        }
        this.prefix = prefix;
        this.factory = ofVirtual.factory();
    }

    @Override
    public Thread newThread(Runnable task) {
        Objects.requireNonNull(task, prefix + " VirtualThreadFactory: task must not be null");
        return factory.newThread(task);
    }

}