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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-17
 */
public class LinkedNodeTest {

    private final LinkedNodeCreator<String> creator = new LinkedNodeCreator<>();
    private final LinkedToAvlConvertor<String> convertor = new LinkedToAvlConvertor<>();

    @Test
    public void setNext() {
        LinkedNode<String> node = new LinkedNode<>('a');
        LinkedNode<String> next = new LinkedNode<>('b');
        node.setNext(next);
        Node<String> find = node.find('b');
        Assertions.assertSame(next, find);
    }

    @Test
    public void find() {
        LinkedNode<String> head = new LinkedNode<>('a');
        LinkedNode<String> next = new LinkedNode<>('b');
        head.setNext(next);

        Node<String> find = head.find('a');
        Assertions.assertSame(head, find);

        find = head.find('b');
        Assertions.assertSame(next, find);

        find = head.find('c');
        Assertions.assertNull(find);
    }

    @Test
    public void findAll() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        for (char c = 'a'; c <= 'z'; c++) {
            parent.addChild(c, creator, convertor);
        }
        Assertions.assertEquals(26, parent.size);
        Assertions.assertEquals(16, parent.table.length);

        Node<String> head = parent.table['a' & (16 - 1)];

        List<Node<String>> all = head.findAll();
        Assertions.assertEquals(2, all.size());

