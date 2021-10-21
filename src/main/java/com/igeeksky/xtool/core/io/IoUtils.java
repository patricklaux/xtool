package com.igeeksky.xtool.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public abstract class IoUtils {

    private IoUtils() {
    }

    public static long copy(InputStream source, OutputStream target) throws IOException {
        Objects.requireNonNull(source, "source Stream must not be null");
        Objects.requireNonNull(target, "target Stream must not be null");
        byte[] buf = new byte[4096];
        long total = 0;
        int r;
        while ((r = source.read(buf)) != -1) {
            target.write(buf, 0, r);
            total += r;
        }
        return total;
    }

}
