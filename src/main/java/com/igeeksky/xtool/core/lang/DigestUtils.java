package com.igeeksky.xtool.core.lang;

import java.security.MessageDigest;

/**
 * 摘要算法工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1 2017-01-12
 */
public class DigestUtils {

    private DigestUtils() {
    }

    /**
     * 使用 MD5 摘要算法生成 HexString(默认大写)
     *
     * @param plainText 明文的 byte[]
     * @return 16进制字符串
     */
    public static String md5(byte[] plainText) {
        return md5(plainText, false);
    }

    /**
     * 使用 MD5 摘要算法生成 HexString
     *
     * @param plainText   明文的 byte[]
     * @param toUpperCase 是否使用小写字符
     * @return 16进制字符串
     */
    public static String md5(byte[] plainText, boolean toUpperCase) {
        return digest(Algorithm.MD5, plainText, toUpperCase);
    }

    /**
     * 使用 MD5 摘要算法生成 HexString(默认大写)
     *
     * @param plainText 明文字符串
     * @return 16进制字符串
     */
    public static String md5(String plainText) {
        return md5(plainText.getBytes());
    }

    /**
     * 使用 SHA-1 摘要算法生成 HexString(默认大写)
     *
     * @param plainText 明文字符串
     * @return 16进制字符串
     */
    public static String sha1(String plainText) {
        return sha1(plainText.getBytes());
    }

    /**
     * 使用 SHA-1 摘要算法生成 HexString(默认大写)
     *
     * @param plainText 明文字符串
     * @return 16进制字符串
     */
    public static String sha1(byte[] plainText) {
        return digest(Algorithm.SHA_1, plainText, false);
    }

    /**
     * 使用 SHA-1 摘要算法生成 HexString(默认大写)
     *
     * @param plainText 明文的 byte[]
     * @return 16进制字符串
     */
    public static String sha512(byte[] plainText) {
        return digest(Algorithm.SHA_512, plainText, false);
    }

    /**
     * 使用 SHA-512 摘要算法生成 HexString(默认大写)
     *
     * @param plainText 明文字符串
     * @return 16进制字符串
     */
    public static String sha512(String plainText) {
        return sha512(plainText.getBytes());
    }

    /**
     * 字符串根据摘要算法转HexString(默认大写)
     *
     * @param algorithm 选择信息摘要算法
     * @param plainText 明文字符串
     * @return 16进制字符串
     */
    public static String digest(Algorithm algorithm, String plainText) {
        return digest(algorithm, plainText.getBytes(), false);
    }

    /**
     * 字符串根据摘要算法转HexString
     *
     * @param algorithm   选择信息摘要算法
     * @param toUpperCase 16进制的字符串是否转换成小写
     * @param plainText   明文字符串
     * @return 16进制字符串
     */
    public static String digest(Algorithm algorithm, String plainText, boolean toUpperCase) {
        return digest(algorithm, plainText.getBytes(), toUpperCase);
    }

    /**
     * byte[]根据摘要算法转HexString
     *
     * @param algorithm   选择信息摘要算法
     * @param toUpperCase 16进制的字符串是否转换成小写
     * @param data        待处理byte数组
     * @return 16进制字符串
     */
    public static String digest(Algorithm algorithm, byte[] data, boolean toUpperCase) {
        try {
            //获取算法实例
            MessageDigest instance = MessageDigest.getInstance(algorithm.getName());
            instance.update(data);

            //生成信息摘要
            byte[] digest = instance.digest();

            //转换成16进制字符
            return HexUtil.encodeHexString(toUpperCase, digest);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 摘要算法类型
     *
     * @author Patrick.Lau
     * @since 1.0.0 2017-01-12 00:00:22
     */
    public enum Algorithm {

        /**
         * MD5 摘要算法(128 bit)
         */
        MD5(128, "MD5"),

        /**
         * SHA-1 摘要算法(160 bit)
         */
        SHA_1(160, "SHA-1"),

        /**
         * SHA-224 摘要算法(224 bit)
         */
        SHA_224(224, "SHA-224"),

        /**
         * SHA-256 摘要算法(256 bit)
         */
        SHA_256(256, "SHA-256"),

        /**
         * SHA-384 摘要算法(384 bit)
         */
        SHA_384(384, "SHA-384"),

        /**
         * SHA-512 摘要算法(512 bit)
         */
        SHA_512(512, "SHA-512");

        private final int bit;
        private final String name;

        Algorithm(int bit, String name) {
            this.bit = bit;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getBit() {
            return bit;
        }
    }
}