package com.igeeksky.xtool.core.nlp;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-12
 */
@SuppressWarnings("unchecked")
public class LinkedNode<V> extends Node<V> {

    protected LinkedNode<V> next;

    public LinkedNode(char c) {
        super(c);
    }

    public LinkedNode(char c, V value, int size, Node<V>[] table) {
        super(c, value, size, table);
    }

    public void setNext(LinkedNode<V> next) {
        this.next = next;
    }

    @Override
    public Node<V> find(char c) {
        LinkedNode<V> node = this;
        while (node != null) {
            if (node.c == c) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    protected List<Node<V>> findAll() {
        List<Node<V>> nodes = new LinkedList<>();
        LinkedNode<V> node = this;
        while (node != null) {
            nodes.add(node);
            node = node.next;
        }
        return nodes;
    }

    @Override
    public Node<V> insert(Node<V> parent, int index, char c, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        //  遍历首节点及其后继节点：如果char值相同，返回该节点；如果char值不同，将新节点添加到链表末尾(超过阈值则转换成AVL树再插入新节点)
        int count = 1;
        LinkedNode<V> next = this;
        while (true) {
            if (c == next.c) {
                return next;
            }
            count++;
            if (next.next == null) {
                if (count >= TrieConstants.TO_TREE_NODE_THRESHOLD) {
                    // 转换成Avl树，并插入新节点
                    Node<V> head = ((NodeConvertor<LinkedNode<V>, ? extends TreeNode<V>>) convertor).toTreeNode(this);
                    return head.insert(parent, index, c, convertor);
                }
                parent.increment();
                LinkedNode<V> node = new LinkedNode<>(c);
                next.setNext(node);
                return node;
            }
            next = next.next;
        }
    }

    @Override
    public Node<V> delete(BaseNode<V> delete, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        LinkedNode<V> del = (LinkedNode<V>) delete;
        if (this.c == del.c) {
            LinkedNode<V> head = this.next;
            this.next = null;
            return head;
        }
        LinkedNode<V> node = this;
        while (node.next != null) {
            if (node.next.c == del.c) {
                del = node.next;
                node.next = del.next;
                del.next = null;
                break;
            }
            node = node.next;
        }
        return this;
    }

    @Override
    public void split(Node<V>[] newTab, int oldCap, int oldIndex, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (this.next == null) {
            newTab[this.c & newTab.length - 1] = this;
            return;
        }
        LinkedNode<V> head = this, loHead = null, loTail = null, hiHead = null, hiTail = null;
        for (LinkedNode<V> node = head, next; node != null; node = next) {
            next = node.next;
            if ((node.c & oldCap) == 0) {
                if (loTail == null) {
                    loHead = node;
                } else {
                    loTail.next = node;
                }
                loTail = node;
            } else {
                if (hiTail == null) {
                    hiHead = node;
                } else {
                    hiTail.next = node;
                }
                hiTail = node;
            }
        }
        if (loHead != null) {
            newTab[oldIndex] = loHead;
            loTail.next = null;
        }

        if (hiHead != null) {
            newTab[oldIndex + oldCap] = hiHead;
            hiTail.next = null;
        }
    }

    @Override
    public Node<V> join(Node<V> old, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (!(old instanceof LinkedNode)) {
            return old.join(this, convertor);
        }
        int count = 1;
        LinkedNode<V> head = this, tail = this;
        while (tail.next != null) {
            ++count;
            tail = tail.next;
        }
        LinkedNode<V> node = (LinkedNode<V>) old;
        while (node != null) {
            ++count;
            tail.next = node;
            tail = node;
            node = node.next;
        }
        if (count >= TrieConstants.TO_TREE_NODE_THRESHOLD) {
            return ((NodeConvertor<LinkedNode<V>, ? extends TreeNode<V>>) convertor).toTreeNode(head);
        }
        return head;
    }

    @Override
    public String toString() {
        return "{\"c\":\"" + c + "\"" +
                (null != value ? (", \"value\":" + value) : "") +
                (null != next ? (", \"next\":" + next) : "") +
                '}';
    }
}