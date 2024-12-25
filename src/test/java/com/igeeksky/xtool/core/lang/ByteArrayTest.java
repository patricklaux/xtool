package com.igeeksky.xtool.core.lang;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ByteArrayTest {

    @Test
    public void wrap_NullInput_ReturnsNullConstant() {
        ByteArray result1 = ByteArray.wrap(null);
        ByteArray result2 = ByteArray.wrap(null);
        assertSame(result1, result2);
    }

    @Test
    public void wrap_EmptyArray_ReturnsEmptyConstant() {
        ByteArray result1 = ByteArray.wrap(new byte[0]);
        ByteArray result2 = ByteArray.wrap(new byte[0]);
        assertSame(result1, result2);
    }

    @Test
    public void wrap_NonEmptyArray_ReturnsNewByteArray() {
        byte[] input = new byte[]{1, 2, 3};
        ByteArray result = ByteArray.wrap(input);
        assertArrayEquals(input, result.getValue());
        assertEquals(Arrays.hashCode(input), result.hashCode());

        input[0] = 4;
        // 外部修改原始数组，相当于直接修改了 ByteArray 的内部对象
        assertArrayEquals(input, result.getValue());

        // 外部修改原始数组，ByteArray 的hashCode 不会更新
        assertNotEquals(Arrays.hashCode(input), result.hashCode());
    }
}
