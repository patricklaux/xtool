package com.igeeksky.xtool.core.lang.compress;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2023-09-07
 */
public class CompressException extends RuntimeException {

    public CompressException(String message) {
        super(message);
    }

    public CompressException(String message, Throwable cause) {
        super(message, cause);
    }
}
