package com.masker.discover.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


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
            initListeners();
        }
        initData();
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
    * bind view by id
     */
    protected <T extends View> T bind(int resId){
        return (T) findViewById(resId);
    }

    protected abstract void initViews();

    protected abstract void initData();

    protected void handleIntent(){}

    protected void shortToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected  void initListeners(){};

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void showSnackbar(View view,String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
