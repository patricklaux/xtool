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

import com.igeeksky.xtool.core.collection.CollectionUtils;
import com.igeeksky.xtool.core.collection.Maps;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 断言
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-20
 */
public class Assert {

    private Assert() {
    }

    /**
     * 表达式是否为真
     *
     * @param expression 表达式
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * 表达式是否为真
     *
     * @param expression 表达式
     * @param message    异常提示信息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 表达式是否为真
     *
     * @param expression 表达式
     * @param supplier   异常提示信息
     */
    public static void isTrue(boolean expression, Supplier<String> supplier) {
        if (!expression) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 表达式是否为真
     *
     * @param expression 表达式
     * @param e          异常
     */
    public static void isTrue(boolean expression, RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }

    /**
     * 表达式是否为假
     *
     * @param expression 表达式
     */
    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }

    /**
     * 表达式是否为假
     *
     * @param expression 表达式
     * @param message    异常提示信息
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 表达式是否为假
     *
     * @param expression 表达式
     * @param supplier   异常提示信息
     */
    public static void isFalse(boolean expression, Supplier<String> supplier) {
        if (expression) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 表达式是否为假
     *
     * @param expression 表达式
     * @param e          异常
     */
    public static void isFalse(boolean expression, RuntimeException e) {
        if (expression) {
            throw e;
        }
    }

    /**
     * 数组是否不为空且至少包含一个元素
     *
     * @param array 数组
     * @param <T>   对象类型
     */
    public static <T> void notEmpty(T[] array) {
        notEmpty(array, "[Assertion failed] - this array must not be null or empty");
    }

    /**
     * 数组是否不为空且至少包含一个元素
     *
     * @param array   数组
     * @param message 异常提示信息
     * @param <T>     对象类型
     */
    public static <T> void notEmpty(T[] array, String message) {
        if (ArrayUtils.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 数组是否不为空且至少包含一个元素
     *
     * @param array    数组
     * @param supplier 异常提示信息
     * @param <T>      对象类型
     */
    public static <T> void notEmpty(T[] array, Supplier<String> supplier) {
        if (ArrayUtils.isEmpty(array)) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 数组是否不为空且至少包含一个元素
     *
     * @param array 数组
     * @param e     异常
     * @param <T>   对象类型
     */
    public static <T> void notEmpty(T[] array, RuntimeException e) {
        if (ArrayUtils.isEmpty(array)) {
            throw e;
        }
    }

    /**
     * 列表是否不为空且至少包含一个元素
     *
     * @param list 列表
     */
    public static void notEmpty(List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("[Assertion failed] - this list must not be null or empty");
        }
    }

    /**
     * 列表是否不为空且至少包含一个元素
     *
     * @param list    列表
     * @param message 异常提示信息
     */
    public static void notEmpty(List<?> list, String message) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 列表是否不为空且至少包含一个元素
     *
     * @param list     列表
     * @param supplier 异常提示信息
     */
    public static void notEmpty(List<?> list, Supplier<String> supplier) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 列表是否不为空且至少包含一个元素
     *
     * @param list 列表
     * @param e    异常
     */
    public static void notEmpty(List<?> list, RuntimeException e) {
        if (CollectionUtils.isEmpty(list)) {
            throw e;
        }
    }

    /**
     * map 是否不为空且至少包含一个元素
     *
     * @param map map
     */
    public static void notEmpty(Map<?, ?> map) {
        if (Maps.isEmpty(map)) {
            throw new IllegalArgumentException("[Assertion failed] - this map must not be null or empty");
        }
    }

    /**
     * map 是否不为空且至少包含一个元素
     *
     * @param map     map
     * @param message 异常信息
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (Maps.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * map 是否不为空且至少包含一个元素
     *
     * @param map      map
     * @param supplier 异常信息
     */
    public static void notEmpty(Map<?, ?> map, Supplier<String> supplier) {
        if (Maps.isEmpty(map)) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * map 是否不为空且至少包含一个元素
     *
     * @param map map
     * @param e   异常
     */
    public static void notEmpty(Map<?, ?> map, RuntimeException e) {
        if (Maps.isEmpty(map)) {
            throw e;
        }
    }

    /**
     * 字符串是否不为空且至少包含一个非空白字符
     *
     * @param text 字符串
     */
    public static void hasText(String text) {
        hasText(text, "[Assertion failed] - this text must not be null or blank");
    }

    /**
     * 字符串是否不为空且至少包含一个非空白字符
     *
     * @param text    字符串
     * @param message 异常信息
     */
    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 字符串是否不为空且至少包含一个非空白字符
     *
     * @param text     字符串
     * @param supplier 异常信息
     */
    public static void hasText(String text, Supplier<String> supplier) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 字符串是否不为空且至少包含一个非空白字符
     *
     * @param text 字符串
     * @param e    异常
     */
    public static void hasText(String text, RuntimeException e) {
        if (!StringUtils.hasText(text)) {
            throw e;
        }
    }

    /**
     * 字符串是否不为空且至少包含一个字符（可以为空白字符）
     *
     * @param text 字符串
     */
    public static void hasLength(String text) {
        hasLength(text, "[Assertion failed] - this text must not be null or empty");
    }

    /**
     * 字符串是否不为空且至少包含一个字符（可以为空白字符）
     *
     * @param text    字符串
     * @param message 异常信息
     */
    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 字符串是否不为空且至少包含一个字符（可以为空白字符）
     *
     * @param text     字符串
     * @param supplier 异常信息
     */
    public static void hasLength(String text, Supplier<String> supplier) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 字符串是否不为空且至少包含一个字符（可以为空白字符）
     *
     * @param text 字符串
     * @param e    异常
     */
    public static void hasLength(String text, RuntimeException e) {
        if (!StringUtils.hasLength(text)) {
            throw e;
        }
    }

    public static void hasLength(char[] chars) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException("array dimensions must be greater than 0.");
        }
    }

    public static void hasLength(char[] chars, String message) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(char[] chars, Supplier<String> supplier) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    public static void hasLength(char[] chars, RuntimeException e) {
        if (chars == null || chars.length == 0) {
            throw e;
        }
    }

    /**
     * 对象是否不为空
     *
     * @param object 对象
     */
    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("[Assertion failed] - this object must not be null.");
        }
    }

    /**
     * 对象是否不为空
     *
     * @param object  对象
     * @param message 异常信息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 对象是否不为空
     *
     * @param object   对象
     * @param supplier 异常信息
     */
    public static void notNull(Object object, Supplier<String> supplier) {
        if (object == null) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    /**
     * 对象是否不为空
     *
     * @param object 对象
     * @param e      异常
     */
    public static void notNull(Object object, RuntimeException e) {
        if (object == null) {
            throw e;
        }
    }

    public static void equals(Object a, Object b) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException("[Assertion failed] - these objects is not equals");
        }
    }

    public static void equals(Object a, Object b, String message) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void equals(Object a, Object b, Supplier<String> supplier) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException(safeGet(supplier));
        }
    }

    private static <T> T safeGet(Supplier<T> supplier) {
        return (supplier != null ? supplier.get() : null);
    }
}
