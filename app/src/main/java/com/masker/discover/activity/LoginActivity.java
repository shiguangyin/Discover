package com.masker.discover.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.masker.discover.AppConstants;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.home.HomeActivity;
import com.masker.discover.model.UserManager;
import com.masker.discover.model.api.TokenService;
import com.masker.discover.model.entity.TokenBean;
import com.masker.discover.model.http.ApiClient;
import com.orhanobut.logger.Logger;


import jp.wasabeef.glide.transformations.BlurTransformation;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: login activity
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mIvBg;
    private Button mBtnLogin;
    private Button mBtnJoin;
    private ImageButton mBtnClose;
    private ProgressBar mProgressBar;
    private RelativeLayout mRlContent;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent != null &&
                intent.getData() != null &&
                TextUtils.equals(intent.getData().getAuthority(),AppConstants.OATHU_HOST)){
            String code = intent.getData().getQueryParameter("code");
            fetchToken(code);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        mIvBg = find(R.id.iv_bg);
        Glide.with(this).load(R.drawable.bg_login)
                .bitmapTransform(new BlurTransformation(this))
                .into(mIvBg);

        mProgressBar = find(R.id.progress_bar);
        mRlContent = find(R.id.rl_content);

        mBtnLogin = find(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnJoin = find(R.id.btn_join);
        mBtnJoin.setOnClickListener(this);
        mBtnClose = find(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
    }



    private void fetchToken(String code){
        mProgressBar.setVisibility(View.VISIBLE);
        ApiClient.getClient().create(TokenService.class)
                .getToken(AppConstants.APP_ID,AppConstants.APP_SECRET,
                        AppConstants.REDIRECT_URL,code,
                        AppConstants.GRANT_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TokenBean>() {
                    @Override
                    public void call(TokenBean token) {
                        UserManager.getInstance().setToken(token.getAccess_token());
                        mProgressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mProgressBar.setVisibility(View.GONE);
                        Snackbar.make(mRlContent,R.string.oauth_failed,
                                Snackbar.LENGTH_SHORT).show();
                        Logger.i(throwable.getMessage());
                    }
                });
    }




    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_join:
                join();
                break;
            case R.id.btn_close:
                finish();
                break;
            default:
                break;
        }
    }

    private void login(){
        Uri uri = Uri.parse(AppConstants.OAUTH_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    private void join(){
        Uri uri = Uri.parse(AppConstants.JOIN_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

}
