package com.igeeksky.xtool.core.lang;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 摘要算法工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1 2017-01-12
 */
public class DigestUtils {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private DigestUtils() {
    }

    /**
     * 使用 MD5 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息，默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text 字符串
     * @return 摘要信息
     */
    public static String md5(String text) {
        return md5(text, DEFAULT_CHARSET, true);
    }

    /**
     * 使用 MD5 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param text    字符串
     * @param charset 字符编码
     * @return 摘要信息
     */
    public static String md5(String text, Charset charset) {
        return md5(text, charset, true);
    }

    /**
     * 使用 MD5 算法生成摘要信息
     * <p>
     * 默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text      字符串
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String md5(String text, boolean upperCase) {
        return md5(text, DEFAULT_CHARSET, upperCase);
    }

    /**
     * 使用 MD5 算法生成摘要信息
     *
     * @param text      字符串
     * @param charset   字符编码
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String md5(String text, Charset charset, boolean upperCase) {
        return md5(text.getBytes(charset), upperCase);
    }

    /**
     * 使用 SHA-1 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息，默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text 字符串
     * @return 摘要信息
     */
    public static String sha1(String text) {
        return sha1(text, DEFAULT_CHARSET, true);
    }

    /**
     * 使用 SHA-1 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param text    字符串
     * @param charset 字符编码
     * @return 摘要信息
     */
    public static String sha1(String text, Charset charset) {
        return sha1(text, charset, true);
    }

    /**
     * 使用 SHA-1 算法生成摘要信息
     * <p>
     * 默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text      字符串
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha1(String text, boolean upperCase) {
        return sha1(text, DEFAULT_CHARSET, upperCase);
    }

    /**
     * 使用 SHA-1 算法生成摘要信息
     *
     * @param text      字符串
     * @param charset   字符编码
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha1(String text, Charset charset, boolean upperCase) {
        return sha1(text.getBytes(charset), upperCase);
    }

    /**
     * 使用 SHA-224 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息，默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text 字符串
     * @return 摘要信息
     */
    public static String sha224(String text) {
        return sha224(text, DEFAULT_CHARSET, true);
    }

    /**
     * 使用 SHA-224 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param text    字符串
     * @param charset 字符编码
     * @return 摘要信息
     */
    public static String sha224(String text, Charset charset) {
        return sha224(text, charset, true);
    }

    /**
     * 使用 SHA-224 算法生成摘要信息
     * <p>
     * 默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text      字符串
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha224(String text, boolean upperCase) {
        return sha224(text, DEFAULT_CHARSET, upperCase);
    }

    /**
     * 使用 SHA-224 算法生成摘要信息
     *
     * @param text      字符串
     * @param charset   字符编码
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha224(String text, Charset charset, boolean upperCase) {
        return sha224(text.getBytes(charset), upperCase);
    }

    /**
     * 使用 SHA-256 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息，默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text 字符串
     * @return 摘要信息
     */
    public static String sha256(String text) {
        return sha256(text, DEFAULT_CHARSET, true);
    }

    /**
     * 使用 SHA-256 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param text    字符串
     * @param charset 字符编码
     * @return 摘要信息
     */
    public static String sha256(String text, Charset charset) {
        return sha256(text, charset, true);
    }

    /**
     * 使用 SHA-256 算法生成摘要信息
     * <p>
     * 默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text      字符串
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha256(String text, boolean upperCase) {
        return sha256(text, DEFAULT_CHARSET, upperCase);
    }

    /**
     * 使用 SHA-256 算法生成摘要信息
     *
     * @param text      字符串
     * @param charset   字符编码
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha256(String text, Charset charset, boolean upperCase) {
        return sha256(text.getBytes(charset), upperCase);
    }

    /**
     * 使用 SHA-384 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息，默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text 字符串
     * @return 摘要信息
     */
    public static String sha384(String text) {
        return sha384(text, DEFAULT_CHARSET, true);
    }

    /**
     * 使用 SHA-384 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param text    字符串
     * @param charset 字符编码
     * @return 摘要信息
     */
    public static String sha384(String text, Charset charset) {
        return sha384(text, charset, true);
    }

    /**
     * 使用 SHA-384 算法生成摘要信息
     * <p>
     * 默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text      字符串
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha384(String text, boolean upperCase) {
        return sha384(text, DEFAULT_CHARSET, upperCase);
    }

    /**
     * 使用 SHA-384 算法生成摘要信息
     *
     * @param text      字符串
     * @param charset   字符编码
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha384(String text, Charset charset, boolean upperCase) {
        return sha384(text.getBytes(charset), upperCase);
    }

    /**
     * 使用 SHA-512 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息，默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text 字符串
     * @return 摘要信息
     */
    public static String sha512(String text) {
        return sha512(text, DEFAULT_CHARSET, true);
    }

