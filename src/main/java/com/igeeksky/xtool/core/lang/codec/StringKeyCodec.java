package com.igeeksky.xtool.core.lang.codec;

/**
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public class StringKeyCodec implements KeyCodec<String> {

    private static final StringKeyCodec INSTANCE = new StringKeyCodec();

    public static StringKeyCodec getInstance() {
        return INSTANCE;
    }

    @Override
    public String encode(String source) {
        if (source == null) {
            throw new CodecException("source must not be null");
        }
        return source;
    }

    @Override
    public String decode(String source) {
        if (source == null) {
            throw new CodecException("source must not be null");
        }
        return source;
    }

}