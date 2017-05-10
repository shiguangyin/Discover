package com.masker.discover.photo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.model.entity.PhotoInfo;
import com.masker.discover.utils.ScreenUtils;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description: show photo detail infomation
 */

public class PhotoInfoActivity extends BaseActivity implements PhotoInfoContract.View{
    public static final String ID = "id";
    public static final String IMG_URL = "img_url";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";

    private String mId;
    private String mImgUrl;
    private int mImgWidth;
    private int mImgHeight;

    private Toolbar mToolbar;
    private ImageView mIvPhoto;
    private ProgressBar mProgressBar;
    private CircleImageView mIvAvator;
    private TextView mTvUser;
    private TextView mTvCreatedAt;

    private PhotoInfoContract.Presenter mPresenter;

    @Override
    protected void handleIntent() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(ID);
        mImgUrl = intent.getStringExtra(IMG_URL);
        mImgWidth = intent.getIntExtra(WIDTH,0);
        mImgHeight = intent.getIntExtra(HEIGHT,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_info;
    }

    @Override
    protected void initViews() {
        mToolbar = getViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mIvPhoto = getViewById(R.id.iv_photo);
        resetSize();
        Glide.with(this).load(mImgUrl).into(mIvPhoto);

        mProgressBar = getViewById(R.id.progress_bar);
        mIvAvator = getViewById(R.id.iv_avator);
        mTvUser = getViewById(R.id.tv_user);
        mTvCreatedAt = getViewById(R.id.tv_create_at);

    }

    @Override
    protected void initDatas() {
        new PhotoInfoPresenter(this);
        mPresenter.loadPhotoInfo(mId);
    }

    /*
    * start the activity
     */
    public static void start(Context context,String id,String imgUrl,int width,int height){
        Intent intent = new Intent(context,PhotoInfoActivity.class);
        intent.putExtra(ID,id);
        intent.putExtra(IMG_URL,imgUrl);
        intent.putExtra(WIDTH,width);
        intent.putExtra(HEIGHT,height);
        context.startActivity(intent);
    }

    /*
    * reset size of imageview
     */
    private void resetSize(){
        ViewGroup.LayoutParams lp = mIvPhoto.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(this);
        lp.height = (lp.width*mImgHeight)/mImgWidth;
        mIvPhoto.setLayoutParams(lp);
    }

    @Override
    public void setPresenter(PhotoInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPhotoInfo(PhotoInfo info) {
        mProgressBar.setVisibility(View.GONE);
        String avatorUrl = info.getUser().getProfile_image().getLarge();
        Glide.with(this).load(avatorUrl).into(mIvAvator);

        String user = info.getUser().getName();
        mTvUser.setText(user);

        String createdAt = info.getCreated_at();
        mTvCreatedAt.setText(createdAt);
    }

    @Override
    public void showError() {

    }
}
