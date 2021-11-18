package com.igeeksky.xtool.core.nlp;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-15
 */
public interface NodeConvertor<T extends Node<?>, R extends TreeNode<?>> {

    /**
     * 节点转换（链表转树）
     *
     * @param node 待转换的节点
     * @return 转换后的节点
     */
    R toTreeNode(T node);

    /**
     * 节点转换（树转链表）
     *
     * @param node 待转换的节点
     * @return 转换后的节点
     */
    T fromTreeNode(R node);

}
