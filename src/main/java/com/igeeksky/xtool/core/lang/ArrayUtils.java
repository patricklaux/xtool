package com.igeeksky.xtool.core.lang;

import java.util.Arrays;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-09-05
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    public static <T> void requireNonEmpty(T[] array, String message) {
        if (isEmpty(array)) {
            throw new NullPointerException(message);
        }
    }

    public static boolean isEmpty(byte[] bytes) {
        return (null == bytes || bytes.length == 0);
    }

    public static boolean isNotEmpty(byte[] bytes) {
        return (null != bytes && bytes.length != 0);
    }

    public static <T> boolean isEmpty(T[] array) {
        return (null == array || array.length == 0);
    }

    public static <T> T getLast(T[] src) {
        return src[src.length - 1];
    }

    public static <T> T[] merge(T[][] arrays) {
        int total = 0;
        for (T[] array : arrays) {
            total += array.length;
        }

        T[] first = arrays[0];
        T[] dest = Arrays.copyOf(first, total);

        int offset = first.length;

        for (int i = 1; i < arrays.length; i++) {
            T[] src = arrays[i];
            System.arraycopy(src, 0, dest, offset, src.length);
            offset += src.length;
        }

        return dest;
    }

    public static byte[] merge(byte[] src1, byte[] src2) {
        if (null == src1 || src1.length == 0) {
            return src2;
        }
        if (null == src2 || src2.length == 0) {
            return src1;
        }

        int src1Len = src1.length;
        int src2Len = src2.length;
        byte[] dest = Arrays.copyOf(src1, src1Len + src2Len);
        System.arraycopy(src2, 0, dest, src1Len, src2Len);
        return dest;
    }
}
