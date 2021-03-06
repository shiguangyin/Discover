package com.masker.discover.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.activity.AboutActivity;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.activity.SettingActivity;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.collection.CollectionFragment;
import com.masker.discover.downloads.DownloadsActivity;
import com.masker.discover.global.Constans;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.User;
import com.masker.discover.photo.PhotoListFragment;
import com.masker.discover.rx.RxBus;
import com.masker.discover.rx.event.ProfileChangeEvent;
import com.masker.discover.rx.event.ReOrderEvent;
import com.masker.discover.search.SearchActivity;
import com.masker.discover.tag.TagListFragment;
import com.masker.discover.user.view.UserInfoActivity;
import com.orhanobut.logger.Logger;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class HomeActivity extends BaseMvpActivity implements HomeContract.View,View.OnClickListener{


    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_COLLECTION = 1;
    public static final int FRAGMENT_TAG = 2;
    public static final int TIME_INTERVAL = 2500;

    private int currentId = -1;

    private Toolbar mToolbar;
    private AppBarLayout mAppBar;
    private TabLayout mTabLayout;
    private NavigationView mNavView;
    private Menu mNavMenu;
    private View mNavHeader;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    //header view
    private CircleImageView mIvAvatar;
    private TextView mTvBio;
    private TextView mTvName;
    private TextView mTvEmail;
    private RelativeLayout mRlUnLogin;
    private Button mBtnLogin;
    private FrameLayout mLayoutContent;


    private PhotoListFragment mPhotoFragment;
    private CollectionFragment mCollectionFragment;
    private TagListFragment mTagFragment;

    private HomePresenter mPrensenter;
    private User mUser;

    private CompositeSubscription mSubscriptions =  new CompositeSubscription();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Subscription subscription = RxBus.toObservable(ProfileChangeEvent.class)
                .subscribe(new Action1<ProfileChangeEvent>() {
                    @Override
                    public void call(ProfileChangeEvent profileChangeEvent) {
                        Logger.i("onEvent");
                        mTvBio.setText(profileChangeEvent.getInfo().getBio());
                        mTvName.setText(profileChangeEvent.getInfo().getName());
                        mTvEmail.setText(profileChangeEvent.getInfo().getEmail());
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    protected void initViews() {
        mToolbar = bind(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mAppBar = bind(R.id.app_bar);
        mTabLayout = bind(R.id.tab_layout);
        mDrawer = bind(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,mDrawer,mToolbar,
                R.string.open,R.string.close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavView = bind(R.id.nv_main);
        mNavMenu = mNavView.getMenu();
        mLayoutContent = bind(R.id.layout_content);
        mNavHeader = mNavView.getHeaderView(0);
        mTvBio = (TextView) mNavHeader.findViewById(R.id.tv_bio);
        mTvName = (TextView) mNavHeader.findViewById(R.id.tv_name);
        mTvEmail = (TextView) mNavHeader.findViewById(R.id.tv_email);
        mIvAvatar = (CircleImageView) mNavHeader.findViewById(R.id.iv_avatar);
        mRlUnLogin = (RelativeLayout) mNavHeader.findViewById(R.id.rl_unlogin);
        mBtnLogin = (Button) mNavHeader.findViewById(R.id.btn_login);
        if(UserManager.getInstance().isLogin()){
            mRlUnLogin.setVisibility(View.GONE);
        }
        switchFragment(FRAGMENT_HOME);
    }

    @Override
    protected void initListeners() {
        mIvAvatar.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
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
                    case R.id.item_downloads:
                        startActivity(new Intent(HomeActivity.this, DownloadsActivity.class));
                        break;
                    case R.id.item_settings:
                        startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                        break;
                    case R.id.item_about:
                        startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                        break;
                    default:
                        break;
                }
                mDrawer.closeDrawer(mNavView);
                invalidateOptionsMenu();
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        if(UserManager.getInstance().isLogin()){
            mUser = UserManager.getInstance().getUser();
            if(mUser != null){
                updateMyInfo(mUser);
            }
            mPrensenter.getMyInfo();
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (currentId){
            case FRAGMENT_HOME:
                menu.findItem(R.id.action_reorder).setVisible(true);
                break;
            case FRAGMENT_COLLECTION:
                menu.findItem(R.id.action_reorder).setVisible(false);
                break;
            case FRAGMENT_TAG:
                menu.findItem(R.id.action_reorder).setVisible(false);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_latest:
                RxBus.post(new ReOrderEvent(PhotoService.LATEST));
                break;
            case R.id.action_oldest:
                RxBus.post(new ReOrderEvent(PhotoService.OLDEST));
                break;
            case R.id.action_popular:
                RxBus.post(new ReOrderEvent(PhotoService.POPULAR));
                break;
            case R.id.action_search:
                SearchActivity.start(this);
                break;
        }
        return super.onOptionsItemSelected(item);
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
        mRlUnLogin.setVisibility(View.GONE);

        mTvName.setVisibility(View.VISIBLE);
        mTvName.setText(user.getName());

        mTvEmail.setVisibility(View.VISIBLE);
        mTvEmail.setText(user.getEmail());

        mTvBio.setVisibility(View.VISIBLE);
        mTvBio.setText(user.getBio());

        mIvAvatar.setVisibility(View.VISIBLE);
        Glide.with(this).load(user.getAvatarUrl()).dontAnimate()
                .into(mIvAvatar);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                invokeActivity(this, LoginActivity.class);
                break;
            case R.id.iv_avatar:
                if(mUser != null){
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(HomeActivity.this,mIvAvatar, Constans.TRANSITION_AVATAR);
                    UserInfoActivity.start(this,mUser,UserInfoActivity.USER_SELF,options.toBundle());
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }

    public static void reStart(Context context){
        Intent intent = new Intent(context,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(mDrawer.isDrawerOpen(mNavView)){
            mDrawer.closeDrawer(mNavView);
        }
        else{
            finish();
        }
    }
}
