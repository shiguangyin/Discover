package com.masker.discover.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.global.UserManager;
import com.masker.discover.home.HomeActivity;
import com.masker.discover.model.entity.User;
import com.masker.discover.model.entity.UserInfoBean;
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

public class UserInfoActivity extends BaseMvpActivity implements UserInfoContract.View,View.OnClickListener{
    public static final String KEY_USER = "user";
    public static final String KEY_USER_TYPE = "user_type";

    public static final int USER_SELF = 1;
    public static final int USER_OTHER = 2;

    private User mUser;
    private UserInfoPresenter mPresenter;

    private int mUserType = USER_SELF;
    private boolean isFollowedByUser;
    private int mFollowersCount;


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
    @BindView(R.id.btn_edit)
    Button mBtnEdit;
    @BindView(R.id.rl_focus)
    RelativeLayout mRlFocus;
    @BindView(R.id.pb_focus)
    ProgressBar mPbFocus;
    @BindView(R.id.tv_focus)
    TextView mTvFocus;
    @BindView(R.id.iv_focus)
    ImageView mIvFocus;
    @BindView(R.id.tv_followers)
    TextView mTvFollowers;
    @BindView(R.id.tv_following)
    TextView mTvFollowing;




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
        if(mUserType == USER_SELF){
            mBtnEdit.setVisibility(View.VISIBLE);
            mRlFocus.setVisibility(View.GONE);
        }
        else{
            mBtnEdit.setVisibility(View.GONE);
            mRlFocus.setVisibility(View.VISIBLE);
        }

        Logger.i(""+mUser.getTotalLikes()+"   "+mUser.getTotalCollections());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mViewPager.setAdapter(new UserVpAdapter(getSupportFragmentManager(),mUser));
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

        mRlFocus.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        if (mUser != null) {
            ImgLoader.loadAvator(this, mUser.getAvatorUrl(), mIvAvatar);
            ImgLoader.loadBlurBackgroud(this,mUser.getAvatorUrl(),mRlHeader);
            mTvName.setText(mUser.getName());
            if(TextUtils.isEmpty(mUser.getLocation())){
                mTvLocation.setText("Unknown");
            }
            else{
                mTvLocation.setText(mUser.getLocation());
            }

        }
        mLoadingView.smoothToShow();
        if(mUserType == USER_SELF){
            mPresenter.loadMyInfo();
        }
        else{
            mPresenter.loadUserInfo(mUser.getUserName());
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.item_logout:
                onLogOutClick();
                break;
        }

        return true;
    }

    private void onLogOutClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Are you sure to log out?")
                .setCancelable(true)
                .setPositiveButton("SURE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserManager.getInstance().logOut();
                        HomeActivity.reStart(UserInfoActivity.this);
                        finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_info,menu);
        if(mUserType == USER_OTHER){
            menu.findItem(R.id.item_logout).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void handleIntent() {
        mUser = getIntent().getParcelableExtra(KEY_USER);
        mUserType = getIntent().getIntExtra(KEY_USER_TYPE,USER_SELF);
    }

    public static void start(Context context, User user,int userType) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(KEY_USER, user);
        intent.putExtra(KEY_USER_TYPE,userType);
        context.startActivity(intent);
    }

    @Override
    public void showError() {
        mLoadingView.smoothToHide();
        mPbFocus.setVisibility(View.GONE);
        mIvFocus.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showMyInfo(UserInfoBean infoBean) {
        UserManager.getInstance().writeMyInfo(infoBean);
        show(infoBean);
    }

    @Override
    public void showUserInfo(UserInfoBean infoBean) {
        show(infoBean);
    }

    @Override
    public void showFollowStatusChange() {
        isFollowedByUser = !isFollowedByUser;
        mPbFocus.setVisibility(View.GONE);
        mIvFocus.setVisibility(View.VISIBLE);
        if(isFollowedByUser){
            mIvFocus.setImageResource(R.drawable.ic_done_white_24dp);
            mTvFocus.setText("FOLLOWED");
        }
        else{
            mIvFocus.setImageResource(R.drawable.ic_follow_white_24dp);
            mTvFocus.setText("FOLLOW");
        }
    }

    @Override
    protected void attach() {
        mPresenter =  new UserInfoPresenter(this);
    }

    @Override
    protected void detach() {
        mPresenter.onUnsubscribe();
    }

    private void show(UserInfoBean infoBean){
        mLoadingView.hide();
        Logger.i("is followed by user = "+infoBean.isFollowed_by_user());
        if(isFollowedByUser != infoBean.isFollowed_by_user()){
            showFollowStatusChange();
        }
        ImgLoader.loadAvator(this, infoBean.getProfile_image().getLarge(), mIvAvatar);
        ImgLoader.loadBlurBackgroud(this,infoBean.getProfile_image().getSmall(),mRlHeader);
        mTvName.setText(infoBean.getName());
        mTvLocation.setText(infoBean.getLocation());
        mTabLayout.getTabAt(0).setText(infoBean.getTotal_photos()+" PHOTOS");
        mTabLayout.getTabAt(1).setText(infoBean.getTotal_likes()+" LIKES");
        mTabLayout.getTabAt(2).setText(infoBean.getTotal_collections()+" COLLECTIONS");

        mFollowersCount = infoBean.getFollowers_count();
        mTvFollowers.setText("Followers "+infoBean.getFollowers_count());
        mTvFollowing.setText("Following "+infoBean.getFollowing_count());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_focus:
                if(mPbFocus.getVisibility() == View.VISIBLE){
                    return ;
                }
                mIvFocus.setVisibility(View.GONE);
                mPbFocus.setVisibility(View.VISIBLE);
                if(!isFollowedByUser){
                    mPresenter.followUser(mUser.getUserName());
                }
                else{
                    mPresenter.deleteFollow(mUser.getUserName());
                }
                break;
        }
    }
}
