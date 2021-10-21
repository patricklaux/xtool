package com.igeeksky.xtool.core.lang;

/**
 * 字符串工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1
 */
public abstract class StringUtils {

    private static final char UPPER_CASE_BEGIN = 'A';
    private static final char UPPER_CASE_END = 'Z';

    private StringUtils() {
    }

    /**
     * 判断字符串是否为空或空白
     *
     * @param text 字符串对象
     * @return 空对象或空白字符串，true；非空对象且非空白，false。
     */
    public static boolean isEmpty(final String text) {
        return (null == text || text.trim().isEmpty());
    }

    /**
     * 判断字符串是否为非空且非空白
     *
     * @param text 字符串对象
     * @return 空对象或空白字符串，false；非空对象且非空白，true。
     */
    public static boolean isNotEmpty(final String text) {
        return (null != text && !(text.trim().isEmpty()));
    }

    /**
     * 去除字符串的前后空白字符
     *
     * @param text 字符串对象
     * @return 去除前后空白字符后，判断是否为空白字符串，如果为空白字符串，返回 null；否则返回去除空白后的字符串。
     */
    public static String trim(String text) {
        if (null == text) {
            return null;
        }
        String temp = text.trim();
        if (temp.isEmpty()) {
            return null;
        }
        return temp;
    }

    /**
     * 转大写
     *
     * @param text 字符串
     * @return 转大写后的字符串
     */
    public static String toUpperCase(String text) {
        String temp = trim(text);
        return (null != temp) ? temp.toUpperCase() : null;
    }

    /**
     * 转小写
     *
     * @param text 字符串
     * @return 转小写后的字符串
     */
    public static String toLowerCase(String text) {
        String temp = trim(text);
        return (null != temp) ? temp.toLowerCase() : null;
    }

    /**
     * 字符串的首字符转小写
     *
     * @param text 字符串
     * @return 首字符转小写后的字符串
     */
    public static String replaceFirstToLowerCase(String text) {
        char first = text.charAt(0);
        if (first >= UPPER_CASE_BEGIN && first <= UPPER_CASE_END) {
            char[] chars = text.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return String.copyValueOf(chars);
        }
        return text;
    }
}
