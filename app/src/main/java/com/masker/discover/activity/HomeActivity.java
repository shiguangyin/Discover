package com.masker.discover.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.fragment.CollectionFragment;
import com.masker.discover.fragment.HomeFragment;
import com.masker.discover.presenter.HomePresenter;

public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";

    private Toolbar mToolbar;
    private NavigationView mNavView;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;


    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_COLLECTION = 1;

    private int currentId = -1;

    private HomeFragment mHomeFragment;
    private CollectionFragment mCollectionFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        mToolbar = getViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mDrawer = getViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,mDrawer,mToolbar,
                R.string.open,R.string.close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavView = getViewById(R.id.nv_main);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:
                        switchFragment(FRAGMENT_HOME);
                        break;
                    case R.id.item_collections:
                        switchFragment(FRAGMENT_COLLECTION);
                        break;
                    default:
                        break;
                }
                mDrawer.closeDrawer(mNavView);
                return true;
            }
        });

        switchFragment(FRAGMENT_HOME);
    }

    public void switchFragment(int id){
        Log.i(TAG, "switchFragment: "+id);
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
                if(mHomeFragment == null){
                    mHomeFragment = HomeFragment.newInstance();
                    HomePresenter homePresenter = new HomePresenter(this,mHomeFragment);
                }
                return mHomeFragment;

            case FRAGMENT_COLLECTION:
                if(mCollectionFragment == null){
                    mCollectionFragment = CollectionFragment.newInstance();
                }
                return mCollectionFragment;
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


}
