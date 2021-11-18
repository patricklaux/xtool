package com.igeeksky.xtool.core.nlp;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-15
 */
public class LinkedToAvlConvertor<V> implements NodeConvertor<LinkedNode<V>, AvlNode<V>> {

    /**
     * 单链表 转换成 AvlTree
     *
     * @param first 单链表头节点
     * @return AvlTree 根节点
     */
    @Override
    public AvlNode<V> toTreeNode(LinkedNode<V> first) {
        AvlNode<V> root = new AvlNode<>(first.c, first.value, first.size, first.table);
        for (LinkedNode<V> next = first.next; next != null; next = next.next) {
            root = AvlNode.insertAndBalance(root, new AvlNode<>(next.c, next.value, next.size, next.table));
        }
        return root;
    }

    /**
     * AvlTree 转换成 单链表
     *
     * @param root AvlTree 根节点
     * @return 单链表头节点
     */
    @Override
    public LinkedNode<V> fromTreeNode(AvlNode<V> root) {
        LinkedNode<V> head = new LinkedNode<>('0');
        fromAvlTree(root, head);
        return head.next;
    }

    private static <V> LinkedNode<V> fromAvlTree(AvlNode<V> root, LinkedNode<V> tail) {
        if (root.left != null) {
            tail = fromAvlTree(root.left, tail);
        }
        tail.next = new LinkedNode<>(root.c, root.value, root.size, root.table);
        tail = tail.next;
        if (root.right != null) {
            tail = fromAvlTree(root.right, tail);
        }
        return tail;
    }
}
