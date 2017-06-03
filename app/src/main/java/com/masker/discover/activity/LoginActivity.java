package com.masker.discover.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.masker.discover.AppConstants;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.model.api.TokenService;
import com.masker.discover.model.entity.TokenBean;
import com.masker.discover.model.http.ApiClient;
import com.masker.discover.utils.SpUtils;

import java.io.ByteArrayInputStream;


import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: login activity
 */

public class LoginActivity extends BaseActivity{



    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    private void fetchToken(String code){
        ApiClient.getClient().create(TokenService.class)
                .getToken(AppConstants.APP_ID,AppConstants.APP_SECRET,
                        AppConstants.REDIRECT_URL,code,
                        AppConstants.GRANT_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TokenBean>() {
                    @Override
                    public void call(TokenBean token) {
                        SpUtils.putUser(LoginActivity.this,SpUtils.TOKEN
                                ,token.getAccess_token());
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i(TAG, "call: "+throwable.getMessage());
                        Log.i(TAG, "call: "+throwable.getStackTrace());
                    }
                });
    }




    @Override
    protected void initDatas() {

    }

}
