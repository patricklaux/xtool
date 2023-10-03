/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
     * <p>
     * 如果出现 {@link java.io.IOException}，转换为{@link IOException} 再抛出
     *
     * @param closeables {@link Closeable}
     */
    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (java.io.IOException e) {
                    throw new IOException(e);
                }
            }
        }
    }

    /**
     * 关闭流（忽略异常）
     *
     * @param closeables {@link AutoCloseable}
     * @since 1.0.7
     */
    public static void closeQuietly(AutoCloseable... closeables) {
        if (closeables == null) {
            return;
        }
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    /**
     * 复制流
     * <p>
     * 如果出现 {@link java.io.IOException}，转换为{@link IOException} 再抛出
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
