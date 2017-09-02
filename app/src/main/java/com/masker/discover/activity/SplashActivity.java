package com.masker.discover.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: masker.
 * Date: 2017/4/24.
 * Description :
 */

public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.content)
    RelativeLayout mContent;
    @BindView(R.id.tv_name)
    TextView mTvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mIvSplash,"alpha",0.1f,1f);
        ObjectAnimator scale = ObjectAnimator.ofFloat(mIvSplash,"scaleX",1f,1.2f);
        ObjectAnimator scale2 = ObjectAnimator.ofFloat(mIvSplash,"scaleY",1f,1.2f);
        ObjectAnimator transition = ObjectAnimator.ofFloat(mTvName,"translationY",0,600);
        transition.setDuration(800);
        transition.start();
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2500);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                invokeActivity(SplashActivity.this, HomeActivity.class);
                finish();
            }
        });
        set.playTogether(alpha,scale,scale2);
        set.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }
}
