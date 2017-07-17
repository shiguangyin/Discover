package com.masker.discover.global;

import android.app.Application;
import android.content.Context;

/**
 * CreatedBy: masker
 * Date: 2017/4/27
 * Description: Application
 */

public class App extends Application{
    private static Application mApp;

    public static Context getApp(){
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }


}
