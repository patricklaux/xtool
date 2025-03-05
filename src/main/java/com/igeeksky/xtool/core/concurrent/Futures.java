package com.igeeksky.xtool.core.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Future 工具类
 *
 * @author Patrick.Lau
 * @since 1.0.13 2024/8/13
 */
public final class Futures {

    /**
     * 私有构造器，禁止实例化
     */
    private Futures() {
    }

    /**
     * 等待所有任务完成（无时间限制）
     * <p>
     * 默认从 0 开始检查并等待任务完成
     *
     * @param futures 任务列表
     */
    public static void awaitAll(Future<?>[] futures) {
        awaitAll(futures, 0);
    }

    /**
     * 等待所有任务完成（无时间限制）
     *
     * @param futures 任务列表
     * @param start   起始位置，从此位置开始检查并等待任务完成
     */
    public static void awaitAll(Future<?>[] futures, int start) {
        int i = start, len = futures.length;
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
     * <p>
     * 默认从 0 开始检查并等待任务完成
     *
     * @param futures 任务列表
     */
    public static void awaitAll(ArrayList<Future<?>> futures) {
        awaitAll(futures, 0);
    }

    /**
     * 等待所有任务完成（无时间限制）
     *
     * @param futures 任务列表
     * @param start   起始位置，从此位置开始检查并等待任务完成
     */
    public static void awaitAll(ArrayList<Future<?>> futures, int start) {
        int i = start, len = futures.size();
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
     * <p>
     * 默认从 0 开始检查并等待任务完成
     *
     * @param futures 任务列表
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 剩余未完成的任务的起始位置
     */
    public static int awaitAll(Future<?>[] futures, long timeout, TimeUnit unit) {
        return awaitAll(futures, timeout, unit, 0);
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
    public static int awaitAll(Future<?>[] futures, long timeout, TimeUnit unit, int start) {
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
     * <p>
     * 默认从 0 开始检查并等待任务完成
     *
     * @param futures 任务列表
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 剩余未完成的任务的起始位置
     */
    public static int awaitAll(ArrayList<Future<?>> futures, long timeout, TimeUnit unit) {
        return awaitAll(futures, timeout, unit, 0);
    }

    /**
     * 等待所有任务完成（有时间限制）
     *
     * @param futures 任务列表
     * @param timeout 超时时间
     * @param unit    时间单位
     * @param start   起始位置，从此位置开始检查任务是否已完成，如未完成，则等待给定的时间
     * @return 剩余未完成的任务的起始位置
     */
    public static int awaitAll(ArrayList<Future<?>> futures, long timeout, TimeUnit unit, int start) {
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
     * <p>
     * 默认从 0 开始检查是否还有未完成的任务
     *
     * @param futures 任务列表
     * @return 剩余未完成任务的起始位置
     */
    public static int checkAll(ArrayList<Future<?>> futures) {
        return checkAll(futures, 0);
    }

    /**
     * 检查是否还有未完成的任务
     *
     * @param futures 任务列表
     * @param start   起始位置，从此位置开始检查任务是否已完成
     * @return 剩余未完成任务的起始位置
     */
    public static int checkAll(ArrayList<Future<?>> futures, int start) {
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
     * <p>
     * 默认从 0 开始检查是否还有未完成的任务
     *
     * @param futures 任务列表
     * @return 剩余未完成任务的起始位置
     */
    public static int checkAll(Future<?>[] futures) {
        return checkAll(futures, 0);
    }

    /**
     * 检查是否还有未完成的任务
     *
     * @param start   起始位置，从此位置开始检查任务是否已完成
     * @param futures 任务列表
     * @return 剩余未完成的任务的起始位置
     */
    public static int checkAll(Future<?>[] futures, int start) {
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
     * <p>
     * 默认从 0 开始取消未完成的任务
     *
     * @param futures               任务列表
     * @param mayInterruptIfRunning 是否中断正在运行的任务
     */
    public static void cancelAll(Future<?>[] futures, boolean mayInterruptIfRunning) {
        cancelAll(futures, mayInterruptIfRunning, 0);
    }

    /**
     * 取消所有未完成的任务
     *
     * @param futures               任务列表
     * @param mayInterruptIfRunning 是否中断正在运行的任务
     * @param start                 起始位置，从此位置开始取消未完成的任务
     */
    public static void cancelAll(Future<?>[] futures, boolean mayInterruptIfRunning, int start) {
        int i = start, len = futures.length;
        for (; i < len; i++) {
            Future<?> future = futures[i];
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                future.cancel(mayInterruptIfRunning);
            }
        }
    }

    /**
     * 取消所有未完成的任务
     * <p>
     * 默认从 0 开始取消未完成的任务
     *
     * @param futures               任务列表
     * @param mayInterruptIfRunning 是否中断正在运行的任务
     */
    public static void cancelAll(ArrayList<Future<?>> futures, boolean mayInterruptIfRunning) {
        cancelAll(futures, mayInterruptIfRunning, 0);
    }

    /**
     * 取消所有未完成的任务
     *
     * @param futures               任务列表
     * @param mayInterruptIfRunning 是否中断正在运行的任务
     * @param start                 起始位置，从此位置开始取消未完成的任务
     */
    public static void cancelAll(ArrayList<Future<?>> futures, boolean mayInterruptIfRunning, int start) {
        int i = start, len = futures.size();
        for (; i < len; i++) {
            Future<?> future = futures.get(i);
            if (future != null) {
                if (future.isDone()) {
                    continue;
                }
                future.cancel(mayInterruptIfRunning);
            }
        }
    }

}