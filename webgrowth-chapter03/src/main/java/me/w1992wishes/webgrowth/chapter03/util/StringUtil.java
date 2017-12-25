package me.w1992wishes.webgrowth.chapter03.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by w1992wishes on 2017/12/18.
 */
public final class StringUtil {

    public static boolean isEmpty(String str){
        if (str != null){
            str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
