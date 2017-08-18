package com.masker.discover.photo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.activity.PhotoPreviewActivity;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.global.App;
import com.masker.discover.global.Constans;
import com.masker.discover.global.PhotoManager;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.TagBean;
import com.masker.discover.rx.RxBus;
import com.masker.discover.rx.RxTransformer;
import com.masker.discover.rx.event.LikeEvent;
import com.masker.discover.utils.FileUtils;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ScreenUtils;
import com.masker.discover.utils.ShareUtils;
import com.masker.discover.utils.SpUtils;
import com.masker.discover.widget.PhotoExifDialog;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description: show photo detail infomation
 */

public class PhotoInfoActivity extends BaseMvpActivity implements PhotoInfoContract.View {
    public static final String DIALOG = "dialog";
    public static final String ID = "id";
    public static final String IMG_URL = "img_url";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String MINE_TYPE = "mimeType";
    public static final String TYPE_JPG = "image/jpg";
    public static final int CODE_WALL_PAPER = 0x10;


    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
    @BindView(R.id.fab_like)
    FloatingActionButton mFabLike;
    @BindView(R.id.fab_info)
    FloatingActionButton mFabInfo;
    @BindView(R.id.fab_wallpaper)
    FloatingActionButton mFabWallpaper;
    @BindView(R.id.fab_download)
    FloatingActionButton mFabDownload;
    @BindView(R.id.fab_menu)
    FloatingActionMenu mActionMenu;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private String mId;
    private String mImgUrl;
    private int mImgWidth;
    private int mImgHeight;
    private List<Object> mObjects;
    private PhotoInfoAdapter mAdapter;

    private PhotoInfoPresenter mPresenter;
    private PhotoBean mPhotoBean;
    private boolean mIsLiked;


    @Override
    protected void handleIntent() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(ID);
        mImgUrl = intent.getStringExtra(IMG_URL);
        mImgWidth = intent.getIntExtra(WIDTH, 0);
        mImgHeight = intent.getIntExtra(HEIGHT, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        createCustomAnimation();
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        resetSize();
        ImgLoader.loadDontAnimate(this, mImgUrl, mIvPhoto);
        mObjects = new ArrayList<>();
        mAdapter = new PhotoInfoAdapter(this,mObjects);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }



    @OnClick(R.id.iv_photo)
    void preview(){
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(PhotoInfoActivity.this, mIvPhoto, Constans.TRANSITION_PHOTO);
        PhotoPreviewActivity.start(PhotoInfoActivity.this, mImgUrl, options.toBundle());
    }

