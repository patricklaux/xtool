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
public class AvlNode<V> extends TreeNode<V> {

    private byte height;
    protected AvlNode<V> left;
    protected AvlNode<V> right;

    public AvlNode(char c) {
        super(c);
    }

    public AvlNode(char c, V value) {
        super(c, value);
    }

    public AvlNode(char c, V value, int size, Node<V>[] table) {
        super(c, value, size, table);
    }

    public static <V> AvlNode<V> balance(AvlNode<V> root) {
        int factor = balanceFactor(root);
        if (factor >= TrieConstants.AVL_RIGHT_ROTATE_THRESHOLD) {
            if (balanceFactor(root.left) <= TrieConstants.AVL_RIGHT_SLANT) {
                return rotateLeftRight(root);
            }
            return rotateRight(root);
        } else if (factor <= TrieConstants.AVL_LEFT_ROTATE_THRESHOLD) {
            if (balanceFactor(root.right) >= TrieConstants.AVL_LEFT_SLANT) {
                return rotateRightLeft(root);
            }
            return rotateLeft(root);
        } else {
            return root;
        }
    }

    public static <V> int balanceFactor(AvlNode<V> root) {
        return height(root.left) - height(root.right);
    }

    public static <V> void updateHeight(AvlNode<V> parent) {
        parent.height = (byte) (Math.max(height(parent.left), height(parent.right)) + 1);
    }

    public static <V> byte height(AvlNode<V> node) {
        return (node == null) ? -1 : node.height;
    }

    public static <V> AvlNode<V> rotateLeft(AvlNode<V> root) {
        AvlNode<V> right = root.right;
        root.right = right.left;
        right.left = root;
        updateHeight(root);
        updateHeight(right);
        return right;
    }

    public static <V> AvlNode<V> rotateRight(AvlNode<V> root) {
        AvlNode<V> left = root.left;
        root.left = left.right;
        left.right = root;
        updateHeight(root);
        updateHeight(left);
        return left;
    }

