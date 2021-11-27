/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.nlp;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-12
 */
@SuppressWarnings("unchecked")
class LinkedNode<V> extends Node<V> {

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedNode)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        LinkedNode<?> that = (LinkedNode<?>) o;

        return Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"c\":\"" + c + "\"" +
                (null != value ? (", \"value\":" + value) : "") +
                (null != next ? (", \"next\":" + next) : "") +
                '}';
    }
}