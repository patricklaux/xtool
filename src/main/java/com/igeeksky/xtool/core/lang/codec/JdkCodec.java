package com.igeeksky.xtool.core.lang.codec;


import java.io.*;

/**
 * JDK内置序列化器
 *
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
@SuppressWarnings("unchecked")
public class JdkCodec<V> implements Codec<V> {

    private static final JdkCodec<?> INSTANCE = new JdkCodec<>();

    public static <V> JdkCodec<V> getInstance() {
        return (JdkCodec<V>) INSTANCE;
    }

    @Override
    public byte[] encode(V value) {
        if (null == value) {
            throw new CodecException("obj must not be null");
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(value);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            String errMsg = String.format("Unable to encode [%s]. %s", value, e.getMessage());
            throw new CodecException(errMsg, e);
        }
    }

    @Override
    public V decode(byte[] source) {
        if (null == source) {
            throw new CodecException("byte[] source must not be null");
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(source);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (V) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            String errMsg = String.format("Unable to decode [%s]. %s", new String(source), e.getMessage());
            throw new CodecException(errMsg, e);
        }
    }

}