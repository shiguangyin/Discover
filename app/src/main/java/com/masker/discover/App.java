package com.masker.discover;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.masker.discover.utils.SpUtils;

/**
 * CreatedBy: masker
 * Date: 2017/4/27
 * Description: Application
 */

public class App extends Application{
    private static Application mApp;

    public static Context getAppContext(){
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static boolean isLogin(){
        String token = SpUtils.getUser(mApp,SpUtils.TOKEN);
        return !TextUtils.isEmpty(token);
    }
}