    public static <V> AvlNode<V> rotateLeftRight(AvlNode<V> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    public static <V> AvlNode<V> rotateRightLeft(AvlNode<V> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    /**
     * 扩容时，将树分裂成两部分，一部分转移到新数组的 oldIndex，一部分转移到新数组的 oldIndex + oldCap
     *
     * @param newTab   新数组
     * @param oldCap   旧容量
     * @param oldIndex 原位置
     */
    @Override
    public void split(Node<V>[] newTab, int oldCap, int oldIndex, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        AvlNode<V> loHead = null, hiHead = null;
        int lc = 0, hc = 0;

        List<Node<V>> nodes = this.findAll();
        for (Node<V> node : nodes) {
            AvlNode<V> avl = (AvlNode<V>) node;
            avl.left = avl.right = null;
            updateHeight(avl);
            if ((node.c & oldCap) == 0) {
                ++lc;
                if (loHead == null) {
                    loHead = avl;
                } else {
                    loHead = insertAndBalance(loHead, avl);
                }
            } else {
                ++hc;
                if (hiHead == null) {
                    hiHead = avl;
                } else {
                    hiHead = insertAndBalance(hiHead, avl);
                }
            }
        }

        // 原位置
        if (loHead != null) {
            setHeadToTable(newTab, loHead, lc, oldIndex, convertor);
        }
        // 原位置+oldCap
        if (hiHead != null) {
            setHeadToTable(newTab, hiHead, hc, oldIndex + oldCap, convertor);
        }
    }

    private static <V> void setHeadToTable(Node<V>[] newTab, AvlNode<V> head, int count, int index, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (count < TrieConstants.TO_TREE_NODE_THRESHOLD) {
            newTab[index] = ((NodeConvertor<? extends Node<V>, AvlNode<V>>) convertor).fromTreeNode(head);
            return;
        }
        newTab[index] = head;
    }

    /**
     * 缩容时合并原数组的两个位置的节点
     *
     * @param old 待合并的链表或AVL树的头节点
     * @return 合并后的AVL树头节点
     */
    @Override
    public Node<V> join(Node<V> old, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        AvlNode<V> root = this;
        List<Node<V>> nodes = old.findAll();
        if (old instanceof AvlNode) {
            for (Node<V> node : nodes) {
                AvlNode<V> avl = (AvlNode<V>) node;
                avl.left = avl.right = null;
                updateHeight(avl);
                root = insertAndBalance(root, avl);
            }
        } else {
            for (Node<V> node : nodes) {
                AvlNode<V> avl = new AvlNode<>(node.c, node.value, node.size, node.table);
                root = insertAndBalance(root, avl);
            }
        }
        return root;
    }

    @Override
    public Node<V> find(char c) {
        if (this.c > c) {
            if (null != left) {
                return left.find(c);
            }
            return null;
        } else if (this.c < c) {
            if (null != right) {
                return right.find(c);
            }
            return null;
        } else {
            return this;
        }
    }

    @Override
    protected List<Node<V>> findAll() {
        List<Node<V>> list = new LinkedList<>();
        this.inorderTraversal(list);
        return list;
    }

    @Override
    public Node<V> insert(Node<V> parent, int index, char c, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        AvlNode<V> root = this, node = new AvlNode<>(c);
        AvlNode<V> insertion = insert(root, node);
        if (node == insertion) {
            root = balance(root);
            parent.increment();
            parent.setHead(root, index);
        }
        return insertion;
    }

    public static <V> AvlNode<V> insertAndBalance(AvlNode<V> root, AvlNode<V> node) {
        AvlNode<V> insertion = insert(root, node);
        if (node == insertion) {
            root = balance(root);
        }
        return root;
    }

    public static <V> AvlNode<V> insert(AvlNode<V> root, AvlNode<V> node) {
        AvlNode<V> p = root;
        int depth = 0, maxDepth = root.height + 1;
        AvlNode<V>[] path = new AvlNode[maxDepth];
        while (depth < maxDepth) {
            path[depth] = p;
            if (p.c > node.c) {
                if (p.left == null) {
                    p.left = node;
                    if (p.right != null) {
                        return node;
                    }
                    break;
                } else {
                    p = p.left;
                    depth++;
                }
            } else if (p.c < node.c) {
                if (p.right == null) {
                    p.right = node;
                    if (p.left != null) {
                        return node;
                    }
                    break;
                } else {
                    p = p.right;
                    depth++;
                }
            } else {
                // 节点已在树中，无需回溯
                return p;
            }
        }
        backtrack(path, depth);
        return node;
    }

    @Override
    public Node<V> delete(BaseNode<V> node, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (node == null) {
            return this;
        }
        AvlNode<V> del = (AvlNode<V>) node;
        AvlNode<V> root = delete(this, del);
        if (root != null && root.height < TrieConstants.FROM_TREE_NODE_THRESHOLD) {
            return ((NodeConvertor<? extends Node<V>, AvlNode<V>>) convertor).fromTreeNode(root);
        }
        return root;
    }

    private static <V> AvlNode<V> delete(AvlNode<V> root, AvlNode<V> del) {
        int depth = 0;
        AvlNode<V> p = root;
        // 建立根节点至删除节点之间的回溯路径
        AvlNode<V>[] path = new AvlNode[root.height - del.height];
        while (p != null) {
            if (p.c == del.c) {
                if (root == p) {
                    return swap(root);
                }
                AvlNode<V> pp = path[--depth];
                if (pp.right == p) {
                    pp.right = swap(p);
                } else {
                    pp.left = swap(p);
                }
                backtrack(path, depth);
                return balance(root);
            }
            path[depth] = p;
            p = (p.c > del.c) ? p.left : p.right;
            depth++;
        }
        return root;
    }

    /**
     * 待删除节点与其前驱节点或后继节点交换位置
     *
     * @param del 待删除节点
     * @param <V> 值类型
     * @return 与删除节点同位置的节点
     */
    private static <V> AvlNode<V> swap(AvlNode<V> del) {
        AvlNode<V> dl = del.left, dr = del.right;
        if (height(dl) >= height(dr)) {
            if (dl != null) {
                // 查找左子树最大节点并交换删除节点
                return swapPredecessor(del, dl);
            }
            // 没有子节点
            return null;
        }
        // 查找右子树最小节点并交换删除节点
        return swapSuccessor(del, dr);
    }

    /**
     * 使用左子树的最大节点（排序后的前驱节点） 替换 删除节点
     *
     * @param del  待删除节点
     * @param swap 前驱节点（左孩子）
     * @param <V>  值类型
     * @return 与删除节点同位置的节点（可能为左子树的最大节点）
     */
    private static <V> AvlNode<V> swapPredecessor(AvlNode<V> del, AvlNode<V> swap) {
        AvlNode<V>[] path = new AvlNode[del.height];
        int depth = 0;
        while (swap.right != null) {
            depth++;
            path[depth] = swap;
            swap = swap.right;
        }

        if (depth > 0) {
            AvlNode<V> parent = path[depth];
            parent.right = swap.left;
            swap.left = del.left;
        }
        swap.right = del.right;

        path[0] = swap;
        backtrack(path, depth);
        return balance(swap);
    }

    /**
     * 使用右子树的最小节点（排序后的后驱节点） 替换 删除节点
     *
     * @param del  待删除节点
     * @param swap 右孩子
     * @param <V>  值类型
     * @return 与删除节点同位置的节点（可能为右子树的最小节点）
     */
    private static <V> AvlNode<V> swapSuccessor(AvlNode<V> del, AvlNode<V> swap) {
        AvlNode<V>[] path = new AvlNode[del.height];
        int depth = 0;
        while (swap.left != null) {
            depth++;
            path[depth] = swap;
            swap = swap.left;
        }

        if (depth > 0) {
            AvlNode<V> parent = path[depth];
            parent.left = swap.right;
            swap.right = del.right;
        }
        swap.left = del.left;
        path[0] = swap;
        backtrack(path, depth);
        return balance(swap);
    }

    /**
     * 回溯（删除节点后回溯更新高度）
     *
     * @param path  回溯路径 parent --> parent --> ... --> root
     * @param depth 回溯深度
     * @param <V>   值类型
     */
    private static <V> void backtrack(AvlNode<V>[] path, int depth) {
        for (int j = depth; j > 0; j--) {
            AvlNode<V> p = path[j];
            int height = p.height;
            AvlNode<V> pp = path[j - 1];
            if (pp.left == p) {
                pp.left = balance(p);
            } else {
                pp.right = balance(p);
            }
            updateHeight(p);
            if (p.height == height) {
                // 高度不变，无需继续回溯
                break;
            }
        }
        updateHeight(path[0]);
    }

    /**
     * 所有节点保存到给定列表（中序遍历）
     *
     * @param list 用于保存节点
     */
    public void inorderTraversal(List<Node<V>> list) {
        if (this.left != null) {
            this.left.inorderTraversal(list);
        }
        list.add(this);
        if (this.right != null) {
            this.right.inorderTraversal(list);
        }
    }

    /**
     * 所有节点的值保存到给定列表（中序遍历）
     *
     * @param list 用于保存节点的值
     */
    public void inorderTraversalValue(List<V> list) {
        if (this.left != null) {
            this.left.inorderTraversalValue(list);
        }
        list.add(this.value);
        if (this.right != null) {
            this.right.inorderTraversalValue(list);
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvlNode<?> avlNode)) return false;
        if (!super.equals(o)) return false;

        return height == avlNode.height && Objects.equals(left, avlNode.left) && Objects.equals(right, avlNode.right);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + height;
        result = 31 * result + Objects.hashCode(left);
        result = 31 * result + Objects.hashCode(right);
        return result;
    }

    @Override
    public String toString() {
        return "{\"height\":" + height(this) +
                ", \"c\":\"" + c + "\"" +
                (null != value ? (", \"value\":\"" + value + "\"") : "") +
                ((null != left) ? (", \"left\":" + left) : "") +
                ((null != right) ? (", \"right\":" + right) : "") +
                "}";
    }

}