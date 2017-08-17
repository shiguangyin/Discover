package com.masker.discover.global;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mApp = this;
    }

}
