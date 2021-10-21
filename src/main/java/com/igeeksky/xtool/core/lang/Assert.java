package com.igeeksky.xtool.core.lang;

import java.util.function.Supplier;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-20
 */
public abstract class Assert {

    private Assert() {
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> message) {
        if (!expression) {
            throw new IllegalStateException(message.get());
        }
    }

    public static void isNull(Object object) {
        isNull(object, "");
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new NullPointerException(message);
        }
    }

    public static void isNull(Object object, Supplier<String> message) {
        if (object != null) {
            throw new NullPointerException(message.get());
        }
    }

    public void hasText(String text) {
        hasText(text, "");
    }

    public void hasText(String text, String message) {
        if (StringUtils.isEmpty(text)) {
            throw new NullPointerException(message);
        }
    }

    public void hasText(String text, Supplier<String> message) {
        if (StringUtils.isEmpty(text)) {
            throw new NullPointerException(message.get());
        }
    }
}
