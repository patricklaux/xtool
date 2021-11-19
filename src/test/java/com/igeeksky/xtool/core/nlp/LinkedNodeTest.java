package com.igeeksky.xtool.core.nlp;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertSame(next, find);
    }

    @Test
    public void find() {
        LinkedNode<String> head = new LinkedNode<>('a');
        LinkedNode<String> next = new LinkedNode<>('b');
        head.setNext(next);

        Node<String> find = head.find('a');
        Assert.assertSame(head, find);

        find = head.find('b');
        Assert.assertSame(next, find);

        find = head.find('c');
        Assert.assertNull(find);
    }

    @Test
    public void findAll() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        for (char c = 'a'; c <= 'z'; c++) {
            parent.addChild(c, creator, convertor);
        }
        Assert.assertEquals(26, parent.size);
        Assert.assertEquals(16, parent.table.length);

        Node<String> head = parent.table['a' & (16 - 1)];

        List<Node<String>> all = head.findAll();
        Assert.assertEquals(2, all.size());

        Assert.assertEquals('a', all.get(0).c);
        Assert.assertEquals('q', all.get(1).c);
    }

    @Test
    public void insert() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        for (char c = 'a'; c <= 'g'; c++) {
            Node<String> head = parent.table[0];
            head.insert(parent, 0, c, convertor);
        }
        Assert.assertEquals(7, parent.size);

        Node<String> head = parent.table[0];
        List<Node<String>> all = head.findAll();
        Assert.assertEquals(7, all.size());
        Assert.assertTrue(head instanceof LinkedNode);

        head.insert(parent, 0, 'h', convertor);
        Assert.assertEquals(8, parent.size);

        head = parent.table[0];
        all = head.findAll();
        Assert.assertEquals(8, all.size());
        Assert.assertTrue(head instanceof AvlNode);
    }

    @Test
    public void delete() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        for (char c = 'a'; c <= 'g'; c++) {
            Node<String> head = parent.table[0];
            head.insert(parent, 0, c, convertor);
        }
        Assert.assertEquals(7, parent.size);

        Node<String> head = parent.table[0];
        List<Node<String>> all = head.findAll();
        Assert.assertEquals(7, all.size());
        Assert.assertTrue(head instanceof LinkedNode);

        Assert.assertEquals('a', head.c);

        Node<String> delete = head.find('a');
        head = head.delete(delete, convertor);

        Assert.assertEquals('b', head.c);
        Assert.assertTrue(head instanceof LinkedNode);

        all = head.findAll();
        Assert.assertEquals(6, all.size());

        head = head.delete(new LinkedNode<>('f'), convertor);
        Assert.assertEquals('b', head.c);
        Assert.assertTrue(head instanceof LinkedNode);

        all = head.findAll();
        Assert.assertEquals(5, all.size());
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
        Assert.assertEquals(expected, head.toString());

        head.split(newTab, oldCap, oldIndex, convertor);

        expected = "{\"c\":\"A\", \"next\":{\"c\":\"E\", \"next\":{\"c\":\"I\", \"next\":{\"c\":\"M\", \"next\":{\"c\":\"Q\", \"next\":{\"c\":\"U\", \"next\":{\"c\":\"Y\"}}}}}}}";
        Assert.assertEquals(expected, newTab[oldIndex].toString());
        System.out.println(split1);
        Assert.assertEquals(split1.toString(), newTab[oldIndex].toString());

        expected = "{\"c\":\"C\", \"next\":{\"c\":\"G\", \"next\":{\"c\":\"K\", \"next\":{\"c\":\"O\", \"next\":{\"c\":\"S\", \"next\":{\"c\":\"W\"}}}}}}";
        Assert.assertEquals(expected, newTab[oldIndex + oldCap].toString());
        System.out.println(split2);
        Assert.assertNotNull(split2);
        Assert.assertEquals(split2.toString(), newTab[oldIndex + oldCap].toString());
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
        Assert.assertEquals(expected, head3.toString());
        Assert.assertEquals(expected, head.toString());
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
        Assert.assertEquals(expected, head3.toString());
        Assert.assertEquals(expected, head.toString());
    }

    @Test
    public void testEquals() {
        LinkedNode<String> root = new LinkedNode<>('a');
        LinkedNode<String> root2 = new LinkedNode<>('a');
        Assert.assertEquals(root, root2);
    }

    @Test
    public void testHashCode() {
        LinkedNode<String> root = new LinkedNode<>('a');
        LinkedNode<String> root2 = new LinkedNode<>('a');
        Assert.assertEquals(root.hashCode(), root2.hashCode());
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