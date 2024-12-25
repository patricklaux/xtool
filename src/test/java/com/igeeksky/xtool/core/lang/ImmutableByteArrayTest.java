package com.igeeksky.xtool.core.lang;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutableByteArrayTest {

    private byte[] emptyArray;
    private byte[] nonEmptyArray;

    @BeforeEach
    public void setUp() {
        emptyArray = new byte[0];
        nonEmptyArray = new byte[]{1, 2, 3};
    }

    @Test
    public void wrap_NullInput_ReturnsNullInstance() {
        ImmutableByteArray result = ImmutableByteArray.wrap(null);
        ImmutableByteArray result2 = ImmutableByteArray.wrap(null);
        assertSame(result, result2);
    }

    @Test
    public void wrap_EmptyArray_ReturnsEmptyInstance() {
        ImmutableByteArray result1 = ImmutableByteArray.wrap(emptyArray);
        ImmutableByteArray result2 = ImmutableByteArray.wrap(emptyArray);
        assertSame(result1, result2);
    }

    @Test
    public void wrap_NonEmptyArray_ReturnsNewInstance() {
        ImmutableByteArray result = ImmutableByteArray.wrap(nonEmptyArray);
        assertNotSame(nonEmptyArray, result.getValue());
        assertArrayEquals(nonEmptyArray, result.getValue());
    }

    @Test
    public void wrap_NonEmptyArray_ModifyingOriginalDoesNotAffectResult() {
        ImmutableByteArray result = ImmutableByteArray.wrap(nonEmptyArray);
        assertArrayEquals(nonEmptyArray, result.getValue());
        assertEquals(Arrays.hashCode(nonEmptyArray), result.hashCode());

        // 外部修改原始数组
        nonEmptyArray[0] = 4;

        // 外部修改原始数组，内部值应保持不变
        assertArrayEquals(new byte[]{1, 2, 3}, result.getValue());

        // 外部修改原始数组，ImmutableByteArray 应保持不变
        assertEquals(Arrays.hashCode(new byte[]{1, 2, 3}), result.hashCode());

        assertNotEquals(Arrays.hashCode(nonEmptyArray), result.hashCode());
    }

    @Test
    public void wrap_NonEmptyArray_ModifyingOriginalDoesNotAffectResult2() {
        ImmutableByteArray result1 = ImmutableByteArray.wrap(nonEmptyArray);

        // 外部修改原始数组
        nonEmptyArray[0] = 4;
        ImmutableByteArray result2 = ImmutableByteArray.wrap(nonEmptyArray);

        // 外部修改原始数组，ImmutableByteArray 应保持不变
        assertNotEquals(result1, result2);
    }

}
