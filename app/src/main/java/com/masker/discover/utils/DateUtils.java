package com.masker.discover.utils;

/**
 * CreatedBy: masker
 * Date: 2017/5/10
 * Description: date utils
 */

public class DateUtils {
    /**
     *
     * @param origin 2014-06-02T14:57:23-04:00
     * @return 2014-06-02
     */
    public static String transform(String origin){
        return origin.substring(0,origin.indexOf("T"));
    }

}
