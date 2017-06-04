package com.masker.discover.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.collection.CollectionFragment;
import com.masker.discover.model.UserManager;
import com.masker.discover.model.entity.User;
import com.masker.discover.tag.TagListFragment;
import com.masker.discover.photo.PhotoListFragment;
import com.orhanobut.logger.Logger;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends BaseMvpActivity implements HomeContract.View{


    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_COLLECTION = 1;
    public static final int FRAGMENT_TAG = 2;

    private int currentId = -1;

    private Toolbar mToolbar;
    private AppBarLayout mAppBar;
    private TabLayout mTabLayout;
    private NavigationView mNavView;
    private View mNavHeader;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    //header view
    private CircleImageView mIvAvatar;
    private TextView mTvLogin;
    private TextView mTvBio;
    private TextView mTvName;
    private TextView mTvEmail;


    private PhotoListFragment mPhotoFragment;
    private CollectionFragment mCollectionFragment;
    private TagListFragment mTagFragment;

    private HomeContract.Presenter mPrensenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        mToolbar = $(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mAppBar = $(R.id.app_bar);
        mTabLayout = $(R.id.tab_layout);
        mDrawer = $(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,mDrawer,mToolbar,
                R.string.open,R.string.close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavView = $(R.id.nv_main);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:
                        switchFragment(FRAGMENT_HOME);
                        setTitle(R.string.item_home);
                        break;
                    case R.id.item_collections:
                        switchFragment(FRAGMENT_COLLECTION);
                        setTitle(R.string.item_collections);
                        break;
                    case R.id.item_tags:
                        switchFragment(FRAGMENT_TAG);
                        setTitle(R.string.item_tags);
                        break;
                    default:
                        break;
                }
                mDrawer.closeDrawer(mNavView);
                return true;
            }
        });
        mNavHeader = mNavView.getHeaderView(0);
        mTvLogin = (TextView) mNavHeader.findViewById(R.id.tv_login);
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeActivity(HomeActivity.this,LoginActivity.class);
            }
        });
        mTvBio = (TextView) mNavHeader.findViewById(R.id.tv_bio);
        mTvName = (TextView) mNavHeader.findViewById(R.id.tv_name);
        mTvEmail = (TextView) mNavHeader.findViewById(R.id.tv_email);
        mIvAvatar = (CircleImageView) mNavHeader.findViewById(R.id.iv_avatar);
        switchFragment(FRAGMENT_HOME);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initDatas() {
        if(UserManager.getInstance().isLogin()){
            Logger.i("get my info");
            User user = UserManager.getInstance().getUser();
            if(user != null){
                updateMyInfo(user);
            }
            mPrensenter.getMyInfo();
        }
    }

    public void switchFragment(int id){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(currentId != id){
            Fragment fragment = getFragment(id);
            if( fragment != null && !fragment.isAdded()){
                ft.add(R.id.layout_content,fragment,fragment.getTag());
            }
            Fragment curFragment = getFragment(currentId);
            if(curFragment != null){
                ft.hide(curFragment);
            }
            ft.show(fragment);
            currentId = id;
        }
        ft.commit();
    }

    private Fragment getFragment(int id){
        switch (id){
            case FRAGMENT_HOME:
                if(mPhotoFragment == null){
                    mPhotoFragment = PhotoListFragment.newInstance();
                }
                return mPhotoFragment;

            case FRAGMENT_COLLECTION:
                if(mCollectionFragment == null){
                    mCollectionFragment = CollectionFragment.newInstance();
                }
                return mCollectionFragment;

            case FRAGMENT_TAG:
                if(mTagFragment == null){
                    mTagFragment = TagListFragment.newInstance();
                }
                return mTagFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    /*
     * set title for toolbar
     */
    public void setTitle(String title){
        mToolbar.setTitle(title);
    }

    /*
     * get TabLayout for collection fragment
     */
    public TabLayout getTabLayout(){
        return mTabLayout;
    }



    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }




    @Override
    protected void attach() {
        mPrensenter = new HomePresenter(this);
    }

    @Override
    protected void detach() {
        if(mPrensenter != null){
            mPrensenter.onUnsubscribe();
            mPrensenter = null;
        }
    }


    @Override
    public void updateMyInfo(User user) {
        mTvLogin.setVisibility(View.GONE);

        mTvName.setVisibility(View.VISIBLE);
        mTvName.setText(user.getUserName());

        mTvEmail.setVisibility(View.VISIBLE);
        mTvEmail.setText(user.getEmail());

        mTvBio.setVisibility(View.VISIBLE);
        mTvBio.setText(user.getBio());

        Glide.with(this).load(user.getAvatorUrl()).dontAnimate()
                .into(mIvAvatar);
    }


}