    /**
     * 使用 SHA-512 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param text    字符串
     * @param charset 字符编码
     * @return 摘要信息
     */
    public static String sha512(String text, Charset charset) {
        return sha512(text, charset, true);
    }

    /**
     * 使用 SHA-512 算法生成摘要信息
     * <p>
     * 默认字符编码为 {@link StandardCharsets#UTF_8}
     *
     * @param text      字符串
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha512(String text, boolean upperCase) {
        return sha512(text, DEFAULT_CHARSET, upperCase);
    }

    /**
     * 使用 SHA-512 算法生成摘要信息
     *
     * @param text      字符串
     * @param charset   字符编码
     * @param upperCase 是否返回大写的摘要信息
     * @return 摘要信息
     */
    public static String sha512(String text, Charset charset, boolean upperCase) {
        return sha512(text.getBytes(charset), upperCase);
    }

    /**
     * 使用 MD5 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String md5(byte[] bytes) {
        return md5(bytes, true);
    }

    /**
     * 使用 MD5 算法生成摘要信息
     *
     * @param bytes     字节数组
     * @param upperCase 是否使用大写字符
     * @return 摘要信息
     */
    public static String md5(byte[] bytes, boolean upperCase) {
        return digest(Algorithm.MD5, bytes, upperCase);
    }

    /**
     * 使用 SHA-1 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha1(byte[] bytes) {
        return sha1(bytes, true);
    }

    /**
     * 使用 SHA-1 算法生成摘要信息
     *
     * @param bytes     字节数组
     * @param upperCase 是否使用大写字符
     * @return 摘要信息
     */
    public static String sha1(byte[] bytes, boolean upperCase) {
        return digest(Algorithm.SHA_1, bytes, upperCase);
    }

    /**
     * 使用 SHA-224 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha224(byte[] bytes) {
        return sha224(bytes, true);
    }

    /**
     * 使用 SHA-224 算法生成摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha224(byte[] bytes, boolean upperCase) {
        return digest(Algorithm.SHA_224, bytes, upperCase);
    }

    /**
     * 使用 SHA-256 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha256(byte[] bytes) {
        return sha256(bytes, true);
    }

    /**
     * 使用 SHA-256 算法生成摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha256(byte[] bytes, boolean upperCase) {
        return digest(Algorithm.SHA_256, bytes, upperCase);
    }

    /**
     * 使用 SHA-384 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha384(byte[] bytes) {
        return sha384(bytes, true);
    }

    /**
     * 使用 SHA-384 算法生成摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha384(byte[] bytes, boolean upperCase) {
        return digest(Algorithm.SHA_384, bytes, upperCase);
    }

    /**
     * 使用 SHA-512 算法生成摘要信息
     * <p>
     * 默认返回大写的摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha512(byte[] bytes) {
        return sha512(bytes, true);
    }

    /**
     * 使用 SHA-512 算法生成摘要信息
     *
     * @param bytes 字节数组
     * @return 摘要信息
     */
    public static String sha512(byte[] bytes, boolean upperCase) {
        return digest(Algorithm.SHA_512, bytes, upperCase);
    }

    /**
     * 根据摘要算法生成哈希摘要信息
     *
     * @param algorithm 摘要算法
     * @param bytes     字节数组
     * @param upperCase 是否使用大写字符
     * @return 摘要信息
     */
    private static String digest(Algorithm algorithm, byte[] bytes, boolean upperCase) {
        try {
            //获取算法实例
            MessageDigest instance = MessageDigest.getInstance(algorithm.getName());
            instance.update(bytes);

            //生成信息摘要
            byte[] digest = instance.digest();

            //转换成16进制字符
            return HexUtil.encodeHexString(digest, upperCase);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 摘要算法类型
     *
     * @author Patrick.Lau
     * @since 0.0.4 2017-01-12 00:00:22
     */
    private enum Algorithm {

        /**
         * MD5 摘要算法(128 bit)
         */
        MD5("MD5"),

        /**
         * SHA-1 摘要算法(160 bit)
         */
        SHA_1("SHA-1"),

        /**
         * SHA-224 摘要算法(224 bit)
         */
        SHA_224("SHA-224"),

        /**
         * SHA-256 摘要算法(256 bit)
         */
        SHA_256("SHA-256"),

        /**
         * SHA-384 摘要算法(384 bit)
         */
        SHA_384("SHA-384"),

        /**
         * SHA-512 摘要算法(512 bit)
         */
        SHA_512("SHA-512");

        private final String name;

        Algorithm(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}