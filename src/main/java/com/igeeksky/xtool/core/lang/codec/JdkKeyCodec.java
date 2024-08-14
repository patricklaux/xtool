package com.igeeksky.xtool.core.lang.codec;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
@SuppressWarnings("unchecked")
public class JdkKeyCodec<K> implements KeyCodec<K> {

    private static final JdkKeyCodec<?> UTF_8 = new JdkKeyCodec<>(StandardCharsets.UTF_8);

    private static final JdkKeyCodec<?> UTF_16 = new JdkKeyCodec<>(StandardCharsets.UTF_16);

    private static final JdkKeyCodec<?> UTF_16BE = new JdkKeyCodec<>(StandardCharsets.UTF_16BE);

    private static final JdkKeyCodec<?> UTF_16LE = new JdkKeyCodec<>(StandardCharsets.UTF_16LE);

    private static final JdkKeyCodec<?> US_ASCII = new JdkKeyCodec<>(StandardCharsets.US_ASCII);

    private static final JdkKeyCodec<?> ISO_8859_1 = new JdkKeyCodec<>(StandardCharsets.ISO_8859_1);

    private final Charset charset;

    private JdkKeyCodec(Charset charset) {
        this.charset = charset;
    }

    public static <K> JdkKeyCodec<K> getInstance(Charset charset) {
        if (Objects.equals(StandardCharsets.UTF_8, charset)) {
            return (JdkKeyCodec<K>) UTF_8;
        }
        if (Objects.equals(StandardCharsets.UTF_16, charset)) {
            return (JdkKeyCodec<K>) UTF_16;
        }
        if (Objects.equals(StandardCharsets.UTF_16BE, charset)) {
            return (JdkKeyCodec<K>) UTF_16BE;
        }
        if (Objects.equals(StandardCharsets.UTF_16LE, charset)) {
            return (JdkKeyCodec<K>) UTF_16LE;
        }
        if (Objects.equals(StandardCharsets.US_ASCII, charset)) {
            return (JdkKeyCodec<K>) US_ASCII;
        }
        if (Objects.equals(StandardCharsets.ISO_8859_1, charset)) {
            return (JdkKeyCodec<K>) ISO_8859_1;
        }
        return new JdkKeyCodec<>(charset);
    }

    @Override
    public String encode(K source) {
        if (null == source) {
            throw new CodecException("source must not be null");
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(source);
            oos.flush();
            return bos.toString(charset);
        } catch (IOException e) {
            String errMsg = String.format("can't encode [%s]. %s", source, e.getMessage());
            throw new CodecException(errMsg, e);
        }
    }

    @Override
    public K decode(String source) {
        if (null == source) {
            throw new CodecException("source must not be null");
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(source.getBytes(charset));
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (K) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            String errMsg = String.format("can't decode [%s]. %s", source, e.getMessage());
            throw new CodecException(errMsg, e);
        }
    }

}
