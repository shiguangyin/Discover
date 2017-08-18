package com.masker.discover.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: masker
 * Date: 2017/7/14
 * Description:
 */

public class PhotoPreviewActivity extends BaseActivity{
    public static final String URL = "url";
    public static final String TYPE = "type";
    public static final int TYPE_PHOTO = 0;
    public static final int TYPE_AVATAR = 1;
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;
    @BindView(R.id.photo_view_avatar)
    PhotoView mPvAvatar;

    private String mUrl;
    private int mType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        if(mType == TYPE_AVATAR){
            mPhotoView.setVisibility(View.GONE);
            mPvAvatar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListeners() {
        mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                finish();
            }
        });
        mPvAvatar.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        if(!TextUtils.isEmpty(mUrl)){
            Glide.with(this).load(mUrl)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into((mType == TYPE_PHOTO)?mPhotoView:mPvAvatar);
        }
    }

    @Override
    protected void handleIntent() {
        mUrl = getIntent().getStringExtra(URL);
        mType = getIntent().getIntExtra(TYPE,TYPE_PHOTO);
    }

    public static void start(Context context, String url, Bundle bundle){
        Intent intent = new Intent(context,PhotoPreviewActivity.class);
        intent.putExtra(URL,url);
        context.startActivity(intent,bundle);
    }

    public static void start(Context context, String url,Bundle bundle,int type){
        Intent intent = new Intent(context,PhotoPreviewActivity.class);
        intent.putExtra(URL,url);
        intent.putExtra(TYPE,type);
        context.startActivity(intent,bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
