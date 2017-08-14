package com.masker.discover.downloads;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: masker
 * Date: 2017/8/11
 * Description:
 */

public class DownloadsActivity extends BaseActivity{

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_downloads;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getString(R.string.title_downloads));
        }
        mViewPager.setAdapter(new DownloadsPagerAdapter(getSupportFragmentManager(),this));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
