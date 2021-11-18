package com.igeeksky.xtool.core.nlp;

/**
 * 创建新链表节点（避免Node与具体实现耦合）
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-30
 */
public class LinkedNodeCreator<V> implements NodeCreator<V> {

    @Override
    public Node<V> apply(char c) {
        return new LinkedNode<>(c);
    }
}