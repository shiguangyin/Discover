package com.masker.discover.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.home.HomeActivity;

/**
 * Author: masker.
 * Date: 2017/4/24.
 * Description :
 */

public class SplashActivity extends BaseActivity{

    private RelativeLayout mRlContent;
    private ImageView mIvSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                invokeActivity(SplashActivity.this,HomeActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mIvSplash.startAnimation(animation);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        mIvSplash = find(R.id.iv_splash);
        mRlContent = find(R.id.content);
    }

    @Override
    protected void initData() {

    }
}
