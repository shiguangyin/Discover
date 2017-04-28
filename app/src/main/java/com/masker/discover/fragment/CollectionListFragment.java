package com.masker.discover.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.adapter.CollectionAdapter;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.contract.CollectionContract;
import com.masker.discover.model.entity.Collection;
import com.masker.discover.presenter.CollectionPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: the fragment to show collection list
 */

public class CollectionListFragment extends BaseFragment
        implements CollectionContract.View{

    public static final String TYPE = "type";

    public static final int ALL = 0;
    public static final int CURATED  = 1;
    public static final int FEATURED = 2;

    private int mType;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private CollectionAdapter mAdapter;
    private List<Collection> mCollections;

    private CollectionContract.Presenter mPresenter;

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
        mRefreshLayout = getViewById(R.id.swipe_refresh_layout);
        mRecyclerView = getViewById(R.id.recycler_view);
        mCollections = new ArrayList<>();
        mAdapter = new CollectionAdapter(mCollections,R.layout.rv_item_collection,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.loadCollections(1,20,mType);
    }

    public static CollectionListFragment newInstance(int type){
        CollectionListFragment fragment = new CollectionListFragment();
        CollectionPresenter presenter = new CollectionPresenter(fragment);
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setPresenter(CollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCollections(List<Collection> collections) {
        mCollections.addAll(collections);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }
}
