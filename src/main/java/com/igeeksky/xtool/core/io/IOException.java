package com.igeeksky.xtool.core.io;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-22
 */
public class IOException extends RuntimeException {

    public IOException(String message) {
        super(message);
    }

    public IOException(Throwable cause) {
        super(cause);
    }

    public IOException(String message, Throwable cause) {
        super(message, cause);
    }

}
