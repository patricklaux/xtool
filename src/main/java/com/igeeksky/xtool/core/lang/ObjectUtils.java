package com.igeeksky.xtool.core.lang;

import java.util.Arrays;

/**
 * Object 工具类
 *
 * @author Patrick.Lau
 * @since 1.0.0
 */
public final class ObjectUtils {

    /**
     * 获取对象的哈希值
     *
     * @param obj 对象
     * @return 哈希值
     */
    public static int deepHashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj.getClass().isArray()) {
            return arrayHashCode(obj);
        }
        return obj.hashCode();
    }

    private static int arrayHashCode(Object o) {
        if (o instanceof Object[] array) {
            return Arrays.deepHashCode(array);
        }
        if (o instanceof byte[] array) {
            return Arrays.hashCode(array);
        }
        if (o instanceof int[] array) {
            return Arrays.hashCode(array);
        }
        if (o instanceof long[] array) {
            return Arrays.hashCode(array);
        }
        if (o instanceof char[] array) {
            return Arrays.hashCode(array);
        }
        if (o instanceof short[] array) {
            return Arrays.hashCode(array);
        }
        if (o instanceof boolean[] array) {
            return Arrays.hashCode(array);
        }
        if (o instanceof double[] array) {
            return Arrays.hashCode(array);
        }
        return Arrays.hashCode((float[]) o);
    }

    public static boolean deepEquals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        if (o1.getClass().isArray() && o2.getClass().isArray()) {
            return arrayDeepEquals(o1, o2);
        }
        return false;
    }

    private static boolean arrayDeepEquals(Object o1, Object o2) {
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.deepEquals((Object[]) o1, (Object[]) o2);
        }
        if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (o1 instanceof short[] && o2 instanceof short[]) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        return false;
    }

}
