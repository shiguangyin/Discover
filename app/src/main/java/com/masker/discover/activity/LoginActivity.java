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

    private Toolbar mToolbar;
    private ProgressBar mProgressbar;
    private WebView mWvLogin;

    private AlertDialog mDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        mToolbar = getViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mProgressbar = getViewById(R.id.progress_bar);
        mWvLogin = getViewById(R.id.wv_login);
        mWvLogin.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    mProgressbar.setVisibility(View.GONE);
                }
                else{
                    mProgressbar.setVisibility(View.VISIBLE);
                    mProgressbar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWvLogin.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if(url.startsWith(AppConstants.REDIRECT_URL)){
                    final String code = url.substring(url.indexOf("=")+1);
                    Log.i(TAG, "shouldOverrideUrlLoading: "+code);
                    Log.i(TAG, "shouldInterceptRequest: "+Thread.currentThread().getName());
                    String response = "<p>Success</p>";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fetchToken(code);
                        }
                    });
                    WebResourceResponse res = new WebResourceResponse("text/html", "utf-8",
                            new ByteArrayInputStream(response.getBytes()));
                    return res;
                }
                return super.shouldInterceptRequest(view, url);
            }
        });
    }

    private void fetchToken(String code){
        showDialog();
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
                        if(mDialog != null ){
                            mDialog.dismiss();
                        }
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


    private void showDialog(){
        mDialog = new AlertDialog.Builder(this)
                .create();
        mDialog.setContentView(new ProgressBar(this));
        mDialog.show();
    }

    @Override
    protected void initDatas() {
        mWvLogin.loadUrl(AppConstants.OAUTH_URL);
    }

}
