package com.igeeksky.xtool.core.nlp;

/**
 * 创建新节点（避免Node与具体实现耦合）
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-30
 */
public interface NodeCreator<V> {

    /**
     * 创建节点
     *
     * @param c 字符
     * @return 新创建的节点
     */
    Node<V> apply(char c);

}