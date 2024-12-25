package com.igeeksky.xtool.core.lang;

import java.util.Arrays;
import java.util.Map;

/**
 * 可变字节数组
 * <p>
 * <b>注意：</b><p>
 * (1). 由于直接使用外部传入的 {@code byte[]} 作为内部值，
 * 又由于获取值时也直接将内部的 {@code byte[]} 返回，
 * 因此，外部改变 传入的或获取的 {@code byte[]}，都会导致{@link ByteArray} 的内部值改变。
 * <p>
 * (2). ByteArray 的 hashcode 仅在第一次调用 {@link ByteArray#hashCode()} 方法时计算，
 * 再次调用时将直接返回第一次的计算结果。<br>
 * 因此，使用者需特别注意，如果依赖于 {@link ByteArray#hashCode()} 方法的计算结果，
 * 譬如使用 {@link ByteArray} 作为 {@link Map} 的 key 时，
 * 使用者需保证不改变 传入的或获取的 {@code byte[]}。
 * <p>
 * 如果使用者不确定是否会改变 传入的或获取的 {@code byte[]}，请使用 {@link ImmutableByteArray}，
 * 该类使用复制副本的方式保证内部值不可改变，代价是会损失一些性能。
 *
 * @author Patrick.Lau
 * @since 1.0.13 2024/8/13
 */
public class ByteArray {

    private static final ByteArray NULL = new ByteArray(null);
    private static final ByteArray EMPTY = new ByteArray(new byte[0]);

    private transient int hash;
    private transient boolean hashIsZero;

    private final byte[] value;

    private ByteArray(byte[] value) {
        this.value = value;
    }

    /**
     * 获取值
     *
     * @return {@code byte[]} - 内部的字节数组
     */
    public byte[] getValue() {
        return (value == null) ? null : value.clone();
    }

    public int length() {
        return (value == null) ? 0 : value.length;
    }

    /**
     * 创建一个包含指定字节数组的 ByteArray 对象（内部值可变）。
     * <p>
     * 传入的 {@code byte[]} 直接作为内部属性
     *
     * @param value 字节数组
     * @return {@link ByteArray} – 新的 ByteArray 对象
     */
    public static ByteArray wrap(byte[] value) {
        return (value == null) ? NULL : (value.length == 0) ? EMPTY :
                new ByteArray(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ByteArray that)) return false;

        return Arrays.equals(this.value, that.value);
    }

    /**
     * 获取 ByteArray 的哈希值
     * <p>
     * ByteArray 的 hashcode 仅在第一次调用 {@code ByteArray.hashCode()} 方法时计算，
     * 再次调用时将直接返回第一次的计算结果。<br>
     * 因此，使用者需特别注意，如果依赖于 {@code ByteArray.hashCode()} 方法的计算结果，
     * 譬如使用 {@link ByteArray} 作为 {@link Map} 的 key 时，
     * 使用者需保证不改变 传入的或获取的 {@code byte[]}。
     *
     * @return int - 哈希值
     */
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