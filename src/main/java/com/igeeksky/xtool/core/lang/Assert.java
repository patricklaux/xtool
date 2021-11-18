package com.igeeksky.xtool.core.lang;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-20
 */
public class Assert {

    private Assert() {
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> supplier) {
        if (!expression) {
            throw new IllegalArgumentException(safeGetMessage(supplier));
        }
    }

    public static void isTrue(boolean expression, RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean expression, Supplier<String> supplier) {
        if (expression) {
            throw new IllegalArgumentException(safeGetMessage(supplier));
        }
    }

    public static void isFalse(boolean expression, RuntimeException e) {
        if (expression) {
            throw e;
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("[Assertion failed] - the map must not be null or empty");
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map, Supplier<String> supplier) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(safeGetMessage(supplier));
        }
    }

    public static void notEmpty(Map<?, ?> map, RuntimeException e) {
        if (map == null || map.isEmpty()) {
            throw e;
        }
    }

    public static void hasText(String text) {
        hasText(text, "[Assertion failed] - the text must not be null or blank");
    }

    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasText(String text, Supplier<String> supplier) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(safeGetMessage(supplier));
        }
    }

    public static void hasText(String text, RuntimeException e) {
        if (!StringUtils.hasText(text)) {
            throw e;
        }
    }

    public static void hasLength(String text) {
        hasLength(text, "[Assertion failed] - the text must not be null or empty");
    }

    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(String text, Supplier<String> supplier) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(safeGetMessage(supplier));
        }
    }

    public static void hasLength(String text, RuntimeException e) {
        if (!StringUtils.hasLength(text)) {
            throw e;
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("[Assertion failed] - the object must not be null");
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, Supplier<String> supplier) {
        if (object == null) {
            throw new IllegalArgumentException(safeGetMessage(supplier));
        }
    }

    public static void notNull(Object object, RuntimeException e) {
        if (object == null) {
            throw e;
        }
    }

    private static String safeGetMessage(Supplier<String> supplier) {
        return (supplier != null ? supplier.get() : null);
    }
}
