package com.zdc.business.business.util;

import org.springframework.lang.Nullable;

public class AssertUtil {
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
    public static void isNull(@Nullable Object object, String message,String placeholder) {
        if (object != null) {
            throw new IllegalArgumentException(message.replace("{}",placeholder));
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
    public static void notNull(@Nullable Object object, String message,String placeholder) {
        if (object == null) {
            throw new IllegalArgumentException(message.replace("{}",placeholder));
        }
    }

}
