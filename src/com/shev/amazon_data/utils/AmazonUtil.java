package com.shev.amazon_data.utils;

public class AmazonUtil {
    public static boolean notNull(Object... args) {
        for (Object arg : args) {
            if (arg == null) {
                return false;
            }
        }
        return true;
    }
}
