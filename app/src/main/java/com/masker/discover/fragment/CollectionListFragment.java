package com.masker.discover.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseFragment;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: the fragment to show collection list
 */

public class CollectionListFragment extends BaseFragment{

    public static final String TYPE = "type";

    public static final int ALL = 0;
    public static final int CURATED  = 1;
    public static final int FEATURED = 2;

    private int mType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(TYPE);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection_list;
    }

    @Override
    protected void initViews(View contentView) {

    }

    @Override
    protected void initData() {

    }

    public static CollectionListFragment newInstance(int type){
        CollectionListFragment fragment = new CollectionListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
