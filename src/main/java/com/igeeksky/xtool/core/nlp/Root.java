package com.igeeksky.xtool.core.nlp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-12
 */
public class Root<V> extends BaseNode<V> {

    @SuppressWarnings("unchecked")
    protected final Node<V>[] table = new Node[TrieConstants.TABLE_MAX_CAPACITY];

    public Root(char c) {
        super(c);
    }

    @Override
    public Node<V> addChild(char c, NodeCreator<V> creator, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        int index = c & TrieConstants.TABLE_MAX_MASK;
        Node<V> head = table[index];
        if (head == null) {
            return (table[index] = creator.apply(c));
        }
        return head;
    }

    @Override
    public Node<V> findChild(char c) {
        return table[c & TrieConstants.TABLE_MAX_MASK];
    }

    @Override
    public void deleteChild(Node<V> child, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        table[child.c & TrieConstants.TABLE_MAX_MASK] = null;
    }

    public void reset() {
        Arrays.fill(table, null);
    }

    @Override
    public Iterator<Node<V>> iterator() {
        return new RootIterator<>(this.table);
    }

    private static class RootIterator<V> implements Iterator<Node<V>> {

        private final Node<V>[] table;
        private Node<V> next;
        private int index;

        public RootIterator(Node<V>[] table) {
            this.table = table;
        }

        private Node<V> findNext() {
            while (index < table.length) {
                Node<V> f = table[index++];
                if (f != null) {
                    return f;
                }
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            if (next == null) {
                next = findNext();
            }
            return next != null;
        }

        @Override
        public Node<V> next() {
            Node<V> node = next;
            next = null;
            return node;
        }
    }
}
