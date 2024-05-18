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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.*;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-19
 */
public class IOUtilsTest {

    @Test
    public void close() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        InputStream in = new ByteArrayInputStream(bytes);
        IOUtils.close(in);
    }

    @Test
    public void testClose() {
        IOUtils.close((Closeable) null);
    }

    @Test
    public void testClose2() {
        try {
            InputStream errStream = new InputStream() {
                @Override
                public int read() {
                    return 0;
                }

                @Override
                public void close() throws IOException {
                    throw new IOException("test-close-IOException");
                }
            };
            IOUtils.close(null, errStream);
        } catch (Exception e) {
            Assertions.assertInstanceOf(com.igeeksky.xtool.core.io.IOException.class, e);
        }
    }

    @Test
    public void testCloseQuietly() {
        IOUtils.closeQuietly(null, new ByteArrayInputStream(new byte[0]));
    }

    @Test
    public void testCloseQuietly2() {
        IOUtils.closeQuietly(new InputStream() {
            @Override
            public int read() {
                return 0;
            }

            @Override
            public void close() throws IOException {
                throw new IOException();
            }
        });
    }

    @Test
    public void copy() {
        byte[] expected = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        InputStream in = new ByteArrayInputStream(expected);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        long num = IOUtils.copy(in, out);
        Assertions.assertEquals(8, num);

        byte[] actual = out.toByteArray();
        int maxLen = Math.max(expected.length, actual.length);
        for (int i = 0; i < maxLen; i++) {
            Assertions.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testCopy() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(new InputStream() {
                @Override
                public int read() throws IOException {
                    throw new IOException();
                }
            }, out);
        } catch (Exception e) {
            Assertions.assertInstanceOf(com.igeeksky.xtool.core.io.IOException.class, e);
        }
    }
}