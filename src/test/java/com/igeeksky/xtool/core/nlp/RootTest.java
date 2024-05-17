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


package com.igeeksky.xtool.core.nlp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-20
 */
public class RootTest {

    @Test
    public void iterator() {
        Root<String> root = new Root<>('0');
        root.addChild('a', new LinkedNodeCreator<>(), null);

        Iterator<Node<String>> iterator = root.iterator();
        boolean hasNext = iterator.hasNext();
        Assertions.assertTrue(hasNext);
        iterator.next();
        hasNext = iterator.hasNext();
        Assertions.assertFalse(hasNext);
        Assertions.assertNull(iterator.next());
    }
}