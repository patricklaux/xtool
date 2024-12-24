package com.igeeksky.xtool.core.lang;

import java.util.Arrays;

/**
 * byte[] 包装类
 * <p>
 * 通过复制副本，保证内部的 byte[] 不被修改。
 *
 * @author Patrick.Lau
 * @since 1.0.13 2024/8/13
 */
public final class ByteArray {

    private static final ByteArray NULL = new ByteArray(null);
    private static final ByteArray EMPTY = new ByteArray(new byte[0]);

    private transient int hash;
    private transient boolean hashIsZero;

    private final byte[] value;

    private ByteArray(byte[] value) {
        this.value = value;
    }

    /**
     * 获取内部字节数组的副本
     *
     * @return {@code byte[]} - 内部字节数组的副本
     */
    public byte[] getValue() {
        return (value == null) ? null : value.clone();
    }

    public int length() {
        return (value == null) ? 0 : value.length;
    }

    /**
     * 创建一个包含指定字节数组的 ByteArray 对象（内部值不可变）。
     * <p>
     * 复制传入的 {@code byte[]} ，将其副本作为内部属性
     *
     * @param value 字节数组
     * @return {@link ByteArray} – 新的 ByteArray 对象
     */
    public static ByteArray wrap(byte[] value) {
        return (value == null) ? NULL : (value.length == 0) ? EMPTY :
                new ByteArray(value.clone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ByteArray byteArray)) return false;

        return Arrays.equals(value, byteArray.value);
    }

    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0 && !hashIsZero) {
            h = Arrays.hashCode(value);
            if (h == 0) {
                hashIsZero = true;
            } else {
                hash = h;
            }
        }
        return h;
    }

}