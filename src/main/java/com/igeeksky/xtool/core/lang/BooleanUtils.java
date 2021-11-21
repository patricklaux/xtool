package com.igeeksky.xtool.core.lang;

/**
 * 布尔工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class BooleanUtils {

    private BooleanUtils() {
    }

    /**
     * 转换值为 {@link Boolean}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值（不捕捉转换异常）
     */
    public static Boolean toBoolean(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Boolean) {
            return (Boolean) original;
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (temp != null) {
            if (temp.equalsIgnoreCase("true") || temp.equalsIgnoreCase("false")) {
                return Boolean.valueOf(temp);
            }
            throw new IllegalArgumentException("For input string: \"" + temp + "\"");
        }
        return null;
    }

    /**
     * 转换值为 boolean
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值（捕捉转换异常，异常时返回默认值）
     */
    public static boolean toBoolean(Object original, boolean defaultValue) {
        try {
            Boolean value = toBoolean(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
