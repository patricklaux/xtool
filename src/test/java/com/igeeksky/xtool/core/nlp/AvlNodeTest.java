package com.igeeksky.xtool.core.nlp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-29
 */
public class AvlNodeTest {

    private final LinkedToAvlConvertor<String> convertor = new LinkedToAvlConvertor<>();

    @Test
    public void balance() {
        String value = "ba";
        char[] chars = value.toCharArray();

        AvlNode<String> root = new AvlNode<>('c');
        for (char c : chars) {
            AvlNode.insert(root, new AvlNode<>(c));
        }
        root = AvlNode.balance(root);
        System.out.println(root);
        String expected = "{\"height\":1, \"c\":\"b\", \"left\":{\"height\":0, \"c\":\"a\"}, \"right\":{\"height\":0, \"c\":\"c\"}}";
        Assert.assertEquals(expected, root.toString());
    }

    @Test
    public void balanceFactor() {
        String value = "ba";
        char[] chars = value.toCharArray();

        AvlNode<String> root = new AvlNode<>('c');
        for (char c : chars) {
            AvlNode.insert(root, new AvlNode<>(c));
        }

        System.out.println(AvlNode.balanceFactor(root));
        Assert.assertEquals(2, AvlNode.balanceFactor(root));

        root = AvlNode.balance(root);
        System.out.println(AvlNode.balanceFactor(root));
        Assert.assertEquals(0, AvlNode.balanceFactor(root));
    }

    @Test
    public void height() {
        String value = "ba";
        char[] chars = value.toCharArray();

        AvlNode<String> root = new AvlNode<>('c');
        for (char c : chars) {
            AvlNode.insert(root, new AvlNode<>(c));
        }

        System.out.println(AvlNode.height(root));
        Assert.assertEquals(2, AvlNode.height(root));

        root = AvlNode.balance(root);
        System.out.println(AvlNode.height(root));
        Assert.assertEquals(1, AvlNode.height(root));
    }

    @Test
    public void rotateLeft() {
        String value = "bc";
        char[] chars = value.toCharArray();

        AvlNode<String> root = new AvlNode<>('a');
        for (char c : chars) {
            AvlNode.insert(root, new AvlNode<>(c));
        }

        System.out.println(AvlNode.balanceFactor(root));
        root = AvlNode.rotateLeft(root);
        System.out.println(root);

        Assert.assertEquals(0, AvlNode.balanceFactor(root));
        String expected = "{\"height\":1, \"c\":\"b\", \"left\":{\"height\":0, \"c\":\"a\"}, \"right\":{\"height\":0, \"c\":\"c\"}}";
        Assert.assertEquals(expected, root.toString());
    }

    @Test
    public void rotateRight() {
        String value = "ba";
        char[] chars = value.toCharArray();

        AvlNode<String> root = new AvlNode<>('c');
        for (char c : chars) {
            AvlNode.insert(root, new AvlNode<>(c));
        }

        System.out.println(AvlNode.balanceFactor(root));
        root = AvlNode.rotateRight(root);
        System.out.println(root);

        Assert.assertEquals(0, AvlNode.balanceFactor(root));
        String expected = "{\"height\":1, \"c\":\"b\", \"left\":{\"height\":0, \"c\":\"a\"}, \"right\":{\"height\":0, \"c\":\"c\"}}";
        Assert.assertEquals(expected, root.toString());
    }

    /**
     * 初始形状：
     * <pre>
     *                         n
     *               i              o
     *           h       k              p
     *       f        j     l
     *                        m
     * </pre>
     * 最终形状：
     * <pre>
     *                        k
     *               i                 n
     *           h       j         l       o
     *        f                       m       p
     * </pre>
     */
    @Test
    public void rotateLeftRight() {
        String value = "iohkpfjlm";
        char[] chars = value.toCharArray();
        int last = chars.length - 1;
        AvlNode<String> root = new AvlNode<>('n');
        for (int i = 0; i < last; i++) {
            root = AvlNode.insertAndBalance(root, new AvlNode<>(chars[i]));
        }

        AvlNode.insert(root, new AvlNode<>(chars[last]));

        String expected = "{\"height\":4, \"c\":\"n\", \"left\":{\"height\":3, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":2, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"j\"}, \"right\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}";
        Assert.assertEquals(expected, root.toString());

        root = AvlNode.rotateLeftRight(root);
        expected = "{\"height\":3, \"c\":\"k\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":0, \"c\":\"j\"}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        findError(expected, root.toString());
        Assert.assertEquals("fhijklmnop", convert(root));
    }

