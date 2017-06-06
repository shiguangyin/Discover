package com.masker.discover.search;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.model.api.SearchService;

public class SearchActivity extends BaseActivity {
    public static final String SEARCH_KEY = "key";

    private String mSearchKey;

    private SearchView mSearchView;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mVpResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mToolbar = find(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mVpResult = find(R.id.vp_result);
        mTabLayout = find(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_photo)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_collection)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_user)));

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search));

        //remove search icon
        ImageView searchViewIcon = (ImageView)mSearchView.findViewById(R.id.search_mag_icon);
        ViewGroup linearLayoutSearchView =(ViewGroup) searchViewIcon.getParent();
        linearLayoutSearchView.removeView(searchViewIcon);
        //underLine
        View underline =mSearchView.findViewById(R.id.search_plate);
        underline.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));

        return super.onCreateOptionsMenu(menu);
    }

    public static void start(Context context){
        Intent intent  = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context,String key){
        Intent intent = new Intent();
        intent.putExtra(SEARCH_KEY,key);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntent() {
        mSearchKey = getIntent().getStringExtra(SEARCH_KEY);
    }
}
