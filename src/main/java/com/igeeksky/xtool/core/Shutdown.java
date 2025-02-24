package com.igeeksky.xtool.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 优雅关停接口
 * <p>
 * 线程池对象及工厂类对象建议实现此接口。
 *
 * @author Patrick.Lau
 * @since 1.1.2
 */
public interface Shutdown {

    /**
     * 关闭（根据配置参数或默认参数执行优雅关闭）
     */
    void shutdown();

    /**
     * 关闭（根据传入参数执行优雅关闭）
     *
     * @param quietPeriod 静默时间
     * @param timeout     超时时间
     * @param unit        时间单位
     */
    void shutdown(long quietPeriod, long timeout, TimeUnit unit);

    /**
     * 异步关闭（根据配置参数或默认参数执行优雅关闭）
     *
     * @return {@link CompletableFuture}
     */
    CompletableFuture<Void> shutdownAsync();

    /**
     * 异步关闭（根据传入参数执行优雅关闭）
     *
     * @param quietPeriod 静默时间
     * @param unit        时间单位
     * @return {@link CompletableFuture}
     */
    CompletableFuture<Void> shutdownAsync(long quietPeriod, long timeout, TimeUnit unit);

}