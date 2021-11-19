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
     * 转换值为 {@link Long} 对象
     *
     * @param value 值
     * @return {@link Long}
     */
    public static Long longValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Long.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Integer} 对象
     *
     * @param value 值
     * @return {@link Integer}
     */
    public static Integer intValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Integer.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Short} 对象
     *
     * @param value 值
     * @return {@link Short}
     */
    public static Short shortValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Short.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Byte} 对象
     *
     * @param value 值
     * @return {@link Byte}
     */
    public static Byte byteValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Byte.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Double} 对象
     *
     * @param value 值
     * @return {@link Double}
     */
    public static Double doubleValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Double.valueOf(temp);
        }
        return null;
    }

    /**
     * 转换值为 {@link Float} 对象
     *
     * @param value 值
     * @return {@link Float}
     */
    public static Float floatValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }

        String temp = StringUtils.trimToNull(value.toString());
        if (null != temp) {
            return Float.valueOf(temp);
        }
        return null;
    }
}
