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
class AvlNode<V> extends TreeNode<V> {

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
     * ????????????????????????????????????????????????????????????????????? oldIndex????????????????????????????????? oldIndex + oldCap
     *
     * @param newTab   ?????????
     * @param oldCap   ?????????
     * @param oldIndex ?????????
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

        //?????????
        if (loHead != null) {
            setHeadToTable(newTab, loHead, lc, oldIndex, convertor);
        }
        //?????????+oldCap
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
     * ????????????????????????????????????????????????
     *
     * @param old ?????????????????????AVL???????????????
     * @return ????????????AVL????????????
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
        int maxDepth = root.height + 1;
        AvlNode<V>[] path = new AvlNode[maxDepth];
        AvlNode<V> p = root;
        int depth = 0;
        while (depth < maxDepth) {
            path[depth] = p;
            if (p.c > node.c) {
                if (p.left == null) {
                    p.left = node;
                    break;
                } else {
                    p = p.left;
                    depth++;
                }
            } else if (p.c < node.c) {
                if (p.right == null) {
                    p.right = node;
                    break;
                } else {
                    p = p.right;
                    depth++;
                }
            } else {
                // ?????????????????????????????????
                return p;
            }
        }
        retracing(path, depth);
        return node;
    }

    @Override
    public Node<V> delete(BaseNode<V> node, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor) {
        if (node == null) {
            return this;
        }
        AvlNode<V> root = this;
        AvlNode<V> delete = (AvlNode<V>) node;
        if (root.c == delete.c) {
            root = swap(root);
        } else {
            root = delete(root, delete);
        }
        if (root != null) {
            if (root.height < TrieConstants.FROM_TREE_NODE_THRESHOLD) {
                return ((NodeConvertor<? extends Node<V>, AvlNode<V>>) convertor).fromTreeNode(root);
            }
        }
        return root;
    }

    private static <V> AvlNode<V> delete(AvlNode<V> root, AvlNode<V> delete) {
        // ????????????????????? root --- delete ?????????
        int maxDepth = root.height - delete.height;
        // ?????? ????????? ??? ????????????????????? ???????????????????????????????????????????????????????????????????????????????????????
        AvlNode<V>[] path = new AvlNode[maxDepth];
        AvlNode<V> p = root;
        int depth = 0;
        for (; depth < maxDepth; depth++) {
            path[depth] = p;
            if (p.c > delete.c) {
                if (p.left == null) {
                    return root;
                }
                if (p.left.c == delete.c) {
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    return root;
                }
                if (p.right.c == delete.c) {
                    break;
                }
                p = p.right;
            }
        }

        // p ???????????????????????????
        p = path[depth];
        if (p.left != null && p.left.c == delete.c) {
            p.left = swap(delete);
        } else {
            p.right = swap(delete);
        }

        retracing(path, depth);
        return balance(root);
    }

    /**
     * ????????????????????????????????????????????????????????????
     *
     * @param delete ???????????????
     * @param <V>    ?????????
     * @return ?????????????????????????????????
     */
    private static <V> AvlNode<V> swap(AvlNode<V> delete) {
        AvlNode<V> pl = delete.left, pr = delete.right;
        if (height(pl) >= height(pr)) {
            if (pl != null) {
                // ????????????????????????????????????????????????
                return swapPredecessor(delete, pl);
            }
            //???????????????
            return null;
        }
        // ????????????????????????????????????????????????
        return swapSuccessor(delete, pr);
    }

    /**
     * ???????????????????????????????????????????????????????????? ?????? ????????????
     *
     * @param delete ???????????????
     * @param pred   ???????????????????????????
     * @param <V>    ?????????
     * @return ????????????????????????????????????????????????????????????????????????
     */
    private static <V> AvlNode<V> swapPredecessor(AvlNode<V> delete, AvlNode<V> pred) {
        AvlNode<V>[] path = new AvlNode[delete.height];
        int depth = 0;
        while (pred.right != null) {
            depth++;
            path[depth] = pred;
            pred = pred.right;
        }

        if (depth > 0) {
            AvlNode<V> parent = path[depth];
            parent.right = pred.left;
            pred.left = delete.left;
        }
        pred.right = delete.right;

        path[0] = pred;
        retracing(path, depth);
        return balance(pred);
    }

    /**
     * ???????????????????????????????????????????????????????????? ?????? ????????????
     *
     * @param delete ???????????????
     * @param succ   ?????????
     * @param <V>    ?????????
     * @return ????????????????????????????????????????????????????????????????????????
     */
    private static <V> AvlNode<V> swapSuccessor(AvlNode<V> delete, AvlNode<V> succ) {
        AvlNode<V>[] path = new AvlNode[delete.height];
        int depth = 0;
        while (succ.left != null) {
            depth++;
            path[depth] = succ;
            succ = succ.left;
        }

        if (depth > 0) {
            AvlNode<V> parent = path[depth];
            parent.left = succ.right;
            succ.right = delete.right;
        }
        succ.left = delete.left;

        path[0] = succ;
        retracing(path, depth);
        return balance(succ);
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param path  ???????????? parent --> parent --> ... --> root
     * @param depth ????????????
     * @param <V>   ?????????
     */
    private static <V> void retracing(AvlNode<V>[] path, int depth) {
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
            // ?????????????????????????????????
            if (p.height == height) {
                break;
            }
        }
        updateHeight(path[0]);
    }

    /**
     * ???????????????????????????????????????????????????
     *
     * @param list ??????????????????
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
     * ?????????????????????????????????????????????????????????
     *
     * @param list ????????????????????????
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvlNode)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        AvlNode<?> avlNode = (AvlNode<?>) o;

        if (height != avlNode.height) {
            return false;
        }
        if (!Objects.equals(left, avlNode.left)) {
            return false;
        }
        return Objects.equals(right, avlNode.right);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) height;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
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