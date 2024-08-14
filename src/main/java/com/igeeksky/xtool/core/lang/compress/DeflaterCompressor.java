package com.igeeksky.xtool.core.lang.compress;

import com.igeeksky.xtool.core.io.IOUtils;
import com.igeeksky.xtool.core.lang.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * deflate 算法
 *
 * @author Patrick.Lau
 * @since 1.0.0 2024/7/8
 */
public class DeflaterCompressor implements Compressor {

    private static final DeflaterCompressor INSTANCE = new DeflaterCompressor();

    private final int level;
    private final boolean nowrap;

    public DeflaterCompressor() {
        this(Deflater.DEFAULT_COMPRESSION, false);
    }

    /**
     * @param level 压缩级别(-1-9)：默认 -1；不压缩 0 ；最快速度 1；最高压缩 9
     */
    public DeflaterCompressor(int level) {
        this(level, false);
    }

    /**
     * @param level  压缩级别(-1-9)：默认 -1；不压缩 0 ；最快速度 1；最高压缩 9
     * @param nowrap 默认 false；如果为 true，则无 ZLIB header 和 checksum 字段，以便支持 GZIP 和 PKZIP 压缩格式。
     */
    public DeflaterCompressor(int level, boolean nowrap) {
        Assert.isTrue(level >= -1 && level <= 9, "level must level >= -1 && level <= 9");
        this.level = level;
        this.nowrap = nowrap;
    }

    public static DeflaterCompressor getInstance() {
        return INSTANCE;
    }

    @Override
    public byte[] compress(byte[] source) {
        if (null == source) {
            throw new CompressException("compress: byte[] source must not be null");
        }
        if (source.length == 0) {
            return source;
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length / 2)) {
            try (DeflaterOutputStream gos = new DeflaterOutputStream(bos, new Deflater(level, nowrap));
                 ByteArrayInputStream bis = new ByteArrayInputStream(source)) {
                IOUtils.copy(bis, gos);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new CompressException(String.format("Can't compress: %s", e.getMessage()), e);
        }
    }

    @Override
    public byte[] decompress(byte[] source) {
        if (null == source) {
            throw new CompressException("decompress: byte[] source must not be null");
        }
        if (source.length == 0) {
            return source;
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length * 2)) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(source);
                 InflaterInputStream iis = new InflaterInputStream(bis, new Inflater(nowrap))) {
                IOUtils.copy(iis, bos);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new CompressException(String.format("Can't decompress: %s", e.getMessage()), e);
        }
    }

}