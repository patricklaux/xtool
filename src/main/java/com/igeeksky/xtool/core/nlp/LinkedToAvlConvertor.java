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
