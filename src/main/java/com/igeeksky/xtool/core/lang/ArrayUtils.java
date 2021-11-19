package com.igeeksky.xtool.core.lang;

import java.util.Arrays;

/**
 * 数组工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-09-05
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * 判断数组是否为空或不含任何元素
     *
     * @param bytes 字节数组
     * @return 如果数组为空或不含任何元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isEmpty(byte[] bytes) {
        return (null == bytes || bytes.length == 0);
    }

    /**
     * 判断数组是否不为空且至少含一个元素
     *
     * @param bytes 字节数组
     * @return 如果数组不为空且至少含一个元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isNotEmpty(byte[] bytes) {
        return !isEmpty(bytes);
    }

    /**
     * 判断数组是否为空或不含任何元素
     *
     * @param array 对象数组
     * @param <T>   对象类型
     * @return 如果数组为空或不含任何元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static <T> boolean isEmpty(T[] array) {
        return (null == array || array.length == 0);
    }

    /**
     * 判断数组是否不为空且至少含一个元素
     *
     * @param array 对象数组
     * @param <T>   对象类型
     * @return 如果数组不为空且至少含一个元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    /**
     * 获取第一个元素
     *
     * @param array 对象数组
     * @param <T>   对象类型
     * @return 第一个元素
     */
    public static <T> T getFirst(T[] array) {
        return isEmpty(array) ? null : array[0];
    }

    /**
     * 获取最后一个元素
     *
     * @param array 对象数组
     * @param <T>   对象类型
     * @return 最后一个元素
     */
    public static <T> T getLast(T[] array) {
        return isEmpty(array) ? null : array[array.length - 1];
    }

    /**
     * 拼接多个对象数组
     *
     * @param arrays 多个对象数组
     * @param <T>    值类型
     * @return 对象数组（包含所有数组的所有元素）
     */
    @SafeVarargs
    public static <T> T[] concat(T[]... arrays) {
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

    /**
     * 拼接多个字节数组
     *
     * @param arrays 多个字节数组
     * @return 字节数组（包含所有数组的所有元素）
     */
    public static byte[] concat(byte[]... arrays) {
        int total = 0;
        for (byte[] array : arrays) {
            total += array.length;
        }

        byte[] first = arrays[0];
        byte[] dest = Arrays.copyOf(first, total);

        int offset = first.length;

        for (int i = 1; i < arrays.length; i++) {
            byte[] src = arrays[i];
            System.arraycopy(src, 0, dest, offset, src.length);
            offset += src.length;
        }

        return dest;
    }
}
