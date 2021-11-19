package com.igeeksky.xtool.core.io;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IO工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class IOUtils {

    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;

    private IOUtils() {
    }

    /**
     * 关闭流
     *
     * @param closeable {@link Closeable}
     */
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (java.io.IOException e) {
                throw new IOException(e);
            }
        }
    }

    /**
     * 复制流
     *
     * @param source 源
     * @param target 目标
     * @return 复制的字节数
     */
    public static long copy(InputStream source, OutputStream target) {
        int r;
        long total = 0;
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        try {
            while ((r = source.read(buf)) != EOF) {
                target.write(buf, 0, r);
                total += r;
            }
            return total;
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }
}
