package com.igeeksky.xtool.core.lang;

import java.util.Arrays;

/**
 * 不可变字节数组
 * <p>
 * 使用复制副本的方式保证内部值不可改变，代价是会损失一些性能。
 *
 * @author Patrick.Lau
 * @since 1.0.22 2024/12/25
 */
public class ImmutableByteArray {

    private static final ImmutableByteArray NULL = new ImmutableByteArray(null);
    private static final ImmutableByteArray EMPTY = new ImmutableByteArray(new byte[0]);

    private transient int hash;
    private transient boolean hashIsZero;

    private final byte[] value;

    /**
     * 私有构造函数。
     */
    private ImmutableByteArray(byte[] value) {
        this.value = value;
    }

    /**
     * 获取内部字节数组的副本
     *
     * @return {@code byte[]} - 内部字节数组的副本
     */
    public byte[] getValue() {
        return (value == null) ? null : (value.length == 0) ? value : value.clone();
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
    public static ImmutableByteArray wrap(byte[] value) {
        return (value == null) ? NULL : (value.length == 0) ? EMPTY :
                new ImmutableByteArray(value.clone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutableByteArray that)) return false;

        return Arrays.equals(this.value, that.value);
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
