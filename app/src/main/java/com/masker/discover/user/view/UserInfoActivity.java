package com.masker.discover.user.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.activity.PhotoPreviewActivity;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.global.Constans;
import com.masker.discover.global.UserManager;
import com.masker.discover.home.HomeActivity;
import com.masker.discover.model.entity.User;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.user.UserVpAdapter;
import com.masker.discover.user.contract.UserInfoContract;
import com.masker.discover.user.presenter.UserInfoPresenter;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description: activity for user info
 */

public class UserInfoActivity extends BaseMvpActivity implements UserInfoContract.View{
    public static final String KEY_USER = "user";
    public static final String KEY_USER_TYPE = "user_type";

    public static final int USER_SELF = 1;
    public static final int USER_OTHER = 2;

    private User mUser;
    private UserInfoBean mUserInfoBean;
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
        mCollapsingToolbarLayout.setTitle(mUser.getName());
        if(mUserType == USER_SELF){
            mBtnEdit.setVisibility(View.VISIBLE);
            mRlFocus.setVisibility(View.GONE);
        }
        else{
            mBtnEdit.setVisibility(View.GONE);
            mRlFocus.setVisibility(View.VISIBLE);
        }

        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mViewPager.setAdapter(new UserVpAdapter(getSupportFragmentManager(),mUser));
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        boolean collapsed = (mCollapsingToolbarLayout.getHeight() + verticalOffset < mCollapsingToolbarLayout.getScrimVisibleHeightTrigger());
                        if (collapsed) {
                            mCollapsingToolbarLayout.setTitle(mUser.getName());
                        } else {
                            mCollapsingToolbarLayout.setTitle("");
                        }
                    }
                });
            }
        });

    }


    @OnClick(R.id.rl_focus)
    void focus(){
        if(UserManager.getInstance().isLogin()){
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
        }
        else{
            startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));
        }
    }

    @OnClick(R.id.btn_edit)
    void editInfo(){
        EditProfileActivity.start(this,mUserInfoBean);
    }

    @OnClick(R.id.iv_avatar)
    void preview(){
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,mIvAvatar, Constans.TRANSITION_AVATAR);
        PhotoPreviewActivity.start(this,mUser.getAvatarUrl(),options.toBundle(),PhotoPreviewActivity.TYPE_AVATAR);
    }


    @Override
    protected void initData() {
        if (mUser != null) {
            ImgLoader.loadAvatar(this, mUser.getAvatarUrl(), mIvAvatar,false);
            ImgLoader.loadBlurBackgroud(this,mUser.getBgUrl(),mAppBar);
            if(TextUtils.isEmpty(mUser.getLocation())){
                mTvLocation.setText(getString(R.string.unknown));
            }
            else{
                mTvLocation.setText(mUser.getLocation());
            }
            mTvName.setText(mUser.getName());
        }
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
            case R.id.item_share:
                if(mUserInfoBean != null){
                    ShareUtils.share(this,getString(R.string.APP_NAME),mUserInfoBean.getLinks().getHtml());
                }
                break;
        }

        return true;
    }

    private void onLogOutClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.sure_to_logout))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserManager.getInstance().logOut();
                        HomeActivity.reStart(UserInfoActivity.this);
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
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


    @Override
    public void showError() {
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
            mTvFocus.setText(getString(R.string.followed));
        }
        else{
            mIvFocus.setImageResource(R.drawable.ic_follow_white_24dp);
            mTvFocus.setText(getString(R.string.follow));
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
        mUserInfoBean = infoBean;
        if(isFollowedByUser != infoBean.isFollowed_by_user()){
            showFollowStatusChange();
        }
        if(TextUtils.isEmpty(infoBean.getLocation())){
           mTvLocation.setText(getString(R.string.unknown));
        }
        else{
            mTvLocation.setText(infoBean.getLocation());
        }
        mTabLayout.getTabAt(0).setText(getString(R.string.photos_count,infoBean.getTotal_photos()));
        mTabLayout.getTabAt(1).setText(getString(R.string.likes_count,infoBean.getTotal_likes()));
        mTabLayout.getTabAt(2).setText(getString(R.string.collections_count,infoBean.getTotal_collections()));
        mFollowersCount = infoBean.getFollowers_count();
        mTvFollowers.setText(getString(R.string.followers_count,infoBean.getFollowers_count()));
        mTvFollowing.setText(getString(R.string.following_count,infoBean.getFollowing_count()));
    }



    private void update(){
        mCollapsingToolbarLayout.setTitle(mUserInfoBean.getName());
        if(TextUtils.isEmpty(mUserInfoBean.getLocation())){
            mTvLocation.setText(getString(R.string.unknown));
        }
        else{
            mTvLocation.setText(mUserInfoBean.getLocation());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == EditProfileActivity.CODE){
            if(resultCode == RESULT_OK){
                UserInfoBean bean = data.getParcelableExtra(EditProfileActivity.KEY_USER);
                if(bean != null){
                    mUserInfoBean = bean;
                    update();
                }
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static void start(Context context, User user, int userType) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(KEY_USER, user);
        intent.putExtra(KEY_USER_TYPE,userType);
        context.startActivity(intent);
    }

    public static void start(Context context, User user, int userType, Bundle bundle){
        Intent intent = new Intent(context,UserInfoActivity.class);
        intent.putExtra(KEY_USER,user);
        intent.putExtra(KEY_USER_TYPE,userType);
        context.startActivity(intent,bundle);
    }
}
