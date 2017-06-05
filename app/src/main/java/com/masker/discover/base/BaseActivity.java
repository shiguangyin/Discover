package com.masker.discover.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.masker.discover.R;


/**
 * Author: masker.
 * Date: 2017/4/24.
 * Description : Base Activity for all activities
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected int mLayoutId;
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
        mLayoutId = getLayoutId();
        if(mLayoutId != 0){
            setContentView(mLayoutId);
            initViews();
        }
        initDatas();
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
    protected <T extends View> T find(int resId){
        return (T) findViewById(resId);
    }

    protected abstract void initViews();

    protected abstract void initDatas();

    protected void handleIntent(){}


}
