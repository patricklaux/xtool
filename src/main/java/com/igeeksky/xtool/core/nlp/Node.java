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


import java.util.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-12
 */
@SuppressWarnings("unchecked")
abstract class Node<V> extends BaseNode<V> {

    protected int size;
    protected Node<V>[] table;

    public Node(char c) {
        super(c);
    }

    public Node(char c, V value) {
        super(c, value);
    }

    public Node(char c, V value, int size, Node<V>[] table) {
        super(c, value);
        this.size = size;
        this.table = table;
    }

    public int size() {
        return size;
    }

    public void increment() {
        ++size;
    }

    public void decrement() {
        --size;
    }

    public void setHead(Node<V> head, int index) {
        this.table[index] = head;
    }

    @Override
    public Node<V> addChild(char c, NodeCreator<V> creator, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (this.table == null) {
            table = new Node[TrieConstants.TABLE_INITIAL_CAPACITY];
        }

        expand(convertor);

        int index = c & (table.length - 1);
        Node<V> head = table[index];
        // 如果当前数组中对应位置头节点为空
        if (head == null) {
            head = creator.apply(c);
            table[index] = head;
            this.increment();
            return head;
        }

        return head.insert(this, index, c, convertor);
    }

    @Override
    public Node<V> findChild(char c) {
        if (null == table) {
            return null;
        }
        Node<V> head = table[c & (table.length - 1)];
        if (head != null) {
            if (head.c == c) {
                return head;
            }
            return head.find(c);
        }
        return null;
    }

    @Override
    public void deleteChild(Node<V> child, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        int index = child.c & (table.length - 1);
        Node<V> head = table[index];
        table[index] = head.delete(child, convertor);
        this.decrement();
        this.reduce(convertor);
    }

    /**
     * 通过头节点查找与字符c相等的兄弟节点
     *
     * @param c 待查找节点的字符
     * @return 该字符对应的节点
     */
    public abstract Node<V> find(char c);

    /**
     * 通过头节点获取同一下标的所有兄弟节点
     *
     * @return 同一下标的所有节点
     */
    protected abstract List<Node<V>> findAll();

    /**
     * 通过头节点增加新节点：
     *
     * @param parent    父节点
     * @param index     头节点下标
     * @param c         新节点的字符
     * @param convertor 节点转换器
     * @return 如果不存在与字符c相等的原节点，则增加新节点并返回新节点；否则，返回原有节点
     */
    public abstract Node<V> insert(Node<V> parent, int index, char c, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor);

    /**
     * 通过头节点删除旧节点
     *
     * @param deletion  待删除节点
     * @param convertor 节点转换器（删除后如果树的高度低于阈值，通过 convertor 转换为链表）
     * @return 头节点
     */
    public abstract Node<V> delete(BaseNode<V> deletion, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor);

    /**
     * 扩容：扩容会导致冲突减小，因此树可能需要转换成链表
     *
     * @param convertor 节点转换器
     */
    private void expand(NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (!isNeedExpand()) {
            return;
        }

        Node<V>[] oldTab = table;
        int oldCap = oldTab.length;
        int newCap = oldCap << 1;
        Node<V>[] newTab = new Node[newCap];

        for (int i = 0; i < oldCap; i++) {
            Node<V> old = oldTab[i];
            if (old == null) {
                continue;
            }
            old.split(newTab, oldCap, i, convertor);
        }

        table = newTab;
    }

    /**
     * 扩容时分裂成两个头节点（判断阈值，树转换为链表）
     *
     * @param newTab    新数组
     * @param oldCap    旧数组容量
     * @param oldIndex  旧数组下标
     * @param convertor 节点转换器
     */
    public abstract void split(Node<V>[] newTab, int oldCap, int oldIndex, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor);

    /**
     * 缩容；缩容会导致冲突加大，同一 bucket 的 key数量增加，因此链表可能需要转换成 AVL树。
     *
     * @param convertor 节点转换器
     */
    private void reduce(NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (size == 0) {
            table = null;
            return;
        }

        if (!isNeedReduce()) {
            return;
        }

        Node<V>[] curTab = table;
        int curCap = curTab.length;
        int newCap = curCap >> 1;
        Node<V>[] newTab = new Node[newCap];
        for (int i = 0; i < newCap; i++) {
            Node<V> old = curTab[i];
            Node<V> old2 = curTab[i + newCap];
            if (old == null) {
                newTab[i] = old2;
                continue;
            }
            if (old2 == null) {
                newTab[i] = old;
                continue;
            }
            newTab[i] = old.join(old2, convertor);
        }
        table = newTab;
    }

