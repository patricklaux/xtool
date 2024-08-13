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

/**
 * 布尔工具类
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class BooleanUtils {

    public static final String TRUE = "true";
    public static final String FALSE = "false";

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
            if (temp.equalsIgnoreCase(TRUE) || temp.equalsIgnoreCase(FALSE)) {
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
