package com.igeeksky.xtool.core.nlp;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-17
 */
public interface Entry<V> {

    /**
     * 获取字符
     *
     * @return 字符 c
     */
    char getC();

    /**
     * 获取 value
     *
     * @return value
     */
    V getValue();

}
