package com.igeeksky.xtool.core.nlp;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-16
 */
public abstract class TreeNode<V> extends Node<V> {

    public TreeNode(char c) {
        super(c);
    }

    public TreeNode(char c, V value) {
        super(c, value);
    }

    public TreeNode(char c, V value, int size, Node<V>[] table) {
        super(c, value, size, table);
    }
}
