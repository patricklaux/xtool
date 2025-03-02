package com.igeeksky.xtool.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 优雅停机接口
 * <p>
 * 池化类对象及工厂类对象建议实现此接口。
 *
 * @author Patrick.Lau
 * @since 1.1.2
 */
public interface GracefulShutdown {

    /**
     * 根据预设参数执行优雅停机
     */
    void shutdown();

    /**
     * 根据传入参数执行优雅停机
     *
     * @param quietPeriod 静默时长
     * @param timeout     最大超时
     * @param unit        时间单位
     */
    void shutdown(long quietPeriod, long timeout, TimeUnit unit);

    /**
     * 根据预设参数执行优雅停机（异步）
     *
     * @return {@code CompletableFuture<Void>} – 关闭结果（可能的异常信息）
     */
    CompletableFuture<Void> shutdownAsync();

    /**
     * 根据传入参数执行优雅停机（异步）
     *
     * @param quietPeriod 静默时长
     * @param timeout     最大超时
     * @param unit        时间单位
     * @return {@code CompletableFuture<Void>} – 关闭结果（可能的异常信息）
     */
    CompletableFuture<Void> shutdownAsync(long quietPeriod, long timeout, TimeUnit unit);

}