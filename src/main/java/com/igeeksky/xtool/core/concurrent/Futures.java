package com.igeeksky.xtool.core.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Patrick.Lau
 * @since 1.0.13 2024/8/13
 */
public class Futures {

    public static int awaitAll(long timeout, TimeUnit unit, int start, Future<?>[] futures) {
        int i = start, len = futures.length;
        try {
            long nanos = unit.toNanos(timeout);
            long time = System.nanoTime();

            for (; i < len; i++) {
                if (nanos < 0) {
                    return i;
                }

                Future<?> future = futures[i];

                if (future != null) {
                    if (future.isDone()) {
                        continue;
                    }

                    future.get(nanos, TimeUnit.NANOSECONDS);

                    long now = System.nanoTime();
                    nanos -= now - time;
                    time = now;
                }
            }

            return i;
        } catch (TimeoutException e) {
            return i;
        } catch (Exception e) {
            throw new ConcurrentException(e);
        }
    }

    public static void cancelAll(int start, Future<?>[] futures) {
        int i = start, len = futures.length;
        for (; i < len; i++) {
            Future<?> future = futures[i];
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                future.cancel(true);
            }
        }
    }

    public static void awaitAll(Future<?>[] futures) {
        int i = 0, len = futures.length;
        try {
            for (; i < len; i++) {
                Future<?> future = futures[i];
                if (future != null) {
                    if (future.isDone()) {
                        continue;
                    }
                    future.get();
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new ConcurrentException(e);
        }
    }

}