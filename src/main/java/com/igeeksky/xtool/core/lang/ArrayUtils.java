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
     * 获取第一个元素
     *
     * @param array byte数组
     * @return 第一个元素
     */
    public static Byte getFirst(byte[] array) {
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
     * 获取最后一个元素
     *
     * @param array 对象数组
     * @return 最后一个元素
     */
    public static Byte getLast(byte[] array) {
        return isEmpty(array) ? null : array[array.length - 1];
    }


    /**
     * 使用指定元素填充数组
     *
     * @param array 待填充的数组
     * @param obj   用于填充数组的元素
     * @return 已填充指定元素的泛型数组（原数组）
     */
    public static <T> T[] fill(T[] array, T obj) {
        // 使用Arrays.fill方法对数组进行填充
        Arrays.fill(array, obj);
        // 返回填充后的数组
        return array;
    }


    /**
     * 根据指定大小和值创建并填充一个泛型数组
     *
     * @param size 数组的大小
     * @param t    需要填充到数组的元素
     * @return 已填充指定元素的泛型数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] fill(int size, T t) {
        T[] array = (T[]) new Object[size];
        Arrays.fill(array, t);
        return array;
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

    /**
     * 拼接多个字符数组
     *
     * @param arrays 多个字节数组
     * @return 字节数组（包含所有数组的所有元素）
     */
    public static char[] concat(char[]... arrays) {
        int total = 0;
        for (char[] array : arrays) {
            total += array.length;
        }

        char[] first = arrays[0];
        char[] dest = Arrays.copyOf(first, total);

        int offset = first.length;

        for (int i = 1; i < arrays.length; i++) {
            char[] src = arrays[i];
            System.arraycopy(src, 0, dest, offset, src.length);
            offset += src.length;
        }

        return dest;
    }
}
