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


package com.igeeksky.xtool.core.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class AssertTest {

    @Test
    public void isTrue() {
        Assert.isTrue(true);
    }

    @Test
    public void testIsTrue() {
        Assert.isTrue(true, "error");
    }

    @Test
    public void testIsTrue1() {
        Assert.isTrue(true, () -> "error");
    }

    @Test
    public void testIsTrue2() {
        Assert.isTrue(true, new RuntimeException("error"));
    }

    @Test
    public void testIsTrue3() {
        try {
            Assert.isTrue(false, "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testIsTrue4() {
        try {
            Assert.isTrue(false, () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testIsTrue5() {
        try {
            Assert.isTrue(false, new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void isFalse() {
        Assert.isFalse(false);
    }

    @Test
    public void testIsFalse() {
        Assert.isFalse(false, "error");
    }

    @Test
    public void testIsFalse1() {
        Assert.isFalse(false, () -> "error");
    }

    @Test
    public void testIsFalse2() {
        Assert.isFalse(false, new RuntimeException("error"));
    }

    @Test
    public void testIsFalse3() {
        try {
            Assert.isFalse(true, "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testIsFalse4() {
        try {
            Assert.isFalse(true, () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testIsFalse5() {
        try {
            Assert.isFalse(true, new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void notEmpty() {
        Assert.notEmpty(Collections.singletonList("a"));
    }

    @Test
    public void testNotEmpty() {
        Assert.notEmpty(Collections.singletonList("a"), "error");
    }

    @Test
    public void testNotEmpty1() {
        Assert.notEmpty(Collections.singletonList("a"), () -> "error");
    }

    @Test
    public void testNotEmpty2() {
        Assert.notEmpty(Collections.singletonList("a"), new RuntimeException("error"));
    }

    @Test
    public void testNotEmpty3() {
        Assert.notEmpty(Collections.singletonMap("a", "a"));
    }

    @Test
    public void testNotEmpty4() {
        Assert.notEmpty(Collections.singletonMap("a", "a"), "error");
    }

    @Test
    public void testNotEmpty5() {
        Assert.notEmpty(Collections.singletonMap("a", "a"), () -> "error");
    }

    @Test
    public void testNotEmpty6() {
        Assert.notEmpty(Collections.singletonMap("a", "a"), new RuntimeException("error"));
    }

    @Test
    public void testNotEmpty7() {
        Assert.notEmpty(new String[]{"a"});
    }

    @Test
    public void testNotEmpty8() {
        Assert.notEmpty(new String[]{"a"}, "error");
    }

    @Test
    public void testNotEmpty9() {
        Assert.notEmpty(new String[]{"a"}, () -> "error");
    }

    @Test
    public void testNotEmpty10() {
        Assert.notEmpty(new String[]{"a"}, new RuntimeException("error"));
    }

    @Test
    public void testNotEmpty11() {
        try {
            Assert.notEmpty(new String[0], "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty12() {
        try {
            Assert.notEmpty(new String[0], () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty13() {
        try {
            Assert.notEmpty(new String[0], new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty14() {
        try {
            Assert.notEmpty(Collections.emptyList(), "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty141() {
        try {
            Assert.notEmpty(Collections.emptyList());
        } catch (Exception e) {
            Assertions.assertEquals("[Assertion failed] - this list must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty15() {
        try {
            Assert.notEmpty(Collections.emptyList(), () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty16() {
        try {
            Assert.notEmpty(Collections.emptyList(), new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty17() {
        try {
            Assert.notEmpty(Collections.emptyMap(), "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty171() {
        try {
            Assert.notEmpty(Collections.emptyMap());
        } catch (Exception e) {
            Assertions.assertEquals("[Assertion failed] - this map must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty18() {
        try {
            Assert.notEmpty(Collections.emptyMap(), () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotEmpty19() {
        try {
            Assert.notEmpty(Collections.emptyMap(), new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void hasText() {
        Assert.hasText("a");
    }

    @Test
    public void testHasText() {
        Assert.hasText("a", "error");
    }

    @Test
    public void testHasText1() {
        Assert.hasText("a", () -> "error");
    }

    @Test
    public void testHasText2() {
        Assert.hasText("a", new RuntimeException("error"));
    }

    @Test
    public void testHasText3() {
        try {
            Assert.hasText("  ", "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasText4() {
        try {
            Assert.hasText("  ", () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasText5() {
        try {
            Assert.hasText("  ", new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void hasLength() {
        Assert.hasLength("a");
    }

    @Test
    public void testHasLength() {
        Assert.hasLength("a", "error");
    }

    @Test
    public void testHasLength1() {
        Assert.hasLength("a", () -> "error");
    }

    @Test
    public void testHasLength2() {
        Assert.hasLength("a", new RuntimeException("error"));
    }

    @Test
    public void testHasLength3() {
        try {
            Assert.hasLength("", "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasLength4() {
        try {
            Assert.hasLength("", () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasLength5() {
        try {
            Assert.hasLength("", new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasLength6() {
        char[] chars = new char[0];
        try {
            Assert.hasLength(chars, new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasLength7() {
        char[] chars = new char[0];
        try {
            Assert.hasLength(chars, () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasLength8() {
        char[] chars = new char[0];
        try {
            Assert.hasLength(chars, "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testHasLength9() {
        char[] chars = new char[0];
        try {
            Assert.hasLength(chars);
        } catch (Exception e) {
            Assertions.assertEquals("array dimensions must be greater than 0.", e.getMessage());
        }
    }

    @Test
    public void notNull() {
        Assert.notNull(notNullObject());
    }

    @Test
    public void testNotNull() {
        Assert.notNull(notNullObject(), "error");
    }

    @Test
    public void testNotNull1() {
        Assert.notNull(notNullObject(), () -> "error");
    }

    @Test
    public void testNotNull2() {
        Assert.notNull(notNullObject(), new RuntimeException("error"));
    }

    @Test
    public void testNotNull3() {
        try {
            Assert.notNull(null, "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotNull4() {
        try {
            Assert.notNull(null, () -> "error");
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotNull5() {
        try {
            Assert.notNull(null, new RuntimeException("error"));
        } catch (Exception e) {
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void testNotNull6() {
        try {
            Assert.notNull(null);
        } catch (Exception e) {
            Assertions.assertEquals("[Assertion failed] - this object must not be null.", e.getMessage());
        }
    }

    private static String notNullObject() {
        return "a";
    }
}