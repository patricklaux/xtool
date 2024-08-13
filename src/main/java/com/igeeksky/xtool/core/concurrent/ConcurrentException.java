package com.igeeksky.xtool.core.concurrent;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/8/13
 */
public class ConcurrentException extends RuntimeException{

    public ConcurrentException() {
        super();
    }

    public ConcurrentException(String message) {
        super(message);
    }

    public ConcurrentException(Throwable cause) {
        super(cause);
    }

    public ConcurrentException(String message, Throwable cause) {
        super(message, cause);
    }

}
