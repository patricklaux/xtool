package com.igeeksky.xtool.core.lang;

/**
 * 哈希字符串工具类
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
     * 摘要信息 byte[] 转16进制字符串
     *
     * @param toUpperCase 是否使用大写字符
     * @param digest      摘要信息
     * @return HexString
     */
    public static String encodeHexString(boolean toUpperCase, byte[] digest) {
        if (null == digest) {
            return null;
        }
        // 选择大小写
        char[] hexDigits = HEX_DIGITS_LC;
        if (toUpperCase) {
            hexDigits = HEX_DIGITS_UC;
        }
        // 转为16进制字符串
        int length = digest.length;
        char[] chars = new char[length * 2];
        int k = 0;
        for (byte byte0 : digest) {
            chars[k++] = hexDigits[byte0 >>> 0x4 & 0xf];
            chars[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(chars);
    }
}
