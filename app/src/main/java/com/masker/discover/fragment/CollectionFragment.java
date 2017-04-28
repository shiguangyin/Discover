package com.masker.discover.fragment;

import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseFragment;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: fragment collection
 */

public class CollectionFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initViews(View contentView) {

    }

    @Override
    protected void initData() {

    }

    public static CollectionFragment newInstance(){
        return new CollectionFragment();
    }
}
