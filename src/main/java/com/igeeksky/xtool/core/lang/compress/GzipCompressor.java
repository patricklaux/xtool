package com.igeeksky.xtool.core.lang.compress;

import com.igeeksky.xtool.core.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * gzip 算法 <p>
 *
 * <b>注意</b>：level 和 nowrap 配置无效，无法调整压缩级别
 * <p>
 * 如需调整压缩级别，请用 {@link DeflaterCompressor}
 *
 * @author Patrick.Lau
 * @since 0.0.3 2021-06-22
 */
public class GzipCompressor implements Compressor {

    private static final GzipCompressor INSTANCE = new GzipCompressor();

    public static GzipCompressor getInstance() {
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
            try (GZIPOutputStream gos = new GZIPOutputStream(bos);
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
                 GZIPInputStream gis = new GZIPInputStream(bis)) {
                IOUtils.copy(gis, bos);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new CompressException(String.format("Can't decompress: %s", e.getMessage()), e);
        }
    }

}