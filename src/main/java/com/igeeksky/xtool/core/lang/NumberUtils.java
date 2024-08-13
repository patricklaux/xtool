/*
 * Copyright 2017 Patrick.lau All rights reserved.
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

/**
 * 数值工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1 2017-02-28
 */
public class NumberUtils {

    private NumberUtils() {
    }

    /**
     * 转换值为 {@link Long}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值，否则抛出异常信息
     */
    public static Long toLong(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Number) {
            return ((Number) original).longValue();
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (null != temp) {
            return Long.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Long}
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值
     */
    public static long toLong(Object original, long defaultValue) {
        try {
            Long value = toLong(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换值为 {@link Integer}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值，否则抛出异常信息
     */
    public static Integer toInteger(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Number) {
            return ((Number) original).intValue();
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (null != temp) {
            return Integer.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Integer}
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值
     */
    public static int toInteger(Object original, int defaultValue) {
        try {
            Integer value = toInteger(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换值为 {@link Short}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值，否则抛出异常信息
     */
    public static Short toShort(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Number) {
            return ((Number) original).shortValue();
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (null != temp) {
            return Short.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Short}
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值
     */
    public static short toShort(Object original, short defaultValue) {
        try {
            Short value = toShort(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换值为 {@link Byte}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值，否则抛出异常信息
     */
    public static Byte toByte(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Number) {
            return ((Number) original).byteValue();
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (null != temp) {
            return Byte.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Byte}
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值
     */
    public static byte toByte(Object original, byte defaultValue) {
        try {
            Byte value = toByte(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换值为 {@link Double}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值，否则抛出异常信息
     */
    public static Double toDouble(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Number) {
            return ((Number) original).doubleValue();
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (null != temp) {
            return Double.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Double}
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值
     */
    public static double toDouble(Object original, double defaultValue) {
        try {
            Double value = toDouble(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换值为 {@link Float}
     *
     * @param original 原对象
     * @return 如果原对象为空，返回空；如果转换正常，返回转换后的值，否则抛出异常信息
     */
    public static Float toFloat(Object original) {
        if (original == null) {
            return null;
        }

        if (original instanceof Number) {
            return ((Number) original).floatValue();
        }

        String temp = StringUtils.trimToNull(original.toString());
        if (null != temp) {
            return Float.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Float}
     *
     * @param original     原对象
     * @param defaultValue 默认值
     * @return 如果原对象为空或转换异常，返回 defaultValue；否则返回转换后的值
     */
    public static float toFloat(Object original, float defaultValue) {
        try {
            Float value = toFloat(original);
            return (value == null) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
