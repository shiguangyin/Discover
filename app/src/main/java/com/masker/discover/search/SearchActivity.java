package com.masker.discover.search;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
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
import com.masker.discover.rx.RxBus;
import com.masker.discover.rx.event.SearchEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {
    public static final String SEARCH_KEY = "key";
    public static final int OFF_SCREEN_LIMIT = 3;
    public static final int TAB_COUNT = 3;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.vp_result)
    ViewPager mVpResult;
    SearchView mSearchView;

    private String mSearchKey;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mVpResult.setAdapter(new SearchVpAdapter(getSupportFragmentManager(), this));
        mVpResult.setOffscreenPageLimit(OFF_SCREEN_LIMIT);
        for (int i = 0; i < TAB_COUNT; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mVpResult);
    }

    @Override
    protected void initData() {

    }

    public void setTabTitle(int index, String title) {
        if (mTabLayout == null) {
            return;
        }
        TabLayout.Tab tab = mTabLayout.getTabAt(index);
        if (tab != null) {
            tab.setText(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
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
        ImageView searchViewIcon = (ImageView) mSearchView.findViewById(R.id.search_mag_icon);
        ViewGroup linearLayoutSearchView = (ViewGroup) searchViewIcon.getParent();
        linearLayoutSearchView.removeView(searchViewIcon);
        //underLine
        View underline = mSearchView.findViewById(R.id.search_plate);
        underline.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        if (!TextUtils.isEmpty(mSearchKey)) {
            mSearchView.setQuery(mSearchKey, true);
            mSearchView.clearFocus();
        }
        return super.onCreateOptionsMenu(menu);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, String key) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(SEARCH_KEY, key);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntent() {
        mSearchKey = getIntent().getStringExtra(SEARCH_KEY);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
