package com.masker.discover.search;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.rxbus.RxBus;
import com.masker.discover.rxbus.SearchEvent;

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
        mToolbar = bind(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mVpResult = bind(R.id.vp_result);
        mVpResult.setAdapter(new SearchVpAdapter(getSupportFragmentManager(),this));
        mVpResult.setOffscreenPageLimit(2);
        mTabLayout = bind(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setupWithViewPager(mVpResult);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query)){
                    RxBus.post(new SearchEvent(query));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //remove search icon
        ImageView searchViewIcon = (ImageView)mSearchView.findViewById(R.id.search_mag_icon);
        ViewGroup linearLayoutSearchView =(ViewGroup) searchViewIcon.getParent();
        linearLayoutSearchView.removeView(searchViewIcon);
        //underLine
        View underline =mSearchView.findViewById(R.id.search_plate);
        underline.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        if(!TextUtils.isEmpty(mSearchKey)){
            mSearchView.setQuery(mSearchKey,true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public static void start(Context context){
        Intent intent  = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, String key){
        Intent intent = new Intent(context,SearchActivity.class);
        intent.putExtra(SEARCH_KEY,key);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntent() {
        mSearchKey = getIntent().getStringExtra(SEARCH_KEY);
    }
}
