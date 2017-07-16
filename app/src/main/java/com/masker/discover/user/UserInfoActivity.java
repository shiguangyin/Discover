package com.masker.discover.user;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.UserManager;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.model.entity.MyInfoBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.utils.ImgLoader;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description: activity for user info
 */

public class UserInfoActivity extends BaseMvpActivity implements UserInfoContract.View{
    public static final String KEY_USER = "user";

    public static final int USER_SELF = 1;
    public static final int USER_OTHER = 2;

    private User mUser;
    private UserInfoPresenter mPresenter;


    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.rl_header)
    RelativeLayout mRlHeader;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(true);
        }
        Logger.i(""+mUser.getTotalLikes()+"   "+mUser.getTotalCollections());
        mTabLayout.addTab(mTabLayout.newTab().setText(mUser.getTotalPhotos()+" PHOTOS"));
        mTabLayout.addTab(mTabLayout.newTab().setText(mUser.getTotalLikes()+" LIKES"));
        mTabLayout.addTab(mTabLayout.newTab().setText(mUser.getTotalCollections()+" COLLECTIONS"));
        mViewPager.setAdapter(new UserVpAdapter(getSupportFragmentManager(),mUser.getUserName()));
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    protected void initListeners() {
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset <= ((-mRlHeader.getHeight()/3)*2)){
                    mCollapsingToolbarLayout.setTitle(mUser.getName());
                }
                else{
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });
    }

    @Override
    protected void initData() {
        if (mUser != null) {
            ImgLoader.loadAvator(this, mUser.getAvatorUrl(), mIvAvatar);
            ImgLoader.loadBlurBackgroud(this,mUser.getAvatorUrl(),mRlHeader);
            mTvName.setText(mUser.getName());
            mTvLocation.setText(mUser.getLocation());
        }
        mLoadingView.smoothToShow();
        mPresenter.loadMyInfo();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_info,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void handleIntent() {
        mUser = getIntent().getParcelableExtra(KEY_USER);
    }

    public static void start(Context context, User user) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(KEY_USER, user);
        context.startActivity(intent);
    }

    @Override
    public void showError() {
        mLoadingView.smoothToHide();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showMyInfo(MyInfoBean infoBean) {
        mLoadingView.smoothToHide();
        UserManager.getInstance().writeMyInfo(infoBean);
        ImgLoader.loadAvator(this, infoBean.getProfile_image().getLarge(), mIvAvatar);
        ImgLoader.loadBlurBackgroud(this,infoBean.getProfile_image().getSmall(),mRlHeader);
        mTvName.setText(infoBean.getName());
        mTvLocation.setText(infoBean.getLocation());
        mTabLayout.getTabAt(0).setText(infoBean.getTotal_photos()+" PHOTOS");
        mTabLayout.getTabAt(1).setText(infoBean.getTotal_likes()+" LIKES");
        mTabLayout.getTabAt(2).setText(infoBean.getTotal_collections()+" COLLECTIONS");

    }

    @Override
    protected void attach() {
        mPresenter =  new UserInfoPresenter(this);
    }

    @Override
    protected void detach() {
        mPresenter.onUnsubscribe();
    }
}
