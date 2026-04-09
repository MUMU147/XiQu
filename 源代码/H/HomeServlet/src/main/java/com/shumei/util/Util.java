package com.shumei.util;

public class Util {

    /**
     * 判断字符串是否为null或者字符串是否为空串
     * @param str 字符串
     * @return true为是，false为假
     */
    public static boolean isEmpty(String str){
        return null == str || str.equals("");
    }
}
