package com.masker.discover.photo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.activity.PhotoDetailActivity;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.global.App;
import com.masker.discover.global.Constans;
import com.masker.discover.global.PhotoManager;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.TagBean;
import com.masker.discover.rxbus.LikeEvent;
import com.masker.discover.rxbus.RxBus;
import com.masker.discover.utils.ScreenUtils;
import com.masker.discover.utils.ShareUtils;
import com.masker.discover.utils.SpUtils;
import com.masker.discover.widget.PhotoExifDialog;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;


/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description: show photo detail infomation
 */

public class PhotoInfoActivity extends BaseMvpActivity implements PhotoInfoContract.View{
    public static final String DIALOG = "dialog";
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
    private FloatingActionMenu mActionMenu;
    private FloatingActionButton mFabLike;
    private FloatingActionButton mFabInfo;
    private FloatingActionButton mFabDownload;

    private AVLoadingIndicatorView mLoadingView;
    private RecyclerView mRecyclerView;
    private List<Object> mDatas;
    private PhotoInfoAdapter mAdapter;

    private PhotoInfoPresenter mPresenter;
    private PhotoBean mPhotoBean;
    private boolean mIsLiked;



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
        mToolbar = bind(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(false);
        }
        mIvPhoto = bind(R.id.iv_photo);
        resetSize();
        Glide.with(this).load(mImgUrl).into(mIvPhoto);

        mActionMenu = bind(R.id.fab_menu);
        mActionMenu.setClosedOnTouchOutside(true);
        createCustomAnimation();
        mFabLike = bind(R.id.fab_like);
        mFabInfo = bind(R.id.fab_info);
        mFabDownload = bind(R.id.fab_download);

        mLoadingView = bind(R.id.loading_view);
        mLoadingView.smoothToShow();
        mRecyclerView = bind(R.id.recycler_view);
        mDatas = new ArrayList<>();
        mAdapter = new PhotoInfoAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initListeners() {
        mIvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(PhotoInfoActivity.this,mIvPhoto,"photo");
                PhotoDetailActivity.start(PhotoInfoActivity.this,mImgUrl,options.toBundle());

            }
        });
        mFabInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPhotoBean != null){
                    showExifDialog(mPhotoBean);
                }
            }
        });
        mFabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserManager.getInstance().isLogin()){
                    mFabLike.setIndeterminate(true);
                    if(mIsLiked){
                        mPresenter.unlikePhoto(mId);
                    }
                    else{
                        mPresenter.likePhoto(mId);
                    }
                }
                else{
                    startActivity(new Intent(PhotoInfoActivity.this,
                            LoginActivity.class));
                }
            }
        });
        mFabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPhotoBean != null){
                    String url = null;
                    String quality  = SpUtils.getString(Constans.SETTING_SP_NAME,
                            getString(R.string.key_download_quality));
                    PhotoBean.UrlsBean urlsBean = mPhotoBean.getUrls();
                    if(TextUtils.isEmpty(quality) || quality.equals(Constans.REGULAR)){
                        quality = Constans.REGULAR;
                        url = urlsBean.getRegular();
                    }
                    else if(quality.equals(Constans.RAW)){
                        url = urlsBean.getRaw();
                    }
                    else if(quality.equals(Constans.FULL)){
                        url = urlsBean.getFull();
                    }
                    else if(quality.equals(Constans.SMALL)){
                        url = urlsBean.getSmall();
                    }
                    else{
                        url = urlsBean.getThumb();
                    }
                    String name = mPhotoBean.getId()+"_"+quality+".jpg";
                    Logger.i("url = "+url+" name = "+name);
                    PhotoManager.getInstance(App.getApp()).download(url,name);
                    Snackbar.make(mRecyclerView,R.string.start_download,
                            Snackbar.LENGTH_SHORT).show();
                }
                mActionMenu.close(true);
            }
        });

    }

    @Override
    protected void initData() {
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
        mPhotoBean = info;
        mLoadingView.smoothToHide();

        mIsLiked = info.isLiked_by_user();
        setLike(mIsLiked);

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
            if(mPhotoBean != null){
                String content = "By "+mPhotoBean.getUser().getName()+" at "+mPhotoBean.getCreated_at()
                        +" "+mPhotoBean.getLinks().getHtml();
                ShareUtils.share(this,getString(R.string.APP_NAME),content);
            }
        }
        else if(item.getItemId() == R.id.action_link){
            if(mPhotoBean != null){
                String url = mPhotoBean.getLinks().getHtml()+ Constans.UTM_PARAMS;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        }
        else if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(mActionMenu.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(mActionMenu.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(mActionMenu.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(mActionMenu.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mActionMenu.getMenuIconView().setImageResource(mActionMenu.isOpened()
                        ? R.drawable.ic_expand_less_white_24dp : R.drawable.ic_expand_more_white_24dp);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        mActionMenu.setIconToggleAnimatorSet(set);
    }

    private void showExifDialog(PhotoBean photoBean){
        mActionMenu.close(true);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(DIALOG);
        if(fragment != null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
        PhotoExifDialog dialog = new PhotoExifDialog();
        dialog.setData(photoBean);
        dialog.show(fm,DIALOG);

    }

    @Override
    public void updatePhoto(LikeResponseBean bean) {
        mFabLike.hideProgress();
        mActionMenu.close(true);
        setLike(bean.getPhoto().isLiked_by_user());
        RxBus.post(new LikeEvent(bean));
    }

    @Override
    public void showLikeError(String message, String id) {
        mFabLike.hideProgress();
        mActionMenu.close(true);
    }

    private void setLike(boolean like){
        mIsLiked = like;
        if(like){
            mFabLike.setImageResource(R.drawable.ic_like_red_24dp);
            mFabLike.setLabelText(getString(R.string.dislike));
        }
        else{
            mFabLike.setImageResource(R.drawable.ic_like_fill_white_24dp);
            mFabLike.setLabelText(getString(R.string.like));
        }
    }
}
