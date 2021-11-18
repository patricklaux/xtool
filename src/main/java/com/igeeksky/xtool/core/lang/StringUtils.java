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
     * 判断字符串是否为空或空白
     * <pre>
     *     hasText(null) = false;
     *     hasText("") = false;
     *     hasText(" ") = false;
     *     hasText("a") = true;
     *     hasText(" a ") = true;
     * </pre>
     *
     * @param text 字符串对象
     * @return 空对象或空白字符串，false；非空对象且包含非空白字符，true。
     */
    public static boolean hasText(String text) {
        return (null != trimToNull(text));
    }

    /**
     * 判断字符串是否为空或空白
     * <pre>
     *     hasLength(null) = false;
     *     hasLength("") = false;
     *     hasLength(" ") = true;
     *     hasLength("a") = true;
     *     hasLength(" a ") = true;
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
     * 转大写
     *
     * @param text 字符串
     * @return 转大写后的字符串
     */
    public static String toUpperCase(String text) {
        String temp = trimToNull(text);
        return (null != temp) ? temp.toUpperCase() : null;
    }

    /**
     * 转小写
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
            return chars;
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
            return chars;
        }
        return chars;
    }
}