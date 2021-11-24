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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class IOExceptionTest {

    @Test
    public void test() {
        try {
            throw new IOException("aa");
        } catch (IOException e) {
            Assert.assertEquals("aa", e.getMessage());
        }
    }

    @Test
    public void test1() {
        try {
            throw new IOException(new java.io.IOException("aa"));
        } catch (IOException e) {
            Assert.assertEquals("java.io.IOException: aa", e.getMessage());
        }
    }

    @Test
    public void test2() {
        try {
            throw new IOException("io error", new java.io.IOException("aa"));
        } catch (IOException e) {
            Assert.assertEquals("io error", e.getMessage());
        }
    }
}