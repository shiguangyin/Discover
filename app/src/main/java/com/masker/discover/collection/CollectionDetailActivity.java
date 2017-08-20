package com.masker.discover.collection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.photo.PhotoListAdapter;
import com.masker.discover.utils.ImgLoader;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CollectionDetailActivity extends BaseMvpActivity
        implements CollectionDetailContract.View {

    public static final String ID = "id";
    public static final String CURATED = "curated";
    public static final String TOTAL = "total";
    public static final String TITLE = "title";
    public static final String DES = "des";
    public static final String USER_AVATAR = "user_avatar";
    public static final String BG_URL = "bg_url";
    public static final String USER_NAME = "user_name";

    public static final int START_PAGE = 1;
    public static final int PER_PAGE = 20;

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.rl_header)
    RelativeLayout mRlHeader;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tool_bar_layout)
    CollapsingToolbarLayout mToolBarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int mId;
    private boolean mIsCurated;
    private int mTotalCount;
    private String mTitle;
    private String mDes;
    private String mAvatarUrl;
    private String mUserName;
    private String mBgUrl;

    private PhotoListAdapter mPhotoAdapter;
    private List<PhotoListBean> mPhotos;
    private int mPage = START_PAGE;


    private CollectionDetailPresenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_detail;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoListAdapter(mPhotos, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mPhotoAdapter);

    }


    @Override
    protected void initListeners() {
        mPhotoAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                if (mIsCurated) {
                    mPresenter.loadCollectionPhotos(mId, mPage, PER_PAGE);
                } else {
                    mPresenter.loadCollectionPhotos(mId, mPage, PER_PAGE);
                }
            }
        });

        mPhotoAdapter.setOnLikeListener(new PhotoListAdapter.OnLikeListener() {
            @Override
            public void onLike(View view, int position) {
                onClickLike(position);
            }
        });
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        boolean collapsed = (mToolBarLayout.getHeight() + verticalOffset < mToolBarLayout.getScrimVisibleHeightTrigger());
                        if (collapsed) {
                            mToolBarLayout.setTitle(mTitle);
                        } else {
                            mToolBarLayout.setTitle("");
                        }
                    }
                });
            }
        });
    }


    private void onClickLike(int position) {
        if (UserManager.getInstance().isLogin()) {
            PhotoListBean photo = mPhotos.get(position);
            boolean isLike = photo.isLiked_by_user();
            mPhotoAdapter.notifyItemChanged(position, PhotoListAdapter.STATE_LOADING);
            if (isLike) {
                mPresenter.unlikePhoto(photo.getId());
            } else {
                mPresenter.likePhoto(photo.getId());
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    @Override
    protected void initData() {
        mTvTitle.setText(mTitle);
        mTvDesc.setText(mDes);
        ImgLoader.loadAvatar(this,mAvatarUrl,mIvAvatar,false);
        ImgLoader.loadBlurBackgroud(this,mBgUrl,mRlHeader);
        mTvFrom.setText(mUserName);

        if (mId != -1) {
            mLoadingView.smoothToShow();
            if (mIsCurated) {
                mPresenter.loadCuratedCollectionPhotos(mId, mPage, PER_PAGE);
            } else {
                mPresenter.loadCollectionPhotos(mId, mPage, PER_PAGE);
            }
        }
    }


    @Override
    public void showError() {
        mLoadingView.smoothToHide();
    }

    @Override
    public void showEmpty() {

    }


    @Override
    public void showCollectionPhotos(List<PhotoListBean> photos) {
        mLoadingView.smoothToHide();
        mRecyclerView.setVisibility(View.VISIBLE);
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
        if (mPhotos.size() == mTotalCount) {
            mPhotoAdapter.enableLoadMore(false);
        }
    }

    @Override
    protected void attach() {
        mPresenter = new CollectionDetailPresenter(this);
    }

    @Override
    protected void detach() {
        if (mPresenter != null) {
            mPresenter.onUnsubscribe();
            mPresenter = null;
        }
    }


    @Override
    protected void handleIntent() {
        Intent intent = getIntent();
        mId = intent.getIntExtra(ID, -1);
        mIsCurated = intent.getBooleanExtra(CURATED, false);
        mTotalCount = intent.getIntExtra(TOTAL, 0);
        mTitle = intent.getStringExtra(TITLE);
        mDes = intent.getStringExtra(DES);
        mAvatarUrl = intent.getStringExtra(USER_AVATAR);
        mUserName = intent.getStringExtra(USER_NAME);
        mBgUrl = intent.getStringExtra(BG_URL);

    }

    public static void start(Context context, int id, boolean curated, int total, String title,
                             String des, String avatarUrl, String userName, String bgUrl, Bundle bundle) {
        Intent intent = new Intent(context, CollectionDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(CURATED, curated);
        intent.putExtra(TOTAL, total);
        intent.putExtra(TITLE, title);
        intent.putExtra(DES,des);
        intent.putExtra(USER_AVATAR,avatarUrl);
        intent.putExtra(USER_NAME,userName);
        intent.putExtra(BG_URL,bgUrl);
        context.startActivity(intent,bundle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            //Snackbar.make(mRecyclerView,"under developing",Snackbar.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updatePhoto(LikeResponseBean bean) {
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean photo = mPhotos.get(i);
            if (photo.getId().equals(bean.getPhoto().getId())) {
                boolean like = bean.getPhoto().isLiked_by_user();
                photo.setLiked_by_user(like);
                photo.setLikes(bean.getPhoto().getLikes());
                mPhotoAdapter.notifyItemChanged(i, PhotoListAdapter.STATE_NORMAL);
                break;
            }
        }
    }

    @Override
    public void showLikeError(String message, String id) {
        //Snackbar.make(mRlContent,message,Snackbar.LENGTH_SHORT).show();
        //stop progress
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean bean = mPhotos.get(i);
            if (bean.getId().equals(id)) {
                mPhotoAdapter.notifyItemChanged(i, PhotoListAdapter.STATE_NORMAL);
            }
        }
    }

}
