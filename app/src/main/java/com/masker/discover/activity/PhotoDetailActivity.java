package com.masker.discover.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Author: masker
 * Date: 2017/7/14
 * Description:
 */

public class PhotoDetailActivity extends BaseActivity{
    public static final String URL = "url";

    private PhotoView mPhotoView;
    private AVLoadingIndicatorView mLoadingView;

    private String mUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initViews() {
        mPhotoView = bind(R.id.photo_view);
        mLoadingView = bind(R.id.loading_view);
    }

    @Override
    protected void initListeners() {
        mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        if(!TextUtils.isEmpty(mUrl)){
            Glide.with(this).load(mUrl).into(new GlideDrawableImageViewTarget(mPhotoView){

                @Override
                public void onLoadStarted(Drawable placeholder) {
                    super.onLoadStarted(placeholder);
                    mLoadingView.smoothToShow();
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    mLoadingView.smoothToHide();
                }

                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    mLoadingView.smoothToHide();
                }

            });
        }
    }

    @Override
    protected void handleIntent() {
        mUrl = getIntent().getStringExtra(URL);
    }

    public static void start(Context context, String url, Bundle bundle){
        Intent intent = new Intent(context,PhotoDetailActivity.class);
        intent.putExtra(URL,url);
        context.startActivity(intent,bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("on destroy");
    }
}
