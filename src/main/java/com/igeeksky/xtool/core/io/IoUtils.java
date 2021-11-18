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

    private IOUtils() {
    }

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (java.io.IOException e) {
                throw new IOException(e);
            }
        }
    }

    public static long copy(InputStream source, OutputStream target) {
        byte[] buf = new byte[4096];
        long total = 0;
        int r;
        try {
            while ((r = source.read(buf)) != -1) {
                target.write(buf, 0, r);
                total += r;
            }
            return total;
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }
}
