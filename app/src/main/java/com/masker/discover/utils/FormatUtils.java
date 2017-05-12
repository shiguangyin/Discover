package com.masker.discover.utils;

/**
 * CreatedBy: masker
 * Date: 2017/5/10
 * Description: date utils
 */

public class FormatUtils {
    /**
     *
     * @param origin 2014-06-02T14:57:23-04:00
     * @return 2014-06-02
     */
    public static String transform(String origin){
        return origin.substring(0,origin.indexOf("T"));
    }

    public static String getNum(int num){
        if(num > 1000){
            return String.valueOf(num/1000)+"K";
        }
        return String.valueOf(num);
    }

}
