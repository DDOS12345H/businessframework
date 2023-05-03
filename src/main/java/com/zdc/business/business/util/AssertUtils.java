package com.zdc.business.business.util;

import org.springframework.lang.Nullable;

public class AssertUtils {
    /**
     * 如果不为空时，抛出异常
     * @param object
     * @param message
     */
    public static void isNull(@Nullable Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 如果为空时，抛出异常
     * @param object
     * @param message
     */
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
