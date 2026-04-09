package com.shumei.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类（线程安全，统一管理随机生成逻辑）
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    /**
     * 生成固定长度数字字符串，例如 length=6 -> "830271"
     */
    public static String randomDigits(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 在给定字符集中随机生成固定长度字符串
     */
    public static String randomFromCharset(String charset, int length) {
        if (charset == null || charset.isEmpty()) {
            throw new IllegalArgumentException("charset must not be empty");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(charset.length());
            sb.append(charset.charAt(index));
        }
        return sb.toString();
    }
}
