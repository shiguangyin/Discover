package com.masker.discover.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.masker.discover.App;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: sharedpreferences utils
 */

public class SpUtils {
    public static final String USER = "USER";

    public static final String TOKEN = "token";

    public static String getUser(String key){
        SharedPreferences sp = App.getAppContext().getSharedPreferences(USER,Context.MODE_PRIVATE);
        return sp.getString(key,null);
    }

    public static void putUser(String key,String value){
        SharedPreferences sp = App.getAppContext().getSharedPreferences(USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }

}
