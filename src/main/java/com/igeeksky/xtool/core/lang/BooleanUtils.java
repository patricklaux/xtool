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
     * 转换值为 {@link Boolean} 对象
     *
     * @param value 值
     * @return {@link Boolean}
     */
    public static Boolean booleanValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Boolean.valueOf(temp);
        }
        return null;
    }

}
