package com.masker.discover.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;

/**
 * Author: masker
 * Date: 2017/7/14
 * Description:
 */

public class PhotoDetailActivity extends BaseActivity{
    public static final String URL = "url";

    private PhotoView mPhotoView;

    private String mUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initViews() {
        mPhotoView = find(R.id.photo_view);
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
            Glide.with(this).load(mUrl).into(mPhotoView);
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

}
