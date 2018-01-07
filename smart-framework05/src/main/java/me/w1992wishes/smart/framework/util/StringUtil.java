package me.w1992wishes.smart.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by w1992wishes on 2017/12/18.
 */
public final class StringUtil {

    public static final String SEPARATOR = String.valueOf((char) 29);

    public static boolean isEmpty(String str){
        if (str != null){
            str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static String[] splitString(String body, String s) {
        return body.split(s);
    }
}
