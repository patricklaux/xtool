package com.igeeksky.xtool.core;

import java.util.concurrent.CompletableFuture;

/**
 * 异步关闭接口
 * <p>
 * IO 操作类对象建议实现此接口。
 *
 * @author Patrick.Lau
 * @since 1.1.3
 */
public interface AsyncCloseable {

    /**
     * 异步关闭
     *
     * @return 异步关闭
     */
    CompletableFuture<Void> closeAsync();

}
