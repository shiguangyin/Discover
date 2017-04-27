package com.masker.discover;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * CreatedBy: masker
 * Date: 2017/4/27
 * Description: Application
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Courier.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
