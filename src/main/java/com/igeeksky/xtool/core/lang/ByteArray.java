package com.igeeksky.xtool.core.lang;

import java.util.Arrays;

/**
 * @author Patrick.Lau
 * @since 1.0.13 2024/8/13
 */
public class ByteArray {

    private int hash;
    private boolean hashIsZero;

    private final byte[] value;

    private ByteArray(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }

    public static ByteArray of(byte[] value) {
        return new ByteArray(value);
    }

    public static ByteArray copyOf(byte[] value) {
        return new ByteArray(Arrays.copyOf(value, value.length));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ByteArray byteArray)) return false;

        return Arrays.equals(getValue(), byteArray.getValue());
    }

    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0 && !hashIsZero) {
            h = Arrays.hashCode(getValue());
            if (h == 0) {
                hashIsZero = true;
            } else {
                hash = h;
            }
        }
        return h;
    }

}