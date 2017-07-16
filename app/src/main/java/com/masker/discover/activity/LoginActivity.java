package com.masker.discover.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.masker.discover.AppConstants;
import com.masker.discover.R;
import com.masker.discover.UserManager;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.home.HomeActivity;
import com.masker.discover.model.api.TokenService;
import com.masker.discover.model.api.UserService;
import com.masker.discover.model.entity.MyInfoBean;
import com.masker.discover.model.entity.TokenBean;
import com.masker.discover.model.http.HttpClient;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import jp.wasabeef.glide.transformations.BlurTransformation;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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
    private AVLoadingIndicatorView mLoadingView;
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
        mIvBg = bind(R.id.iv_bg);
        Glide.with(this).load(R.drawable.bg_login)
                .bitmapTransform(new BlurTransformation(this))
                .into(mIvBg);

        mLoadingView = bind(R.id.loading_view);
        mRlContent = bind(R.id.rl_content);

        mBtnLogin = bind(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnJoin = bind(R.id.btn_join);
        mBtnJoin.setOnClickListener(this);
        mBtnClose = bind(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
    }



    private void fetchToken(String code){
        mLoadingView.smoothToShow();
        HttpClient.getClient().create(TokenService.class)
                .getToken(AppConstants.APP_ID,AppConstants.APP_SECRET,
                        AppConstants.REDIRECT_URL,code,
                        AppConstants.GRANT_TYPE)
                .flatMap(new Func1<TokenBean, Observable<MyInfoBean>>() {
                    @Override
                    public Observable<MyInfoBean> call(TokenBean tokenBean) {
                        UserManager.getInstance().setToken(tokenBean.getAccess_token());
                        return HttpClient.getClient().create(UserService.class).getMyInfo();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MyInfoBean>() {
                    @Override
                    public void call(MyInfoBean myInfoBean) {
                        UserManager.getInstance().writeMyInfo(myInfoBean);
                        mLoadingView.smoothToHide();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLoadingView.smoothToHide();
                        Snackbar.make(mRlContent, R.string.oauth_failed,
                                Snackbar.LENGTH_SHORT).show();
                        Logger.i(throwable.getMessage());
                    }
                });
    }




    @Override
    protected void initData() {

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
