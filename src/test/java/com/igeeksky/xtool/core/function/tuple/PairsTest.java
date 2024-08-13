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


package com.igeeksky.xtool.core.function.tuple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class PairsTest {

    @Test
    public void of() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assertions.assertEquals("key", pair.key());
        Assertions.assertEquals("value", pair.value());
    }

    @Test
    public void emptyPair() {
        Pair<String, String> pair = Pairs.emptyPair();
        Assertions.assertNull(pair.key());
        Assertions.assertNull(pair.value());
    }
}