    private boolean isNeedExpand() {
        if (size > TrieConstants.EXPAND_RANGE_63488) {
            return table.length <= TrieConstants.TABLE_HALF_CAPACITY;
        }
        return size >= ((table.length << 1) - 1);
    }

    /**
     * <b>是否需要缩容</b>
     * <p>
     * 根据 size 和 table.length 来计算判断是否需要缩容。
     * <p>
     * 1. 预留缓冲区间，避免增加一个 Key 就扩容，删除一个 key 就缩容；
     * <p>
     * 2. 缓冲区间随着 size 扩大而增加，最大为 8，避免空间浪费过多。
     * <p>
     * 为了避免循环乘除法导致的性能消耗，采用硬编码的方式。
     * <p>
     * 划分为16个step，采用折半查找，比较4次即能判断结果
     *
     * @return 根据缩容策略计算后得到是否需要缩容的布尔值
     */
    private boolean isNeedReduce() {
        int curCap = table.length;
        if (size > TrieConstants.REDUCE_RANGE_512) {
            if (size > TrieConstants.REDUCE_RANGE_8192) {
                if (size > TrieConstants.REDUCE_RANGE_32768) {
                    if (size > TrieConstants.REDUCE_RANGE_63488) {
                        return false;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_32768;
                    }
                } else {
                    if (size > TrieConstants.REDUCE_RANGE_16384) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_16384;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_8192;
                    }
                }
            } else {
                if (size > TrieConstants.REDUCE_RANGE_2048) {
                    if (size > TrieConstants.REDUCE_RANGE_4096) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_4096;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_2048;
                    }
                } else {
                    if (size > TrieConstants.REDUCE_RANGE_1024) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_1024;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_512;
                    }
                }
            }
        } else {
            if (size > TrieConstants.REDUCE_RANGE_32) {
                if (size > TrieConstants.REDUCE_RANGE_128) {
                    if (size > TrieConstants.REDUCE_RANGE_256) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_256;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_128;
                    }
                } else {
                    if (size > TrieConstants.REDUCE_RANGE_64) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_64;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_32;
                    }
                }
            } else {
                if (size > TrieConstants.REDUCE_RANGE_8) {
                    if (size > TrieConstants.REDUCE_RANGE_16) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_16;
                    } else {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_8;
                    }
                } else {
                    if (size > TrieConstants.REDUCE_RANGE_4) {
                        return size <= curCap - TrieConstants.REDUCE_THRESHOLD_4;
                    }
                    return size < curCap;
                }
            }
        }
    }

    /**
     * 缩容时合并两个数组下标的节点
     *
     * @param old       旧节点
     * @param convertor 节点转换器
     * @return 头节点
     */
    public abstract Node<V> join(Node<V> old, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor);

    @Override
    public Iterator<Node<V>> iterator() {
        return new NodeIterator<>(this);
    }

    /**
     * @author Patrick.Lau
     * @since 0.0.4 2021-10-30
     */
    private static class NodeIterator<V> implements Iterator<Node<V>> {

        private final List<Node<V>> list;
        private int index;

        public NodeIterator(Node<V> parent) {
            Node<V>[] tab = parent.table;
            if (tab == null || parent.size() == 0) {
                this.list = Collections.emptyList();
                return;
            }

            // 使用AvlTree来做字典序处理
            int count = 0;
            AvlNode<Node<V>> root = null;
            for (Node<V> node : tab) {
                if (node != null) {
                    List<Node<V>> nodes = node.findAll();
                    for (Node<V> next : nodes) {
                        ++count;
                        if (root == null) {
                            root = new AvlNode<>(next.c, next);
                        } else {
                            root = AvlNode.insertAndBalance(root, new AvlNode<>(next.c, next));
                        }
                    }
                }
            }

            assert root != null;
            List<Node<V>> nodes = new ArrayList<>(count);
            root.inorderTraversalValue(nodes);
            this.list = nodes;
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public Node<V> next() {
            return list.get(index++);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Node<?> node = (Node<?>) o;

        if (size != node.size) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(table, node.table);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + size;
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}
