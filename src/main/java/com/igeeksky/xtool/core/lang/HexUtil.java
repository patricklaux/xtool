package com.igeeksky.xtool.core.lang;

/**
 * 哈希工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1 2017-01-11
 */
public class HexUtil {

    private static final char[] HEX_DIGITS_UC = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] HEX_DIGITS_LC = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private HexUtil() {
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param lowerCase 是否返回小写的摘要信息
     * @param data      摘要信息
     * @return 16进制字符串
     */
    public static String encodeHexString(byte[] data, boolean lowerCase) {
        char[] chars = encodeHex(data, lowerCase);
        return (chars == null) ? null : new String(chars);
    }

    /**
     * 字节数组转16进制字符数组
     *
     * @param lowerCase 是否返回小写的摘要信息
     * @param data      摘要信息
     * @return 16进制字符数组
     */
    public static char[] encodeHex(byte[] data, boolean lowerCase) {
        if (null == data) {
            return null;
        }
        // 选择大小写
        char[] hexDigits = HEX_DIGITS_UC;
        if (lowerCase) {
            hexDigits = HEX_DIGITS_LC;
        }
        // 转为16进制字符串
        int length = data.length;
        char[] chars = new char[length * 2];
        int k = 0;
        for (byte byte0 : data) {
            chars[k++] = hexDigits[byte0 >>> 0x4 & 0xf];
            chars[k++] = hexDigits[byte0 & 0xf];
        }
        return chars;
    }
}