        Assertions.assertEquals('a', all.get(0).c);
        Assertions.assertEquals('q', all.get(1).c);
    }

    @Test
    public void insert() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        for (char c = 'a'; c <= 'g'; c++) {
            Node<String> head = parent.table[0];
            head.insert(parent, 0, c, convertor);
        }
        Assertions.assertEquals(7, parent.size);

        Node<String> head = parent.table[0];
        List<Node<String>> all = head.findAll();
        Assertions.assertEquals(7, all.size());
        Assertions.assertInstanceOf(LinkedNode.class, head);

        head.insert(parent, 0, 'h', convertor);
        Assertions.assertEquals(8, parent.size);

        head = parent.table[0];
        all = head.findAll();
        Assertions.assertEquals(8, all.size());
        Assertions.assertInstanceOf(AvlNode.class, head);
    }

    @Test
    public void delete() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        for (char c = 'a'; c <= 'g'; c++) {
            Node<String> head = parent.table[0];
            head.insert(parent, 0, c, convertor);
        }
        Assertions.assertEquals(7, parent.size);

        Node<String> head = parent.table[0];
        List<Node<String>> all = head.findAll();
        Assertions.assertEquals(7, all.size());
        Assertions.assertInstanceOf(LinkedNode.class, head);

        Assertions.assertEquals('a', head.c);

        Node<String> delete = head.find('a');
        head = head.delete(delete, convertor);

        Assertions.assertEquals('b', head.c);
        Assertions.assertInstanceOf(LinkedNode.class, head);

        all = head.findAll();
        Assertions.assertEquals(6, all.size());

        head = head.delete(new LinkedNode<>('f'), convertor);
        Assertions.assertEquals('b', head.c);
        Assertions.assertInstanceOf(LinkedNode.class, head);

        all = head.findAll();
        Assertions.assertEquals(5, all.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void split() {
        int oldCap = 2;
        int oldMask = oldCap - 1;
        int newCap = oldCap << 1;
        int newMask = newCap - 1;

        Node<String>[] newTab = new Node[newCap];
        LinkedNode<String> head = null, tail = null;

        LinkedNode<String> split1 = null, tail1 = null, split2 = null, tail2 = null;

        int oldIndex = 1;
        for (int c = 65; c < 91; c++) {
            if ((c & oldMask) == oldIndex) {
                LinkedNode<String> node = new LinkedNode<>((char) c);
                if (tail == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }

                LinkedNode<String> node2 = new LinkedNode<>((char) c);
                if ((c & newMask) == oldIndex) {
                    if (tail1 == null) {
                        split1 = tail1 = node2;
                    } else {
                        tail1.next = node2;
                        tail1 = node2;
                    }
                } else {
                    if (tail2 == null) {
                        split2 = tail2 = node2;
                    } else {
                        tail2.next = node2;
                        tail2 = node2;
                    }
                }
            }
        }

        String expected = "{\"c\":\"A\", \"next\":{\"c\":\"C\", \"next\":{\"c\":\"E\", \"next\":{\"c\":\"G\", \"next\":{\"c\":\"I\", \"next\":{\"c\":\"K\", \"next\":{\"c\":\"M\", \"next\":{\"c\":\"O\", \"next\":{\"c\":\"Q\", \"next\":{\"c\":\"S\", \"next\":{\"c\":\"U\", \"next\":{\"c\":\"W\", \"next\":{\"c\":\"Y\"}}}}}}}}}}}}}";
        Assertions.assertEquals(expected, head.toString());

        head.split(newTab, oldCap, oldIndex, convertor);

        expected = "{\"c\":\"A\", \"next\":{\"c\":\"E\", \"next\":{\"c\":\"I\", \"next\":{\"c\":\"M\", \"next\":{\"c\":\"Q\", \"next\":{\"c\":\"U\", \"next\":{\"c\":\"Y\"}}}}}}}";
        Assertions.assertEquals(expected, newTab[oldIndex].toString());
        System.out.println(split1);
        Assertions.assertEquals(split1.toString(), newTab[oldIndex].toString());

        expected = "{\"c\":\"C\", \"next\":{\"c\":\"G\", \"next\":{\"c\":\"K\", \"next\":{\"c\":\"O\", \"next\":{\"c\":\"S\", \"next\":{\"c\":\"W\"}}}}}}";
        Assertions.assertEquals(expected, newTab[oldIndex + oldCap].toString());
        System.out.println(split2);
        Assertions.assertNotNull(split2);
        Assertions.assertEquals(split2.toString(), newTab[oldIndex + oldCap].toString());
    }

    @Test
    public void join() {
        String text = "niohk";
        Node<String> head = insert(text);

        String text2 = "pfjlm";
        Node<String> head2 = insert(text2);

        String text3 = "niohkpfjlm";
        Node<String> head3 = insert(text3);

        head = head.join(head2, convertor);

        String expected = "{\"height\":3, \"c\":\"k\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":0, \"c\":\"j\"}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        Assertions.assertEquals(expected, head3.toString());
        Assertions.assertEquals(expected, head.toString());
    }

    @Test
    public void testJoin1() {
        String text = "pfjlm";
        Node<String> head = insert(text);

        String text2 = "niohk";
        Node<String> head2 = AvlNodeTest.buildAvlTree(text2, text2.length());

        String text3 = "niohkpfjlm";
        Node<String> head3 = insert(text3);

        head = head.join(head2, convertor);

        String expected = "{\"height\":3, \"c\":\"k\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":0, \"c\":\"j\"}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        Assertions.assertEquals(expected, head3.toString());
        Assertions.assertEquals(expected, head.toString());
    }

    @Test
    public void testEquals() {
        LinkedNode<String> root = new LinkedNode<>('a');
        LinkedNode<String> root2 = new LinkedNode<>('a');
        Assertions.assertEquals(root, root2);
    }

    @Test
    public void testHashCode() {
        LinkedNode<String> root = new LinkedNode<>('a');
        LinkedNode<String> root2 = new LinkedNode<>('a');
        Assertions.assertEquals(root.hashCode(), root2.hashCode());
    }

    private Node<String> insert(String text) {
        char[] chars = text.toCharArray();
        LinkedNode<String> head = null, tail = null;
        for (char c : chars) {
            LinkedNode<String> node = new LinkedNode<>(c);
            if (tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        if (chars.length >= TrieConstants.TO_TREE_NODE_THRESHOLD) {
            return convertor.toTreeNode(head);
        }
        return head;
    }
}