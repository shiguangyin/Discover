package com.masker.discover.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.global.Constans;
import com.masker.discover.global.UserManager;
import com.masker.discover.home.HomeActivity;
import com.masker.discover.model.api.TokenService;
import com.masker.discover.model.api.UserService;
import com.masker.discover.model.entity.TokenBean;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.http.HttpClient;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

public class LoginActivity extends BaseActivity{

    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.btn_close)
    ImageButton mBtnClose;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_join)
    Button mBtnJoin;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null &&
                intent.getData() != null &&
                TextUtils.equals(intent.getData().getAuthority(), Constans.OATHU_HOST)) {
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
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.bg_login)
                .bitmapTransform(new BlurTransformation(this,15))
                .into(mIvBg);

    }


    private void fetchToken(String code) {
        mLoadingView.smoothToShow();
        HttpClient.getClient().create(TokenService.class)
                .getToken(Constans.APP_ID, Constans.APP_SECRET,
                        Constans.REDIRECT_URL, code,
                        Constans.GRANT_TYPE)
                .flatMap(new Func1<TokenBean, Observable<UserInfoBean>>() {
                    @Override
                    public Observable<UserInfoBean> call(TokenBean tokenBean) {
                        UserManager.getInstance().setToken(tokenBean.getAccess_token());
                        return HttpClient.getClient().create(UserService.class).getMyInfo();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean myInfoBean) {
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

    @OnClick(R.id.btn_close)
    void close(){
        finish();
    }


    @OnClick(R.id.btn_login)
    void login() {
        Uri uri = Uri.parse(Constans.OAUTH_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.btn_join)
    void join() {
        Uri uri = Uri.parse(Constans.JOIN_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
