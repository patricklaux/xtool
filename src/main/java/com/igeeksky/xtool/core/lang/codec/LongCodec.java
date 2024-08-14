package com.igeeksky.xtool.core.lang.codec;

/**
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public class LongCodec implements Codec<Long> {

    private static final LongCodec INSTANCE = new LongCodec();

    public static LongCodec getInstance() {
        return INSTANCE;
    }

    @Override
    public byte[] encode(Long value) {
        if (value == null) {
            throw new CodecException("source must not be null");
        }
        byte[] array = new byte[8];
        long v = value;
        array[0] = (byte) (v >>> 56);
        array[1] = (byte) (v >>> 48);
        array[2] = (byte) (v >>> 40);
        array[3] = (byte) (v >>> 32);
        array[4] = (byte) (v >>> 24);
        array[5] = (byte) (v >>> 16);
        array[6] = (byte) (v >>> 8);
        array[7] = (byte) (v);
        return array;
    }

    @Override
    public Long decode(byte[] source) {
        if (source == null) {
            throw new CodecException("byte[] source must not be null");
        }
        if (source.length < 8) {
            throw new CodecException("byte[] source is too short to be a long");
        }
        long value = 0L;
        value |= (source[0] & 0xffL) << 56;
        value |= (source[1] & 0xffL) << 48;
        value |= (source[2] & 0xffL) << 40;
        value |= (source[3] & 0xffL) << 32;
        value |= (source[4] & 0xffL) << 24;
        value |= (source[5] & 0xffL) << 16;
        value |= (source[6] & 0xffL) << 8;
        value |= (source[7] & 0xffL);
        return value;
    }

}