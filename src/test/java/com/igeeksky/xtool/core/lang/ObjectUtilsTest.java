package com.igeeksky.xtool.core.lang;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectUtilsTest {

    @Test
    public void deepHashCode_NullObject_ReturnsZero() {
        assertEquals(0, ObjectUtils.deepHashCode(null));
    }

    @Test
    public void deepHashCode_NonArrayObject_ReturnsHashCode() {
        Object obj = new Object();
        assertEquals(obj.hashCode(), ObjectUtils.deepHashCode(obj));
    }

    @Test
    public void deepHashCode_EmptyArrayObject_ReturnsHashCode() {
        Object[] objArray = new Object[0];
        assertEquals(Arrays.hashCode(objArray), ObjectUtils.deepHashCode(objArray));
    }

    @Test
    public void deepHashcode_ObjectArray_ReturnsDeepHashCode() {
        Object[] objArray = {new Object(), new Object()};
        assertEquals(Arrays.deepHashCode(objArray), ObjectUtils.deepHashCode(objArray));
    }

    @Test
    public void deepHashCode_PrimitiveByteArray_ReturnsHashCode() {
        byte[] byteArray = {1, 2, 3};
        assertEquals(Arrays.hashCode(byteArray), ObjectUtils.deepHashCode(byteArray));
    }

    @Test
    public void deepHashCode_PrimitiveIntArray_ReturnsHashCode() {
        int[] intArray = {1, 2, 3};
        assertEquals(Arrays.hashCode(intArray), ObjectUtils.deepHashCode(intArray));
    }

    @Test
    public void deepHashCode_PrimitiveLongArray_ReturnsHashCode() {
        long[] longArray = {1L, 2L, 3L};
        assertEquals(Arrays.hashCode(longArray), ObjectUtils.deepHashCode(longArray));
    }

    @Test
    public void deepHashCode_PrimitiveCharArray_ReturnsHashCode() {
        char[] charArray = {'a', 'b', 'c'};
        assertEquals(Arrays.hashCode(charArray), ObjectUtils.deepHashCode(charArray));
    }

    @Test
    public void deepHashCode_PrimitiveShortArray_ReturnsHashCode() {
        short[] shortArray = {1, 2, 3};
        assertEquals(Arrays.hashCode(shortArray), ObjectUtils.deepHashCode(shortArray));
    }

    @Test
    public void deepHashCode_PrimitiveBooleanArray_ReturnsHashCode() {
        boolean[] booleanArray = {true, false, true};
        assertEquals(Arrays.hashCode(booleanArray), ObjectUtils.deepHashCode(booleanArray));
    }

    @Test
    public void deepHashCode_PrimitiveDoubleArray_ReturnsHashCode() {
        double[] doubleArray = {1.0, 2.0, 3.0};
        assertEquals(Arrays.hashCode(doubleArray), ObjectUtils.deepHashCode(doubleArray));
    }

    @Test
    public void deepHashCode_PrimitiveFloatArray_ReturnsHashCode() {
        float[] floatArray = {1.0f, 2.0f, 3.0f};
        assertEquals(Arrays.hashCode(floatArray), ObjectUtils.deepHashCode(floatArray));
    }

    @Test
    public void deepEquals_SameReference_ReturnsTrue() {
        Object obj = new Object();
        assertTrue(ObjectUtils.deepEquals(obj, obj));
    }

    @Test
    public void deepEquals_OneNull_ReturnsFalse() {
        Object obj = new Object();
        assertFalse(ObjectUtils.deepEquals(obj, null));
        assertFalse(ObjectUtils.deepEquals(null, obj));
    }

    @Test
    public void deepEquals_BothNull_ReturnsTrue() {
        assertTrue(ObjectUtils.deepEquals(null, null));
    }

    @Test
    public void deepEquals_EqualObj_ReturnsTrue() {
        String str1 = new String("a".getBytes());
        String str2 = new String("a".getBytes());
        assertTrue(ObjectUtils.deepEquals(str1, str2));
    }

    @Test
    public void deepEquals_DifferentObjects_ReturnsFalse() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertFalse(ObjectUtils.deepEquals(obj1, obj2));
    }

    @Test
    public void deepEquals_DifferentObjectArrays_ReturnsFalse() {
        Object[] objArray1 = {new Object(), new Object()};
        Object[] objArray2 = {new Object(), new Object()};
        assertFalse(ObjectUtils.deepEquals(objArray1, objArray2));
    }

    @Test
    public void deepEquals_EqualPrimitiveArrays_ReturnsTrue() {
        int[] intArray1 = {1, 2, 3};
        int[] intArray2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(intArray1, intArray2));
    }

    @Test
    public void deepEquals_DifferentPrimitiveArrays_ReturnsFalse() {
        int[] intArray1 = {1, 2, 3};
        int[] intArray2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(intArray1, intArray2));
    }

    @Test
    public void deepEquals_EqualByteArrays_ReturnsTrue() {
        byte[] array1 = {1, 2, 3};
        byte[] array2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentByteArrays_ReturnsFalse() {
        byte[] array1 = {1, 2, 3};
        byte[] array2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_EqualLongArrays_ReturnsTrue() {
        long[] array1 = {1, 2, 3};
        long[] array2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentLongArrays_ReturnsFalse() {
        long[] array1 = {1, 2, 3};
        long[] array2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_EqualCharArrays_ReturnsTrue() {
        char[] array1 = {1, 2, 3};
        char[] array2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentCharArrays_ReturnsFalse() {
        char[] array1 = {1, 2, 3};
        char[] array2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_EqualShortArrays_ReturnsTrue() {
        short[] array1 = {1, 2, 3};
        short[] array2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentShortArrays_ReturnsFalse() {
        short[] array1 = {1, 2, 3};
        short[] array2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_EqualDoubleArrays_ReturnsTrue() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentDoubleArrays_ReturnsFalse() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_EqualFloatArrays_ReturnsTrue() {
        float[] array1 = {1, 2, 3};
        float[] array2 = {1, 2, 3};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentFloatArrays_ReturnsFalse() {
        float[] array1 = {1, 2, 3};
        float[] array2 = {3, 2, 1};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_EqualBooleanArrays_ReturnsTrue() {
        boolean[] array1 = {true, false, true};
        boolean[] array2 = {true, false, true};
        assertTrue(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentBooleanArrays_ReturnsFalse() {
        boolean[] array1 = {false, true, false};
        boolean[] array2 = {true, false, true};
        assertFalse(ObjectUtils.deepEquals(array1, array2));
    }

    @Test
    public void deepEquals_DifferentArrayTypes_ReturnsFalse() {
        Object[] objArray = {new Object(), new Object()};
        int[] intArray = {1, 2};
        assertFalse(ObjectUtils.deepEquals(objArray, intArray));
    }

    @Test
    public void deepEquals_MixedArrayTypes_ReturnsFalse() {
        Object[] objArray = {new Object(), new Object()};
        Object[] mixedArray = {new Object(), 1};
        assertFalse(ObjectUtils.deepEquals(objArray, mixedArray));
    }
}
