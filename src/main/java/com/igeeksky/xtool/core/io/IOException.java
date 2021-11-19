package com.igeeksky.xtool.core.io;

/**
 * IO异常
 * <p>
 * {@link java.io.IOException} 转换为 {@link RuntimeException}
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-22
 */
public class IOException extends RuntimeException {

    /**
     * @param message 异常信息
     */
    public IOException(String message) {
        super(message);
    }

    /**
     * @param cause IO异常
     */
    public IOException(java.io.IOException cause) {
        super(cause);
    }

    /**
     * @param message 异常信息
     * @param cause   IO异常
     */
    public IOException(String message, java.io.IOException cause) {
        super(message, cause);
    }

}
