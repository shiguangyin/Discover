package com.masker.discover.activity;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.global.Constans;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: masker
 * Date: 2017/8/16
 * Description:
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.ll_unsplash)
    LinearLayout mLlUnsplash;
    @BindView(R.id.ll_github)
    LinearLayout mLlGithub;
    @BindView(R.id.ll_email)
    LinearLayout mLlEmail;
    @BindView(R.id.ll_retrofit)
    LinearLayout mLlRetrofit;
    @BindView(R.id.ll_rx_java)
    LinearLayout mLlRxJava;
    @BindView(R.id.ll_rx_android)
    LinearLayout mLlRxAndroid;
    @BindView(R.id.ll_glide)
    LinearLayout mLlGlide;
    @BindView(R.id.ll_glide_tranformations)
    LinearLayout mLlGlideTranformations;
    @BindView(R.id.ll_logger)
    LinearLayout mLlLogger;
    @BindView(R.id.ll_circle_iv)
    LinearLayout mLlCircleIv;
    @BindView(R.id.ll_photo_view)
    LinearLayout mLlPhotoView;
    @BindView(R.id.ll_avloading_view)
    LinearLayout mLlAvloadingView;
    @BindView(R.id.ll_butter_knife)
    LinearLayout mLlButterKnife;
    @BindView(R.id.ll_fab)
    LinearLayout mLlFab;



    @OnClick({R.id.ll_unsplash,R.id.ll_github,R.id.ll_email,R.id.ll_retrofit,R.id.ll_rx_java,
            R.id.ll_rx_android,R.id.ll_glide,R.id.ll_glide_tranformations,R.id.ll_logger,R.id.ll_circle_iv,
            R.id.ll_photo_view,R.id.ll_avloading_view,R.id.ll_butter_knife,R.id.ll_fab})
    void click(View view){
        switch (view.getId()){
            case R.id.ll_unsplash:
                gotoUrl("https://unsplash.com"+ Constans.UTM_PARAMS);
                break;
            case R.id.ll_github:
                gotoUrl("https://github.com/shiguangyin/Discover");
                break;
            case R.id.ll_email:
                send();
                break;
            case R.id.ll_retrofit:
                gotoUrl("https://github.com/square/retrofit");
                break;
            case R.id.ll_rx_java:
                gotoUrl("https://github.com/ReactiveX/RxJava");
                break;
            case R.id.ll_rx_android:
                gotoUrl("https://github.com/ReactiveX/RxAndroid");
                break;
            case R.id.ll_glide:
                gotoUrl("https://github.com/bumptech/glide");
                break;
            case R.id.ll_glide_tranformations:
                gotoUrl("https://github.com/wasabeef/glide-transformations");
                break;
            case R.id.ll_logger:
                gotoUrl("https://github.com/orhanobut/logger");
                break;
            case R.id.ll_circle_iv:
                gotoUrl("https://github.com/hdodenhof/CircleImageView");
                break;
            case R.id.ll_photo_view:
                gotoUrl("https://github.com/chrisbanes/PhotoView");
                break;
            case R.id.ll_avloading_view:
                gotoUrl("https://github.com/81813780/AVLoadingIndicatorView");
                break;
            case R.id.ll_butter_knife:
                gotoUrl("https://github.com/JakeWharton/butterknife");
                break;
            case R.id.ll_fab:
                gotoUrl("https://github.com/Clans/FloatingActionButton");
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getString(R.string.about));
        }
    }

    @Override
    protected void initData() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            if(info != null){
                mTvVersion.setText(info.versionName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initListeners() {

    }
    private void gotoUrl(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    private void send(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"shiguangyin928@gmail.com"});
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