    @OnClick(R.id.fab_info)
    void showExifDialog() {
        mActionMenu.close(true);
        if(mPhotoBean != null){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag(DIALOG);
            if (fragment != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fragment);
                ft.commit();
            }
            PhotoExifDialog dialog = new PhotoExifDialog();
            dialog.setData(mPhotoBean);
            dialog.show(fm, DIALOG);
        }
        else{
            showSnackbar(getString(R.string.wait));
        }
    }

    @OnClick(R.id.fab_like)
    void onLikeClick(){
        if (UserManager.getInstance().isLogin()) {
            mFabLike.setIndeterminate(true);
            if (mIsLiked) {
                mPresenter.unlikePhoto(mId);
            } else {
                mPresenter.likePhoto(mId);
            }
        } else {
            startActivity(new Intent(PhotoInfoActivity.this,
                    LoginActivity.class));
        }
    }

    @OnClick(R.id.fab_download)
    void download(){
        mActionMenu.close(true);
        if (mPhotoBean != null) {
            String quality = SpUtils.getString(Constans.SP_SETTINGS, getString(R.string.key_download_quality));
            String url = getUrl(quality);
            String name = mPhotoBean.getId() + "_" + quality + ".jpg";
            long id = PhotoManager.getInstance(App.getApp()).download(url, name);
            SpUtils.putString(Constans.SP_LOADING_URL, String.valueOf(id), mPhotoBean.getUrls().getThumb());
            Snackbar.make(mRecyclerView, R.string.start_download,
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.fab_wallpaper)
    void setWallpaper(){
        mActionMenu.close(true);
        if (mPhotoBean != null) {
            String quality = SpUtils.getString(Constans.SP_SETTINGS, getString(R.string.key_wallpaper_quality));
            String url = getUrl(quality);
            final String name = mPhotoBean.getId() + "_" + quality + ".jpg";
            mProgressBar.setVisibility(View.VISIBLE);
            Glide.with(PhotoInfoActivity.this).load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                        @Override
                        public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            mProgressBar.setVisibility(View.GONE);
                            final Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
                            final File imgFile = new File(Environment.getExternalStorageDirectory(), Constans.DOWNLOAD_DIR + File.separator + "wallpaper" +
                                    File.separator + name);
                            Observable.create(new Observable.OnSubscribe<Boolean>() {
                                @Override
                                public void call(Subscriber<? super Boolean> subscriber) {
                                    boolean success = FileUtils.saveBitmap(resource, imgFile, format);
                                    subscriber.onNext(success);
                                }
                            }).compose(RxTransformer.<Boolean>ioMain())
                                    .subscribe(new Action1<Boolean>() {
                                        @Override
                                        public void call(Boolean aBoolean) {
                                            mProgressBar.setVisibility(View.GONE);
                                            if (aBoolean == true) {
                                                Uri uri = Uri.parse(imgFile.getAbsolutePath());
                                                setWallpaper(uri);
                                            }
                                        }
                                    });
                        }
                    });

        }
    }

    private String getUrl(String quality){
        String url = null;
        PhotoBean.UrlsBean urlsBean = mPhotoBean.getUrls();
        if (TextUtils.isEmpty(quality) || quality.equals(Constans.REGULAR)) {
            quality = Constans.REGULAR;
            url = urlsBean.getRegular();
        } else if (quality.equals(Constans.RAW)) {
            url = urlsBean.getRaw();
        } else if (quality.equals(Constans.FULL)) {
            url = urlsBean.getFull();
        } else if (quality.equals(Constans.SMALL)) {
            url = urlsBean.getSmall();
        } else {
            url = urlsBean.getThumb();
        }
        return url;
    }

    private void setWallpaper(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
        intent.setDataAndType(uri, TYPE_JPG);
        intent.putExtra(MINE_TYPE, TYPE_JPG);
        startActivityForResult(intent, CODE_WALL_PAPER);
    }

    @Override
    protected void initData() {
        mLoadingView.smoothToShow();
        mPresenter.loadPhotoInfo(mId);
    }

    /*
    * start the activity
     */
    public static void start(Context context, String id, String imgUrl, int width, int height) {
        Intent intent = new Intent(context, PhotoInfoActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(IMG_URL, imgUrl);
        intent.putExtra(WIDTH, width);
        intent.putExtra(HEIGHT, height);
        context.startActivity(intent);
    }

    public static void start(Context context, Bundle bundle, String id, String imgUrl, int width, int height) {
        Intent intent = new Intent(context, PhotoInfoActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(IMG_URL, imgUrl);
        intent.putExtra(WIDTH, width);
        intent.putExtra(HEIGHT, height);
        context.startActivity(intent, bundle);
    }


    /*
    * reset size of imageview
     */
    private void resetSize() {
        ViewGroup.LayoutParams lp = mIvPhoto.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(this);
        lp.height = (lp.width * mImgHeight) / mImgWidth;
        mIvPhoto.setLayoutParams(lp);
    }


    @Override
    public void showPhotoInfo(PhotoBean info) {
        mPhotoBean = info;
        mLoadingView.smoothToHide();

        mIsLiked = info.isLiked_by_user();
        setLike(mIsLiked);

        mRecyclerView.setVisibility(View.VISIBLE);
        mObjects.add(info);
        List<PhotoBean.RelatedCollectionsBean.ResultsBean> collections = info.getRelated_collections().getResults();
        if (collections != null && collections.size() > 0) {
            int total = info.getRelated_collections().getTotal();
            mObjects.add(getString(R.string.featured_in,total));
            mObjects.addAll(collections);
        }
        List<TagBean> tags = info.getTags();
        if (tags != null && tags.size() > 0) {
            mObjects.add(getString(R.string.related_tags));
            mObjects.addAll(tags);
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
        if (mPresenter != null) {
            mPresenter.onUnsubscribe();
            mPresenter = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            if (mPhotoBean != null) {
                String content = "By " + mPhotoBean.getUser().getName() + " at " + mPhotoBean.getCreated_at()
                        + " " + mPhotoBean.getLinks().getHtml();
                ShareUtils.share(this, getString(R.string.APP_NAME), content);
            }
        } else if (item.getItemId() == R.id.action_link) {
            if (mPhotoBean != null) {
                String url = mPhotoBean.getLinks().getHtml() + Constans.UTM_PARAMS;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        } else if (item.getItemId() == android.R.id.home) {
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

    private void setLike(boolean like) {
        mIsLiked = like;
        if (like) {
            mFabLike.setImageResource(R.drawable.ic_like_red_24dp);
            mFabLike.setLabelText(getString(R.string.dislike));
        } else {
            mFabLike.setImageResource(R.drawable.ic_like_fill_white_24dp);
            mFabLike.setLabelText(getString(R.string.like));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CODE_WALL_PAPER && resultCode == RESULT_OK) {
            showSnackbar(getString(R.string.wallpaper_success));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showSnackbar(String msg) {
        Snackbar.make(mRlContent, msg, Snackbar.LENGTH_SHORT).show();
    }

}
