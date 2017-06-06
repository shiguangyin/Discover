package com.masker.discover.photo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.TagBean;
import com.masker.discover.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description: show photo detail infomation
 */

public class PhotoInfoActivity extends BaseMvpActivity implements PhotoInfoContract.View{
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
    private RecyclerView mRecyclerView;
    private List<Object> mDatas;
    private PhotoInfoAdapter mAdapter;

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
        mToolbar = find(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(false);
        }
        mIvPhoto = find(R.id.iv_photo);
        resetSize();
        Glide.with(this).load(mImgUrl).into(mIvPhoto);

        mProgressBar = find(R.id.progress_bar);
        mRecyclerView = find(R.id.recycler_view);
        mDatas = new ArrayList<>();
        mAdapter = new PhotoInfoAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initDatas() {
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

    public static void start(Context context,Bundle bundle,String id,String imgUrl,int width,int height){
        Intent intent = new Intent(context,PhotoInfoActivity.class);
        intent.putExtra(ID,id);
        intent.putExtra(IMG_URL,imgUrl);
        intent.putExtra(WIDTH,width);
        intent.putExtra(HEIGHT,height);
        context.startActivity(intent,bundle);
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
    public void showPhotoInfo(PhotoBean info) {
        mProgressBar.setVisibility(View.GONE);

        mRecyclerView.setVisibility(View.VISIBLE);
        mDatas.add(info);

        List<PhotoBean.RelatedCollectionsBean.ResultsBean> collections
                = info.getRelated_collections().getResults();
        if(collections != null && collections.size()>0){
            int total = info.getRelated_collections().getTotal();
            String titleCollection = "Featured in "+total+" collections";
            mDatas.add(titleCollection);
            mDatas.addAll(collections);
        }

        List<TagBean> tags = info.getTags();
        if(tags != null && tags.size() > 0){
            String titleTag = "Related Tags";
            mDatas.add(titleTag);
            mDatas.addAll(tags);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    protected void attach() {
        mPresenter = new PhotoInfoPresenter(this);
    }

    @Override
    protected void detach() {
        if(mPresenter != null){
            mPresenter.onUnsubscribe();
            mPresenter = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_share){
            Snackbar.make(mRecyclerView,"under developing",Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
