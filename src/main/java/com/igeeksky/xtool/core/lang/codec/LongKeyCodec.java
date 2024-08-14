package com.igeeksky.xtool.core.lang.codec;

/**
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public class LongKeyCodec implements KeyCodec<Long> {

    private static final LongKeyCodec INSTANCE = new LongKeyCodec();

    public static LongKeyCodec getInstance() {
        return INSTANCE;
    }

    @Override
    public String encode(Long source) {
        if (source == null) {
            throw new CodecException("source must not be null");
        }
        return String.valueOf(source);
    }

    @Override
    public Long decode(String source) {
        if (source == null) {
            throw new CodecException("source must not be null");
        }
        return Long.parseLong(source);
    }

}
