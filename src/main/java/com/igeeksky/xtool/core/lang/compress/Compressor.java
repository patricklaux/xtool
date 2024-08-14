package com.igeeksky.xtool.core.lang.compress;

/**
 * @author Patrick.Lau
 * @since 0.03 2021-06-22
 */
public interface Compressor {

    /**
     * 压缩给定的字节数组
     * <p>
     * 如果输入为 null，抛出 CompressException 异常；如果输入为空数组，直接返回原数组。
     *
     * @param source 待压缩的字节数组（不能为空）
     * @return 压缩后的字节数组。
     * @throws CompressException 如果压缩过程中发生错误
     */
    byte[] compress(byte[] source);

    /**
     * 解压缩给定的字节数组
     * <p>
     * 如果输入为 null，抛出 CompressException 异常；如果输入为空数组，直接返回原数组。
     *
     * @param source 需要解压缩的字节数组（不能为空）
     * @return 解压缩后的字节数组
     * @throws CompressException 如果解压缩过程中出现错误
     */
    byte[] decompress(byte[] source);

}
