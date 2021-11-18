package com.igeeksky.xtool.core.nlp;

import com.igeeksky.xtool.core.function.Tuple2;
import com.igeeksky.xtool.core.function.Tuples;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-17
 */
public class NodeTest {

    private final LinkedNodeCreator<String> creator = new LinkedNodeCreator<>();
    private final LinkedToAvlConvertor<String> convertor = new LinkedToAvlConvertor<>();

    @Test
    public void size() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        for (char c = 'a'; c <= 'z'; c++) {
            parent.addChild(c, creator, convertor);
        }
        Assert.assertEquals(26, parent.size);
    }

    @Test
    public void increment() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        Assert.assertEquals(0, parent.size);

        parent.increment();
        Assert.assertEquals(1, parent.size);
    }

    @Test
    public void decrement() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        Assert.assertEquals(0, parent.size);

        parent.increment();
        Assert.assertEquals(1, parent.size);

        parent.decrement();
        Assert.assertEquals(0, parent.size);
    }

    @Test
    public void setHead() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        Assert.assertEquals('a', parent.table[0].c);

        parent.setHead(new LinkedNode<>('b'), 0);
        Assert.assertEquals('b', parent.table[0].c);
    }

    @Test
    public void addChild() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        Assert.assertEquals('a', parent.table[0].c);
    }

    /**
     * 测试扩容是否符合预期
     */
    @Test
    @SuppressWarnings("unchecked")
    public void addChild2() {
        int finalLen = TrieConstants.TABLE_INITIAL_CAPACITY << 4;
        int finalMask = finalLen - 1;
        LinkedNode<String>[] finalTab = new LinkedNode[finalLen];

        LinkedNode<String> parent = new LinkedNode<>('0');
        int i = 0;
        int capacity = TrieConstants.TABLE_INITIAL_CAPACITY;
        for (char c = 'a'; c <= 'z'; c++) {
            int index = c & finalMask;
            LinkedNode<String> head = finalTab[index];
            if (null == head) {
                finalTab[index] = new LinkedNode<>(c);
            } else {
                LinkedNode<String> tail = head;
                while (tail.next != null) {
                    tail = tail.next;
                }
                tail.next = new LinkedNode<>(c);
            }

            parent.addChild(c, creator, convertor);
            if (++i > (capacity << 1)) {
                capacity = capacity << 1;
            }
            Assert.assertEquals(i, parent.size());
            Assert.assertEquals(capacity, parent.table.length);
        }

        Assert.assertEquals(finalLen, parent.table.length);

        for (int j = 0; j < finalLen; j++) {
            Node<String> node = finalTab[j];
            Node<String> node2 = parent.table[j];
            Assert.assertEquals(node.toString(), node2.toString());
        }
    }

    @Test
    public void findChild() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        parent.addChild('b', creator, convertor);

        Node<String> child = parent.findChild('a');

        Assert.assertEquals('a', child.c);
    }

    @Test
    public void deleteChild() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        parent.addChild('a', creator, convertor);
        parent.addChild('b', creator, convertor);

        Node<String> child = parent.findChild('a');
        Assert.assertEquals('a', child.c);

        parent.deleteChild(child, convertor);
        child = parent.findChild('a');
        Assert.assertNull(child);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteChild2() {
        int finalLen = 8;
        int finalMask = finalLen - 1;
        LinkedNode<String>[] finalTab = new LinkedNode[finalLen];

        LinkedNode<String> parent = new LinkedNode<>('0');
        for (char c = 'a'; c <= 'z'; c++) {
            parent.addChild(c, creator, convertor);
            System.out.println(parent.size + "\t" + parent.table.length);
            if (c <= 'h') {
                int index = c & finalMask;
                LinkedNode<String> head = finalTab[index];
                if (null == head) {
                    finalTab[index] = new LinkedNode<>(c);
                } else {
                    LinkedNode<String> tail = head;
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    tail.next = new LinkedNode<>(c);
                }
            }
        }

        for (char c = 'i'; c <= 'z'; c++) {
            Node<String> child = parent.findChild(c);
            parent.deleteChild(child, convertor);
            System.out.println(parent.size() + "\t" + parent.table.length);
        }

        Assert.assertEquals(finalLen, parent.table.length);
        Assert.assertEquals(finalLen, parent.size);

        for (int j = 0; j < finalLen; j++) {
            Node<String> node = finalTab[j];
            Node<String> node2 = parent.table[j];
            if (null != node) {
                Assert.assertEquals(node.toString(), node2.toString());
            }
        }
    }

    @Test
    public void deleteChild3() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        for (int c = 0; c < 66; c++) {
            parent.addChild((char) c, creator, convertor);
            System.out.println(parent.size() + "\t" + parent.table.length);
        }

        List<Tuple2<Integer, Integer>> list = new ArrayList<>(66);
        for (int c = 0; c < 66; c++) {
            Node<String> child = parent.findChild((char) c);
            parent.deleteChild(child, convertor);
            list.add(Tuples.of(parent.size(), (null != parent.table) ? parent.table.length : 0));
        }

        Collections.reverse(list);

        for (Tuple2<Integer, Integer> tuple2 : list) {
            System.out.println(tuple2.getT1() + "\t" + tuple2.getT2());
        }

        Assert.assertEquals(0, parent.size());
        Assert.assertNull(parent.table);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteChild4() {
        int finalLen = 8;
        int finalMask = finalLen - 1;
        LinkedNode<String>[] finalTab = new LinkedNode[finalLen];
        List<Character> list = new ArrayList<>(8192);

        LinkedNode<String> parent = new LinkedNode<>('0');
        for (int i = 0, j = 0; i < 65536; i++) {
            char c = (char) i;
            if ((i & finalMask) == 1) {
                parent.addChild(c, creator, convertor);
                //System.out.println(parent.size + "\t" + parent.table.length);
                list.add(c);
                if (j < 8) {
                    LinkedNode<String> head = finalTab[1];
                    LinkedNode<String> node = new LinkedNode<>(c);
                    if (null == head) {
                        finalTab[1] = node;
                    } else {
                        LinkedNode<String> tail = head;
                        while (tail.next != null) {
                            tail = tail.next;
                        }
                        tail.next = node;
                    }
                    j++;
                }
            }
        }

        Collections.reverse(list);

        for (int i = 0; i < list.size() - 8; i++) {
            Node<String> child = parent.findChild(list.get(i));
            parent.deleteChild(child, convertor);
        }

        Assert.assertEquals(finalLen, parent.table.length);
        Assert.assertEquals(finalLen, parent.size);

        for (int j = 0; j < finalLen; j++) {
            LinkedNode<String> node = finalTab[j];
            Node<String> node1 = parent.table[j];
            if (null != node) {
                Assert.assertTrue(node1 instanceof AvlNode);
                List<Node<String>> all = convertor.toTreeNode(node).findAll();
                List<Node<String>> all1 = node1.findAll();
                Assert.assertEquals(all.size(), all1.size());
                for (int i = 0; i < all.size(); i++) {
                    Assert.assertEquals(all.get(i).getC(), all1.get(i).getC());
                }
            } else {
                Assert.assertNull(node1);
            }
        }
    }

    @Test
    public void iterator() {
        LinkedNode<String> parent = new LinkedNode<>('0');
        for (int c = 65; c < 91; c++) {
            parent.addChild((char) c, creator, convertor);
            System.out.println(parent.size() + "\t" + parent.table.length);
        }

        Iterator<Node<String>> iterator = parent.iterator();
        int i = 65;
        while (iterator.hasNext()) {
            Node<String> next = iterator.next();
            Assert.assertEquals(i++, next.c);
        }
    }
}