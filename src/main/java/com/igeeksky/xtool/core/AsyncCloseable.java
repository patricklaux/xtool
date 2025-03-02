package com.igeeksky.xtool.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步关闭接口
 *
 * @author Patrick.Lau
 * @since 1.1.3
 */
public interface AsyncCloseable extends AutoCloseable {

    @Override
    default void close() {
        try {
            closeAsync().get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 异步关闭
     *
     * @return {@code CompletableFuture<Void>} – 关闭结果（可能的异常信息）
     */
    CompletableFuture<Void> closeAsync();

}
