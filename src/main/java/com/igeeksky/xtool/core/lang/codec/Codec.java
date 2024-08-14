package com.igeeksky.xtool.core.lang.codec;


/**
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public interface Codec<V> {

    /**
     * <b>序列化</b><p>
     * 实现此接口时，需判断 obj 是否为空：如果 obj为空，请抛出 {@link CodecException}
     *
     * @param value 需序列化的对象
     * @return 序列化数组
     */
    byte[] encode(V value);

    /**
     * <b>反序列化</b><p>
     * 实现此接口时，需判断 source 是否为空：如果 source为空，请抛出 {@link CodecException}
     *
     * @param source 序列化数组
     * @return 反序列化生成的对象
     */
    V decode(byte[] source);

}