    /**
     * 初始形状：
     * <pre>
     *                i
     *           h         n
     *       f          k     o
     *                j   l     p
     *                     m
     *
     * </pre>
     * 最终形状：
     * <pre>
     *                        k
     *               i                 n
     *           h       j         l       o
     *        f                       m       p
     * </pre>
     */
    @Test
    public void rotateRightLeft() {
        String value = "ihnfkojlpm";
        char[] chars = value.toCharArray();
        int last = chars.length - 1;
        AvlNode<String> root = new AvlNode<>('n');
        for (int i = 0; i < last; i++) {
            root = AvlNode.insertAndBalance(root, new AvlNode<>(chars[i]));
        }

        AvlNode.insert(root, new AvlNode<>(chars[last]));

        String expected = "{\"height\":4, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":3, \"c\":\"n\", \"left\":{\"height\":2, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"j\"}, \"right\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        Assert.assertEquals(expected, root.toString());

        root = AvlNode.rotateRightLeft(root);
        expected = "{\"height\":3, \"c\":\"k\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":0, \"c\":\"j\"}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        findError(expected, root.toString());
        Assert.assertEquals("fhijklmnop", convert(root));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void split() {
        int oldCap = 2;
        int oldMask = oldCap - 1;
        int newCap = oldCap << 1;
        int newMask = newCap - 1;

        Node<String>[] newTab = new Node[newCap];

        AvlNode<String> head = null;
        AvlNode<String> split1 = null;
        AvlNode<String> split2 = null;

        int oldIndex = 1;
        for (int i = 65; i < 91; i++) {
            if ((i & oldMask) == oldIndex) {
                AvlNode<String> avl = new AvlNode<>((char) i);
                if (head == null) {
                    head = avl;
                } else {
                    head = AvlNode.insertAndBalance(head, avl);
                }

                AvlNode<String> avl2 = new AvlNode<>((char) i);
                if ((i & newMask) == oldIndex) {
                    if (split1 == null) {
                        split1 = avl2;
                    } else {
                        split1 = AvlNode.insertAndBalance(split1, avl2);
                    }
                } else {
                    if (split2 == null) {
                        split2 = avl2;
                    } else {
                        split2 = AvlNode.insertAndBalance(split2, avl2);
                    }
                }
            }
        }

        String expected = "{\"height\":3, \"c\":\"O\", \"left\":{\"height\":2, \"c\":\"G\", \"left\":{\"height\":1, \"c\":\"C\", \"left\":{\"height\":0, \"c\":\"A\"}, \"right\":{\"height\":0, \"c\":\"E\"}}, \"right\":{\"height\":1, \"c\":\"K\", \"left\":{\"height\":0, \"c\":\"I\"}, \"right\":{\"height\":0, \"c\":\"M\"}}}, \"right\":{\"height\":2, \"c\":\"S\", \"left\":{\"height\":0, \"c\":\"Q\"}, \"right\":{\"height\":1, \"c\":\"W\", \"left\":{\"height\":0, \"c\":\"U\"}, \"right\":{\"height\":0, \"c\":\"Y\"}}}}";
        Assert.assertEquals(expected, head.toString());

        head.split(newTab, oldCap, oldIndex, convertor);

        expected = "{\"c\":\"A\", \"next\":{\"c\":\"E\", \"next\":{\"c\":\"I\", \"next\":{\"c\":\"M\", \"next\":{\"c\":\"Q\", \"next\":{\"c\":\"U\", \"next\":{\"c\":\"Y\"}}}}}}}";
        Assert.assertEquals(expected, newTab[oldIndex].toString());
        System.out.println(split1);
        Assert.assertEquals(convertor.fromTreeNode(split1).toString(), newTab[oldIndex].toString());

        expected = "{\"c\":\"C\", \"next\":{\"c\":\"G\", \"next\":{\"c\":\"K\", \"next\":{\"c\":\"O\", \"next\":{\"c\":\"S\", \"next\":{\"c\":\"W\"}}}}}}";
        Assert.assertEquals(expected, newTab[oldIndex + oldCap].toString());
        System.out.println(split2);
        Assert.assertEquals(convertor.fromTreeNode(split2).toString(), newTab[oldIndex + oldCap].toString());
    }

    @Test
    public void join() {
        String text = "niohk";
        AvlNode<String> head = buildAvlTree(text, text.length());

        String text2 = "pfjlm";
        AvlNode<String> head2 = buildAvlTree(text2, text2.length());

        head = (AvlNode<String>) head.join(head2, convertor);

        String text3 = "niohkfjlmp";
        AvlNode<String> head3 = buildAvlTree(text3, text3.length());

        String expected = "{\"height\":3, \"c\":\"l\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":1, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"j\"}}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":0, \"c\":\"m\"}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        Assert.assertEquals(expected, head3.toString());
        Assert.assertEquals(head3.toString(), head.toString());
    }

    @Test
    public void fromAvl() {
        String value = "niohkpfjlm";
        AvlNode<String> root = buildAvlTree(value, value.length());

        StringBuilder builder = new StringBuilder(10);
        LinkedNode<String> node = convertor.fromTreeNode(root);
        while (node != null) {
            builder.append(node.c);
            node = node.next;
        }

        Assert.assertEquals("fhijklmnop", builder.toString());
    }

    @Test
    public void toAvl() {
        String value = "iohkpfjlm";
        LinkedNode<String> head = new LinkedNode<>('n'), tail = head;
        char[] chars = value.toCharArray();
        for (char c : chars) {
            tail = tail.next = new LinkedNode<>(c);
        }

        AvlNode<String> root = convertor.toTreeNode(head);
        System.out.println(root);
        String expected = "{\"height\":3, \"c\":\"k\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":0, \"c\":\"j\"}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        Assert.assertEquals(expected, root.toString());
    }

    @Test
    public void find() {
        String value = "niohkpfjlm";
        AvlNode<String> root = buildAvlTree(value, value.length());
        Node<String> node = root.find('i');
        Assert.assertEquals('i', node.c);
    }

    @Test
    public void testFind() {
        String value = "niohkpfjlm";
        AvlNode<String> root = buildAvlTree(value, value.length());
        Node<String> node = root.find('a');
        Assert.assertNull(node);
    }

    @Test
    public void findAll() {
        String value = "niohkpfjlm";
        AvlNode<String> root = buildAvlTree(value, value.length());
        List<Node<String>> all = root.findAll();
        StringBuilder builder = new StringBuilder(10);
        for (Node<String> node : all) {
            builder.append(node.c);
        }
        Assert.assertEquals("fhijklmnop", builder.toString());
    }

    @Test
    public void insert() {
        Node<String> parent = new LinkedNode<>('0');
        AvlNode<String> head = (AvlNode<String>) parent.addChild('a', AvlNode::new, convertor);

        Assert.assertEquals("[{\"height\":0, \"c\":\"a\"}]", Arrays.toString(parent.table));
        Assert.assertEquals(1, parent.size);

        head.insert(parent, 0, 'b', convertor);

        Assert.assertEquals(2, parent.size);
        Assert.assertEquals("[{\"height\":1, \"c\":\"a\", \"right\":{\"height\":0, \"c\":\"b\"}}]", Arrays.toString(parent.table));

        // 重复插入，parent 的 size 和 table 不变
        head.insert(parent, 0, 'a', convertor);

        Assert.assertEquals(2, parent.size);
        Assert.assertEquals("[{\"height\":1, \"c\":\"a\", \"right\":{\"height\":0, \"c\":\"b\"}}]", Arrays.toString(parent.table));
    }

    @Test
    public void insert1() {
        String text = "niohkpfjlm";
        char[] chars = text.toCharArray();
        AvlNode<String> root = null;
        for (char c : chars) {
            if (root == null) {
                root = new AvlNode<>(c);
            } else {
                AvlNode<String> newNode = AvlNode.insert(root, new AvlNode<>(c));
                Assert.assertEquals(c, newNode.c);
                root = AvlNode.balance(root);
            }
        }
    }

    @Test
    public void insertAndBalance() {
        String value = "niohkpfjlm";
        AvlNode<String> root = buildAvlTree(value, value.length());

        Assert.assertEquals(root.c, 'k');
        String expected = "{\"height\":3, \"c\":\"k\", \"left\":{\"height\":2, \"c\":\"i\", \"left\":{\"height\":1, \"c\":\"h\", \"left\":{\"height\":0, \"c\":\"f\"}}, \"right\":{\"height\":0, \"c\":\"j\"}}, \"right\":{\"height\":2, \"c\":\"n\", \"left\":{\"height\":1, \"c\":\"l\", \"right\":{\"height\":0, \"c\":\"m\"}}, \"right\":{\"height\":1, \"c\":\"o\", \"right\":{\"height\":0, \"c\":\"p\"}}}}";
        findError(expected, root.toString());
        Assert.assertEquals("fhijklmnop", convert(root));
    }

    /**
     * 测试添加重复节点
     */
    @Test
    public void insertAndBalanceRepeat() {
        AvlNode<String> root = new AvlNode<>('a');

        root = AvlNode.insertAndBalance(root, new AvlNode<>('b'));

        root = AvlNode.insertAndBalance(root, new AvlNode<>('a'));

        root = AvlNode.insertAndBalance(root, new AvlNode<>('b'));

        findError("{\"height\":1, \"c\":\"a\", \"right\":{\"height\":0, \"c\":\"b\"}}", root.toString());
        Assert.assertEquals("ab", convert(root));
    }

    @Test
    public void delete() {
        String value = "abcdefghijklmnopqrstuvwxyz";
        AvlNode<String> root = buildAvlTree(value, value.length());

        Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"p\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"left\":{\"height\":0, \"c\":\"a\"}, \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"l\", \"left\":{\"height\":1, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"k\"}}, \"right\":{\"height\":1, \"c\":\"n\", \"left\":{\"height\":0, \"c\":\"m\"}, \"right\":{\"height\":0, \"c\":\"o\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());


        Node<String> delete = root.find('a');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bcdefghijklmnopqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"p\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"l\", \"left\":{\"height\":1, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"k\"}}, \"right\":{\"height\":1, \"c\":\"n\", \"left\":{\"height\":0, \"c\":\"m\"}, \"right\":{\"height\":0, \"c\":\"o\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());


        delete = root.find('p');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bcdefghijklmnoqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"l\", \"left\":{\"height\":1, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"k\"}}, \"right\":{\"height\":1, \"c\":\"n\", \"left\":{\"height\":0, \"c\":\"m\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());


        delete = root.find('n');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bcdefghijklmoqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"l\", \"left\":{\"height\":1, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"k\"}}, \"right\":{\"height\":0, \"c\":\"m\"}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());


        delete = root.find('m');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bcdefghijkloqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":1, \"c\":\"l\", \"left\":{\"height\":0, \"c\":\"k\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());

        delete = root.find('c');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bdefghijkloqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":0, \"c\":\"b\"}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":1, \"c\":\"l\", \"left\":{\"height\":0, \"c\":\"k\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());

        delete = root.find('c');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bdefghijkloqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":0, \"c\":\"b\"}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":1, \"c\":\"l\", \"left\":{\"height\":0, \"c\":\"k\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());


        delete = root.find('j');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bdefghikloqrstuvwxyz", convert(root));
        findError("{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":0, \"c\":\"b\"}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":1, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"l\"}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}", root.toString());


        delete = root.find('t');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bdefghikloqrsuvwxyz", convert(root));
        String expected = "{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":0, \"c\":\"b\"}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":1, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"l\"}}}, \"right\":{\"height\":3, \"c\":\"u\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}";
        findError(expected, root.toString());

        delete = root.find('u');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bdefghikloqrsvwxyz", convert(root));
        expected = "{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":0, \"c\":\"b\"}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":1, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"l\"}}}, \"right\":{\"height\":3, \"c\":\"v\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":0, \"c\":\"w\"}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}";
        findError(expected, root.toString());

        delete = root.find('v');
        root = (AvlNode<String>) root.delete(delete, convertor);
        Assert.assertEquals("bdefghikloqrswxyz", convert(root));
        expected = "{\"height\":4, \"c\":\"o\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":0, \"c\":\"b\"}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":1, \"c\":\"k\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"l\"}}}, \"right\":{\"height\":2, \"c\":\"w\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"left\":{\"height\":0, \"c\":\"x\"}, \"right\":{\"height\":0, \"c\":\"z\"}}}}";
        findError(expected, root.toString());
    }

    /**
     * 重复删除相同节点
     */
    @Test
    public void deleteRepeat() {
        String value = "abcdefghijklmnopqrstuvwxyz";
        AvlNode<String> root = buildAvlTree(value, value.length());

        AvlNode<String> delete = new AvlNode<>('a');
        String expected = "{\"height\":4, \"c\":\"p\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"l\", \"left\":{\"height\":1, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"k\"}}, \"right\":{\"height\":1, \"c\":\"n\", \"left\":{\"height\":0, \"c\":\"m\"}, \"right\":{\"height\":0, \"c\":\"o\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":1, \"c\":\"y\", \"right\":{\"height\":0, \"c\":\"z\"}}}}}";

        root = (AvlNode<String>) root.delete(delete, convertor);
        findError(expected, root.toString());

        root = (AvlNode<String>) root.delete(delete, convertor);
        findError(expected, root.toString());

        delete = new AvlNode<>('z');
        root = (AvlNode<String>) root.delete(delete, convertor);
        expected = "{\"height\":4, \"c\":\"p\", \"left\":{\"height\":3, \"c\":\"h\", \"left\":{\"height\":2, \"c\":\"d\", \"left\":{\"height\":1, \"c\":\"b\", \"right\":{\"height\":0, \"c\":\"c\"}}, \"right\":{\"height\":1, \"c\":\"f\", \"left\":{\"height\":0, \"c\":\"e\"}, \"right\":{\"height\":0, \"c\":\"g\"}}}, \"right\":{\"height\":2, \"c\":\"l\", \"left\":{\"height\":1, \"c\":\"j\", \"left\":{\"height\":0, \"c\":\"i\"}, \"right\":{\"height\":0, \"c\":\"k\"}}, \"right\":{\"height\":1, \"c\":\"n\", \"left\":{\"height\":0, \"c\":\"m\"}, \"right\":{\"height\":0, \"c\":\"o\"}}}}, \"right\":{\"height\":3, \"c\":\"t\", \"left\":{\"height\":1, \"c\":\"r\", \"left\":{\"height\":0, \"c\":\"q\"}, \"right\":{\"height\":0, \"c\":\"s\"}}, \"right\":{\"height\":2, \"c\":\"x\", \"left\":{\"height\":1, \"c\":\"v\", \"left\":{\"height\":0, \"c\":\"u\"}, \"right\":{\"height\":0, \"c\":\"w\"}}, \"right\":{\"height\":0, \"c\":\"y\"}}}}";
        findError(expected, root.toString());

        root = (AvlNode<String>) root.delete(delete, convertor);
        findError(expected, root.toString());
    }

    @Test
    public void inorderTraversal() {
        String value = "xyzwvutsrqponmlkjihgfedcba";
        AvlNode<String> root = buildAvlTree(value, value.length());
        List<Node<String>> list = new ArrayList<>(26);
        root.inorderTraversal(list);
        StringBuilder builder = new StringBuilder(26);
        for (Node<String> node : list) {
            builder.append(node.c);
        }
        Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", builder.toString());
    }

    @Test
    public void inorderTraversalValue() {
        AvlNode<String> root = new AvlNode<>('a', "aa");
        root = AvlNode.insertAndBalance(root, new AvlNode<>('b', "bb"));
        root = AvlNode.insertAndBalance(root, new AvlNode<>('d', "dd"));
        root = AvlNode.insertAndBalance(root, new AvlNode<>('c', "cc"));
        List<String> list = new ArrayList<>(26);
        root.inorderTraversalValue(list);
        Assert.assertEquals("[aa, bb, cc, dd]", list.toString());
    }

    public static AvlNode<String> buildAvlTree(String value, int end) {
        char[] chars = value.toCharArray();
        AvlNode<String> root = null;
        for (int i = 0; i < end; i++) {
            if (root == null) {
                root = new AvlNode<>(chars[i]);
            } else {
                root = AvlNode.insertAndBalance(root, new AvlNode<>(chars[i]));
            }
        }
        return root;
    }

    private String convert(AvlNode<String> root) {
        List<Node<String>> nodes = root.findAll();
        char[] array = new char[nodes.size()];
        int i = 0;
        for (Node<String> node : nodes) {
            array[i] = node.c;
            i++;
        }
        return new String(array);
    }

    private void findError(String expected, String actual) {
        char[] expectedChars = expected.toCharArray();
        char[] actualChars = actual.toCharArray();
        int minLen = Math.min(expected.length(), actual.length());

        for (int i = 0; i < minLen; i++) {
            char e = expectedChars[i];
            char a = actualChars[i];
            if (e != a) {
                int offset = Math.max(0, i - 10);
                int count = Math.min(minLen, i + 10) - offset;
                System.out.println(String.copyValueOf(expectedChars, offset, count));
                System.out.println(String.copyValueOf(actualChars, offset, count));
                System.out.println(i);
                break;
            }
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        AvlNode<String> root = new AvlNode<>('a', "aa");
        AvlNode<String> root2 = new AvlNode<>('a', "aa");
        Assert.assertEquals(root, root2);
    }

    @Test
    public void testHashCode() {
        AvlNode<String> root = new AvlNode<>('a', "aa");
        AvlNode<String> root2 = new AvlNode<>('a', "aa");
        Assert.assertEquals(root.hashCode(), root2.hashCode());
    }
}