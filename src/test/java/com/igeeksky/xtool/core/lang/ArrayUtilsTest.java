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


package com.igeeksky.xtool.core.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class ArrayUtilsTest {

    @Test
    public void isEmpty() {
        Assertions.assertTrue(ArrayUtils.isEmpty(nullBytes()));
        Assertions.assertTrue(ArrayUtils.isEmpty(emptyBytes()));
        Assertions.assertFalse(ArrayUtils.isEmpty(singletonBytes()));
    }

    @Test
    public void isNotEmpty() {
        Assertions.assertFalse(ArrayUtils.isNotEmpty(nullBytes()));
        Assertions.assertFalse(ArrayUtils.isNotEmpty(emptyBytes()));
        Assertions.assertTrue(ArrayUtils.isNotEmpty(singletonBytes()));
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(ArrayUtils.isEmpty(nullArray()));
        Assertions.assertTrue(ArrayUtils.isEmpty(emptyArray()));
        Assertions.assertFalse(ArrayUtils.isEmpty(singletonArray()));
    }

    @Test
    public void testIsNotEmpty() {
        Assertions.assertFalse(ArrayUtils.isNotEmpty(nullArray()));
        Assertions.assertFalse(ArrayUtils.isNotEmpty(emptyArray()));
        Assertions.assertTrue(ArrayUtils.isNotEmpty(singletonArray()));
    }

    @Test
    public void getFirst() {
        String[] array = new String[]{"a", "b", "c", "d"};
        Assertions.assertEquals("a", ArrayUtils.getFirst(array));

        Byte expected = 1;
        byte[] bytes = new byte[]{1, 2, 3, 4};
        Assertions.assertEquals(expected, ArrayUtils.getFirst(bytes));
    }

    @Test
    public void getLast() {
        String[] array = new String[]{"a", "b", "c", "d"};
        Assertions.assertEquals("d", ArrayUtils.getLast(array));

        Byte expected = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};
        Assertions.assertEquals(expected, ArrayUtils.getLast(bytes));
    }

    @Test
    public void concat() {
        String[] array1 = new String[]{"a", "b", "c", "d"};
        String[] array2 = new String[]{"e", "f", "g", "h"};
        Assertions.assertEquals("[a, b, c, d, e, f, g, h]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }

    @Test
    public void testConcat() {
        byte[] array1 = new byte[]{1, 2, 3, 4};
        byte[] array2 = new byte[]{5, 6, 7, 8};
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }

    private byte[] nullBytes() {
        return null;
    }

    private byte[] emptyBytes() {
        return new byte[0];
    }

    private byte[] singletonBytes() {
        return new byte[]{1};
    }

    private String[] nullArray() {
        return null;
    }

    private String[] emptyArray() {
        return new String[0];
    }

    private String[] singletonArray() {
        return new String[]{"1"};
    }

    @Test
    public void isEmpty_ByteArray_Null_ReturnsTrue() {
        Assertions.assertTrue(ArrayUtils.isEmpty(nullBytes()));
    }

    @Test
    public void isEmpty_ByteArray_Empty_ReturnsTrue() {
        Assertions.assertTrue(ArrayUtils.isEmpty(emptyBytes()));
    }

    @Test
    public void isEmpty_ByteArray_NonEmpty_ReturnsFalse() {
        Assertions.assertFalse(ArrayUtils.isEmpty(singletonBytes()));
    }

    @Test
    public void isNotEmpty_ByteArray_Null_ReturnsFalse() {
        Assertions.assertFalse(ArrayUtils.isNotEmpty(nullBytes()));
    }

    @Test
    public void isNotEmpty_ByteArray_Empty_ReturnsFalse() {
        Assertions.assertFalse(ArrayUtils.isNotEmpty(emptyBytes()));
    }

    @Test
    public void isNotEmpty_ByteArray_NonEmpty_ReturnsTrue() {
        Assertions.assertTrue(ArrayUtils.isNotEmpty(singletonBytes()));
    }

    @Test
    public void isEmpty_ObjectArray_Null_ReturnsTrue() {
        Assertions.assertTrue(ArrayUtils.isEmpty(nullArray()));
    }

    @Test
    public void isEmpty_ObjectArray_Empty_ReturnsTrue() {
        Assertions.assertTrue(ArrayUtils.isEmpty(emptyArray()));
    }

    @Test
    public void isEmpty_ObjectArray_NonEmpty_ReturnsFalse() {
        Assertions.assertFalse(ArrayUtils.isEmpty(singletonArray()));
    }

    @Test
    public void isNotEmpty_ObjectArray_Null_ReturnsFalse() {
        Assertions.assertFalse(ArrayUtils.isNotEmpty(nullArray()));
    }

    @Test
    public void isNotEmpty_ObjectArray_Empty_ReturnsFalse() {
        Assertions.assertFalse(ArrayUtils.isNotEmpty(emptyArray()));
    }

    @Test
    public void isNotEmpty_ObjectArray_NonEmpty_ReturnsTrue() {
        Assertions.assertTrue(ArrayUtils.isNotEmpty(singletonArray()));
    }

    @Test
    public void getFirst_ObjectArray_NonEmpty_ReturnsFirstElement() {
        String[] array = new String[]{"a", "b", "c", "d"};
        Assertions.assertEquals("a", ArrayUtils.getFirst(array));
    }

    @Test
    public void getFirst_ByteArray_NonEmpty_ReturnsFirstElement() {
        Byte expected = 1;
        byte[] bytes = new byte[]{1, 2, 3, 4};
        Assertions.assertEquals(expected, ArrayUtils.getFirst(bytes));
    }

    @Test
    public void getLast_ObjectArray_NonEmpty_ReturnsLastElement() {
        String[] array = new String[]{"a", "b", "c", "d"};
        Assertions.assertEquals("d", ArrayUtils.getLast(array));
    }

    @Test
    public void getLast_ByteArray_NonEmpty_ReturnsLastElement() {
        Byte expected = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};
        Assertions.assertEquals(expected, ArrayUtils.getLast(bytes));
    }

    @Test
    public void concat_ObjectArrays_ConcatenatesArrays() {
        String[] array1 = new String[]{"a", "b", "c", "d"};
        String[] array2 = new String[]{"e", "f", "g", "h"};
        Assertions.assertEquals("[a, b, c, d, e, f, g, h]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }

    @Test
    public void concat_ByteArrays_ConcatenatesArrays() {
        byte[] array1 = new byte[]{1, 2, 3, 4};
        byte[] array2 = new byte[]{5, 6, 7, 8};
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }

    @Test
    public void concat_CharArrays_ConcatenatesArrays() {
        char[] array1 = new char[]{'a', 'b', 'c', 'd'};
        char[] array2 = new char[]{'e', 'f', 'g', 'h'};
        Assertions.assertEquals("[a, b, c, d, e, f, g, h]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }
}