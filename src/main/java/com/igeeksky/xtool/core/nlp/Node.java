package com.igeeksky.xtool.core.nlp;


import java.util.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-12
 */
@SuppressWarnings("unchecked")
public abstract class Node<V> extends BaseNode<V> {

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
            Node<V> node = creator.apply(c);
            table[index] = node;
            this.increment();
            return node;
        }

        return head.insert(this, index, c, convertor);
    }

    @Override
    public Node<V> findChild(char c) {
        if (null == table) {
            return null;
        }
        Node<V> head = table[c & (table.length - 1)];
        return (head != null) ? head.find(c) : null;
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
        if (size <= ((table.length << 1) - 1)) {
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

        Node<V>[] curTab = table;
        int curCap = curTab.length;
        if (!isNeedReduce(curCap)) {
            return;
        }

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

    /**
     * 是否需要缩容
     * <p>
     * 根据当前 size 和 table.length 来计算判断是否需要缩容。
     * <p>
     * 1. 需要预留一定的缓冲，避免增加一个Key就扩容，删除一个key就缩容；
     * <p>
     * 2. 避免空间浪费过多。
     *
     * @param curCap 当前容量
     * @return 根据缩容策略计算后得到是否需要缩容的布尔值
     */
    private boolean isNeedReduce(int curCap) {
        if (size > TrieConstants.REDUCE_RANGE_32) {
            return size <= curCap - TrieConstants.REDUCE_THRESHOLD_32;
        }
        if (size > TrieConstants.REDUCE_RANGE_16) {
            return size <= curCap - TrieConstants.REDUCE_THRESHOLD_16;
        }
        if (size > TrieConstants.REDUCE_RANGE_8) {
            return size <= curCap - TrieConstants.REDUCE_THRESHOLD_8;
        }
        if (size > TrieConstants.REDUCE_RANGE_4) {
            return size <= curCap - TrieConstants.REDUCE_THRESHOLD_4;
        }
        return size < curCap;
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
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        if (!super.equals(o)) return false;

        Node<?> node = (Node<?>) o;

        if (size != node.size) return false;
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
