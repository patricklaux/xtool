package com.igeeksky.xtool.core.lang.compress;

import com.igeeksky.xtool.core.lang.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-06-23
 */
class GzipCompressorTest {

    @Test
    void compress() {
        int length = 1024 * 1024;
        byte[] data = loadData(length);

        GzipCompressor gzipCompressor = new GzipCompressor();
        byte[] compress = gzipCompressor.compress(data);
        System.out.println("compress-length:" + compress.length);

        byte[] decompress = gzipCompressor.decompress(compress);
        System.out.println("decompress-length:" + decompress.length);
        Assertions.assertEquals(length, decompress.length);
    }

    private byte[] loadData(int length) {
        // 生成小段数据
        int times = 1024;
        byte[] data = new byte[length / times];
        RandomUtils.nextBytes(data);

        // 数据复制 1024 遍，压缩才有效果（如果完全随机生成，压缩难有效果）
        byte[] result = new byte[length];
        for (int i = 0; i < times; i++) {
            System.arraycopy(data, 0, result, i * data.length, data.length);
        }
        return result;
    }

}