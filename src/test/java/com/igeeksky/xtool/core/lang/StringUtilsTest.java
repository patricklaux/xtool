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
        Assert.assertEquals("you raise me up, so i can stand on mountains.", StringUtils.toLowerCase("You raise me up, so i can stand on mountains."));
    }

    @Test
    public void unCapitalize() {
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
    public void capitalize() {
        Assert.assertEquals("Practice Is Perfect.", StringUtils.capitalize("practice Is Perfect."));
        Assert.assertEquals("Practice Is Perfect.", StringUtils.capitalize("Practice Is Perfect."));
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
}