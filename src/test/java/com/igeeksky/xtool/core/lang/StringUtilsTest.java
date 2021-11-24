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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class StringUtilsTest {

    @Test
    public void hasText() {
        Assert.assertFalse(StringUtils.hasText(null));
        Assert.assertFalse(StringUtils.hasText(""));
        Assert.assertFalse(StringUtils.hasText(" "));
        Assert.assertTrue(StringUtils.hasText("a"));
        Assert.assertTrue(StringUtils.hasText(" a "));
    }

    @Test
    public void hasLength() {
        Assert.assertFalse(StringUtils.hasLength(nullString()));
        Assert.assertFalse(StringUtils.hasLength(""));
        Assert.assertTrue(StringUtils.hasLength(" "));
        Assert.assertTrue(StringUtils.hasLength("a"));
        Assert.assertTrue(StringUtils.hasLength(" a "));
    }

    private String nullString() {
        return null;
    }

    @Test
    public void trim() {
        Assert.assertNull(StringUtils.trim(nullString()));
        Assert.assertEquals("", StringUtils.trim(""));
        Assert.assertEquals("", StringUtils.trim(" "));
        Assert.assertEquals("a", StringUtils.trim("a"));
        Assert.assertEquals("a", StringUtils.trim("  a  "));
    }

    @Test
    public void trimToNull() {
        Assert.assertNull(StringUtils.trimToNull(nullString()));
        Assert.assertNull(StringUtils.trimToNull(""));
        Assert.assertNull(StringUtils.trimToNull(" "));
        Assert.assertEquals("a", StringUtils.trimToNull("a"));
        Assert.assertEquals("a", StringUtils.trimToNull("  a  "));
    }

    @Test
    public void toUpperCase() {
        // 情形一：字符串为空对象，upperCase == null
        String upperCase = StringUtils.toUpperCase(null);
        Assert.assertNull(upperCase);

        // 情形二：字符串无字符，upperCase == null
        upperCase = StringUtils.toUpperCase("");
        Assert.assertNull(upperCase);

        // 情形三：字符串只有空白字符，upperCase == null
        upperCase = StringUtils.toUpperCase("   ");
        Assert.assertNull(upperCase);

        // 情形四：字符串有非空白字符，upperCase == "AAA"
        upperCase = StringUtils.toUpperCase("aaa");
        Assert.assertEquals("AAA", upperCase);

        // 情形五：字符串有非空白字符，upperCase == "AAA"
        upperCase = StringUtils.toUpperCase(" aaa ");
        Assert.assertEquals("AAA", upperCase);
    }

    @Test
    public void toLowerCase() {
        // 情形一：字符串为空对象，lowerCase == null
        String lowerCase = StringUtils.toLowerCase(null);
        Assert.assertNull(lowerCase);

        // 情形二：字符串无字符，lowerCase == null
        lowerCase = StringUtils.toLowerCase("");
        Assert.assertNull(lowerCase);

        // 情形三：字符串只有空白字符，lowerCase == null
        lowerCase = StringUtils.toLowerCase("   ");
        Assert.assertNull(lowerCase);

        // 情形四：字符串有非空白字符，lowerCase == "aaa"
        lowerCase = StringUtils.toLowerCase("AAA");
        Assert.assertEquals("aaa", lowerCase);

        // 情形五：字符串有非空白字符，lowerCase == "aaa"
        lowerCase = StringUtils.toLowerCase(" AAA ");
        Assert.assertEquals("aaa", lowerCase);
    }

    @Test
    public void unCapitalize() {
        Assert.assertNull(StringUtils.unCapitalize(nullString()));
        Assert.assertEquals("", StringUtils.unCapitalize(""));
        Assert.assertEquals("practice Is Perfect.", StringUtils.unCapitalize("Practice Is Perfect."));
        Assert.assertEquals("practice Is Perfect.", StringUtils.unCapitalize("practice Is Perfect."));
    }

    @Test
    public void testUnCapitalize() {
        char[] expected = "practice Is Perfect.".toCharArray();
        char[] actual = StringUtils.unCapitalize("Practice Is Perfect.".toCharArray());
        int maxLen = expected.length;
        for (int i = 0; i < maxLen; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testUnCapitalize1() {
        char[] expected = "practice Is Perfect.".toCharArray();
        char[] actual = StringUtils.unCapitalize("practice Is Perfect.".toCharArray());
        int maxLen = expected.length;
        for (int i = 0; i < maxLen; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testUnCapitalize2() {
        char[] unCapitalize = StringUtils.unCapitalize("".toCharArray());
        Assert.assertEquals(0, unCapitalize.length);

        unCapitalize = StringUtils.unCapitalize(nullArray());
        Assert.assertNull(unCapitalize);
    }

    private char[] nullArray() {
        return null;
    }

    @Test
    public void capitalize() {
        // 情形一：字符串为空对象，capitalize == null
        String original = null;
        String capitalize = StringUtils.capitalize(original);
        Assert.assertNull(capitalize);

        // 情形二：字符串无字符，capitalize == ""
        capitalize = StringUtils.capitalize("");
        Assert.assertEquals("", capitalize);

        // 情形三：字符串只有空白字符，capitalize == "   "
        capitalize = StringUtils.capitalize("   ");
        Assert.assertEquals("   ", capitalize);

        // 情形四：字符串有非空白字符，首字符为字母且为小写，capitalize == "Aaa"
        capitalize = StringUtils.capitalize("aaa");
        Assert.assertEquals("Aaa", capitalize);

        // 情形五：字符串有非空白字符，首字符为字母且为大写，capitalize == "Aaa"
        capitalize = StringUtils.capitalize("Aaa");
        Assert.assertEquals("Aaa", capitalize);

        // 情形六：字符串有非空白字符，首字符为非字母，capitalize == " aaa "
        capitalize = StringUtils.capitalize(" aaa ");
        Assert.assertEquals(" aaa ", capitalize);
    }

    @Test
    public void testCapitalize() {
        char[] expected = "Practice Is Perfect.".toCharArray();
        char[] actual = StringUtils.capitalize("practice Is Perfect.".toCharArray());
        int maxLen = expected.length;
        for (int i = 0; i < maxLen; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testCapitalize1() {
        char[] expected = "Practice Is Perfect.".toCharArray();
        char[] actual = StringUtils.capitalize("Practice Is Perfect.".toCharArray());
        int maxLen = expected.length;
        for (int i = 0; i < maxLen; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testCapitalize2() {
        char[] capitalize = StringUtils.capitalize("".toCharArray());
        Assert.assertEquals(0, capitalize.length);

        capitalize = StringUtils.capitalize(nullArray());
        Assert.assertNull(capitalize);
    }
}