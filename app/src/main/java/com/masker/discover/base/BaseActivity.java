package com.masker.discover.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Author: masker.
 * Date: 2017/4/24.
 * Description : Base Activity for all activities
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected int mLayoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutId = getLayoutId();
        if(mLayoutId != 0){
            setContentView(mLayoutId);
            initViews();
        }
    }

    protected abstract int getLayoutId();

    /*
    * launch another activity
     */
    protected void invokeActivity(Context context,Class clazz){
        Intent intent = new Intent(context,clazz);
        startActivity(intent);
    }

    /*
    * find view by id
     */
    protected <T extends View> T getViewById(int resId){
        return (T) findViewById(resId);
    }

    protected abstract void initViews();


    /*
    * custom font
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
