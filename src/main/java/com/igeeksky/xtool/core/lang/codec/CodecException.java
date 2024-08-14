package com.igeeksky.xtool.core.lang.codec;

/**
 * 序列化异常
 *
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public class CodecException extends RuntimeException {

    public CodecException(String msg) {
        super(msg);
    }

    public CodecException(String msg, Exception e) {
        super(msg, e);
    }

}
