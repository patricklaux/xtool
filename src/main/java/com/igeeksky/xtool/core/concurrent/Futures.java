package com.igeeksky.xtool.core.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Patrick.Lau
 * @since 1.0.13 2024/8/13
 */
public class Futures {

    /**
     * 等待所有任务完成（无时间限制）
     *
     * @param futures 任务列表
     */
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

    /**
     * 等待所有任务完成（无时间限制）
     *
     * @param futures 任务列表
     */
    public static void awaitAll(ArrayList<Future<?>> futures) {
        int i = 0, len = futures.size();
        try {
            for (; i < len; i++) {
                Future<?> future = futures.get(i);
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

    /**
     * 等待所有任务完成（有时间限制）
     *
     * @param timeout 超时时间
     * @param unit    时间单位
     * @param start   起始位置，从此位置开始检查任务是否已完成，如未完成，则等待给定的时间
     * @param futures 任务列表
     * @return 剩余未完成任务的起始位置
     */
    public static int awaitAll(long timeout, TimeUnit unit, int start, Future<?>[] futures) {
        int i = start, len = futures.length;
        try {
            long nanos = unit.toNanos(timeout);
            long endTime = System.nanoTime() + nanos;

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
                    nanos = endTime - System.nanoTime();
                }
            }

            return i;
        } catch (TimeoutException e) {
            return i;
        } catch (Exception e) {
            throw new ConcurrentException(e);
        }
    }

    /**
     * 等待所有任务完成（有时间限制）
     *
     * @param timeout 超时时间
     * @param unit    时间单位
     * @param start   起始位置，从此位置开始检查任务是否已完成，如未完成，则等待给定的时间
     * @param futures 任务列表
     * @return 剩余未完成的任务的起始位置
     */
    public static int awaitAll(long timeout, TimeUnit unit, int start, ArrayList<Future<?>> futures) {
        int i = start, len = futures.size();
        try {
            long nanos = unit.toNanos(timeout);
            long endTime = System.nanoTime() + nanos;

            for (; i < len; i++) {
                if (nanos < 0) {
                    return i;
                }

                Future<?> future = futures.get(i);
                if (future != null) {
                    if (future.isDone()) {
                        continue;
                    }
                    future.get(nanos, TimeUnit.NANOSECONDS);
                    nanos = endTime - System.nanoTime();
                }
            }

            return i;
        } catch (TimeoutException e) {
            return i;
        } catch (Exception e) {
            throw new ConcurrentException(e);
        }
    }

    /**
     * 检查是否还有未完成的任务
     *
     * @param start   起始位置，从此位置开始检查任务是否已完成
     * @param futures 任务列表
     * @return 剩余未完成的任务的起始位置
     */
    public static int checkAll(int start, ArrayList<Future<?>> futures) {
        int i = start, len = futures.size();
        for (; i < len; i++) {
            Future<?> future = futures.get(i);
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                return i;
            }
        }
        return i;
    }

    /**
     * 检查是否还有未完成的任务
     *
     * @param start   起始位置，从此位置开始检查任务是否已完成
     * @param futures 任务列表
     * @return 剩余未完成的任务的起始位置
     */
    public static int checkAll(int start, Future<?>[] futures) {
        int i = start, len = futures.length;
        for (; i < len; i++) {
            Future<?> future = futures[i];
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                return i;
            }
        }
        return i;
    }

    /**
     * 取消所有未完成的任务
     *
     * @param start   起始位置，从此位置开始取消未完成的任务
     * @param futures 任务列表
     */
    public static void cancelAll(int start, Future<?>[] futures, boolean mayInterruptIfRunning) {
        int i = start, len = futures.length;
        for (; i < len; i++) {
            Future<?> future = futures[i];
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                if (future.isCancelled()) {
                    continue;
                }
                future.cancel(mayInterruptIfRunning);
            }
        }
    }

    /**
     * 取消所有未完成的任务
     *
     * @param start   起始位置，从此位置开始取消未完成的任务
     * @param futures 任务列表
     */
    public static void cancelAll(int start, ArrayList<Future<?>> futures) {
        int i = start, len = futures.size();
        for (; i < len; i++) {
            Future<?> future = futures.get(i);
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                if (future.isCancelled()) {
                    continue;
                }
                future.cancel(true);
            }
        }
    }

}