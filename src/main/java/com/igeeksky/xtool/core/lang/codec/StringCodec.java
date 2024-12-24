package com.igeeksky.xtool.core.lang.codec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * String 序列化器
 *
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public class StringCodec implements Codec<String> {

    private static final StringCodec UTF_8 = new StringCodec(StandardCharsets.UTF_8);

    private static final StringCodec UTF_16 = new StringCodec(StandardCharsets.UTF_16);

    private static final StringCodec UTF_16BE = new StringCodec(StandardCharsets.UTF_16BE);

    private static final StringCodec UTF_16LE = new StringCodec(StandardCharsets.UTF_16LE);

    private static final StringCodec US_ASCII = new StringCodec(StandardCharsets.US_ASCII);

    private static final StringCodec ISO_8859_1 = new StringCodec(StandardCharsets.ISO_8859_1);

    private final Charset charset;

    private StringCodec(Charset charset) {
        this.charset = charset;
    }

    public static StringCodec getInstance(Charset charset) {
        if (charset == null) {
            throw new CodecException("charset must not be null");
        }
        if (Objects.equals(StandardCharsets.UTF_8, charset)) {
            return UTF_8;
        }
        if (Objects.equals(StandardCharsets.UTF_16, charset)) {
            return UTF_16;
        }
        if (Objects.equals(StandardCharsets.UTF_16BE, charset)) {
            return UTF_16BE;
        }
        if (Objects.equals(StandardCharsets.UTF_16LE, charset)) {
            return UTF_16LE;
        }
        if (Objects.equals(StandardCharsets.US_ASCII, charset)) {
            return US_ASCII;
        }
        if (Objects.equals(StandardCharsets.ISO_8859_1, charset)) {
            return ISO_8859_1;
        }
        return new StringCodec(charset);
    }

    @Override
    public byte[] encode(String value) {
        if (null == value) {
            throw new CodecException("source must not be null");
        }
        return value.getBytes(charset);
    }

    @Override
    public String decode(byte[] source) {
        if (null == source) {
            throw new CodecException("byte[] source must not be null");
        }
        return new String(source, charset);
    }

    public String decode(byte[] source, int offset, int length) {
        if (null == source) {
            throw new CodecException("byte[] source must not be null");
        }
        return new String(source, offset, length, charset);
    }

}