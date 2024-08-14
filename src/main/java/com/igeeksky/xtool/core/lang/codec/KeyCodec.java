package com.igeeksky.xtool.core.lang.codec;

/**
 * @author Patrick.Lau
 * @since 1.0.14 2024/8/15
 */
public interface KeyCodec<K> {

    String encode(K key);

    K decode(String key);

}
