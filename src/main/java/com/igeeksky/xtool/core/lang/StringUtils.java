package com.igeeksky.xtool.core.lang;

/**
 * 字符串工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1
 */
public class StringUtils {

    private static final char UPPER_CASE_BEGIN = 'A';
    private static final char UPPER_CASE_END = 'Z';

    private static final char LOWER_CASE_BEGIN = 'a';
    private static final char LOWER_CASE_END = 'z';

    private StringUtils() {
    }

    /**
     * 判断字符串是否有非空白字符
     * <pre>
     *     StringUtils.hasText(null) == false;
     *     StringUtils.hasText("") == false;
     *     StringUtils.hasText(" ") == false;
     *     StringUtils.hasText("a") == true;
     *     StringUtils.hasText(" a ") == true;
     * </pre>
     *
     * @param text 字符串对象
     * @return 空对象或空白字符串，false；非空对象且包含非空白字符，true。
     */
    public static boolean hasText(String text) {
        return (null != trimToNull(text));
    }

    /**
     * 判断字符串是否有长度
     * <pre>
     *     StringUtils.hasLength(null) == false;
     *     StringUtils.hasLength("") == false;
     *     StringUtils.hasLength(" ") == true;
     *     StringUtils.hasLength("a") == true;
     *     StringUtils.hasLength(" a ") == true;
     * </pre>
     *
     * @param text 字符串对象
     * @return 空对象或空白字符串，false；非空对象且非空白，true。
     */
    public static boolean hasLength(String text) {
        return (null != text && !text.isEmpty());
    }

    /**
     * 去除字符串的前后空白字符
     * <pre>
     *     trim(null) = null;
     *     trim("") = "";
     *     trim(" ") = "";
     *     trim("a") = "a";
     *     trim(" a ") = "a";
     * </pre>
     *
     * @param text 字符串对象
     * @return 去除前后空白字符的字符串
     */
    public static String trim(String text) {
        return (null == text) ? null : text.trim();
    }

    /**
     * 去除字符串的前后空白字符
     *
     * @param text 字符串对象
     * @return 去除前后空白字符后，判断是否为空字符串，如果为空字符串，返回 null；否则返回去除空白后的字符串。
     */
    public static String trimToNull(String text) {
        if (null == text) {
            return null;
        }
        String temp = text.trim();
        return temp.isEmpty() ? null : temp;
    }

    /**
     * 先去除空白，然后转大写
     * <p>
     * 转大写之前会先调用{@link StringUtils#trimToNull}方法，因此如果字符串没有非空白字符，返回结果为 null
     * <pre>
     *     情形一：字符串为空对象，upperCase == null
     *     String upperCase = StringUtils.toUpperCase(null);
     *     Assert.assertNull(upperCase);
     *
     *     情形二：字符串无字符，upperCase == null
     *     upperCase = StringUtils.toUpperCase("");
     *     Assert.assertNull(upperCase);
     *
     *     情形三：字符串只有空白字符，upperCase == null
     *     upperCase = StringUtils.toUpperCase("   ");
     *     Assert.assertNull(upperCase);
     *
     *     情形四：字符串有非空白字符，upperCase == "AAA"
     *     upperCase = StringUtils.toUpperCase("aaa");
     *     Assert.assertEquals("AAA", upperCase);
     *
     *     情形五：字符串有非空白字符，upperCase == "AAA"
     *     upperCase = StringUtils.toUpperCase(" aaa ");
     *     Assert.assertEquals("AAA", upperCase);
     * </pre>
     *
     * @param text 字符串
     * @return 转大写后的字符串
     */
    public static String toUpperCase(String text) {
        String temp = trimToNull(text);
        return (null != temp) ? temp.toUpperCase() : null;
    }

    /**
     * 先去除空白，然后转小写
     * <p>
     * 转大写之前会先调用{@link StringUtils#trimToNull}方法，因此如果字符串没有非空白字符，返回结果为 null
     *
     * @param text 字符串
     * @return 转小写后的字符串
     */
    public static String toLowerCase(String text) {
        String temp = trimToNull(text);
        return (null != temp) ? temp.toLowerCase() : null;
    }

    /**
     * 字符串的首字符转小写
     *
     * @param text 字符串
     * @return 首字符转小写后的字符串
     */
    public static String unCapitalize(String text) {
        char first = text.charAt(0);
        if (first >= UPPER_CASE_BEGIN && first <= UPPER_CASE_END) {
            char[] chars = text.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return String.copyValueOf(chars);
        }
        return text;
    }

    /**
     * 字符串的首字符转小写
     *
     * @param chars 字符数组
     * @return 首字符转小写后的字符串
     */
    public static char[] unCapitalize(char[] chars) {
        char first = chars[0];
        if (first >= UPPER_CASE_BEGIN && first <= UPPER_CASE_END) {
            chars[0] = Character.toLowerCase(chars[0]);
        }
        return chars;
    }

    /**
     * 字符串的首字符转大写
     *
     * @param text 字符串
     * @return 首字符转小写后的字符串
     */
    public static String capitalize(String text) {
        char first = text.charAt(0);
        if (first >= LOWER_CASE_BEGIN && first <= LOWER_CASE_END) {
            char[] chars = text.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return String.copyValueOf(chars);
        }
        return text;
    }

    /**
     * 字符串的首字符转大写
     *
     * @param chars 字符数组
     * @return 首字符转小写后的字符串
     */
    public static char[] capitalize(char[] chars) {
        char first = chars[0];
        if (first >= LOWER_CASE_BEGIN && first <= LOWER_CASE_END) {
            chars[0] = Character.toUpperCase(chars[0]);
        }
        return chars;
    }
}