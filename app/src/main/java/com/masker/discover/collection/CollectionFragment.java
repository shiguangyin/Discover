package com.masker.discover.collection;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.activity.HomeActivity;
import com.masker.discover.base.BaseFragment;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: collection fragment container
 */

public class CollectionFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CollectionFragmentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initViews(View contentView) {
        mTabLayout = ((HomeActivity)getActivity()).getTabLayout();
        if(mTabLayout != null){
            mTabLayout.setVisibility(View.VISIBLE);
        }
        mViewPager = getViewById(R.id.vp_collection);
        mAdapter = new CollectionFragmentAdapter(getChildFragmentManager(),getContext());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }

    public static CollectionFragment newInstance(){
        return new CollectionFragment();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(mTabLayout != null){
                mTabLayout.setVisibility(View.GONE);
            }
        }
        else{
            if(mTabLayout != null){
                mTabLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}
