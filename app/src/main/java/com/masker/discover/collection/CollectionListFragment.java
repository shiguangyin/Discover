package com.masker.discover.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseMvpFragment;
import com.masker.discover.model.entity.CollectionListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: the fragment to show collection list
 */

public class CollectionListFragment extends BaseMvpFragment
        implements CollectionListContract.View{

    public static final String TYPE = "type";

    //load const value
    public static final int START_PAGE = 1;
    public static final int PER_PAGE = 20;

    public static final int ALL = 0;
    public static final int CURATED  = 1;
    public static final int FEATURED = 2;

    private int mType;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener mListener;
    private CollectionListAdapter mAdapter;
    private List<CollectionListBean> mCollections;
    private RecyclerView.RecycledViewPool mRecycledViewPool;

    private CollectionListPresenter mPresenter;
    private int mPage = START_PAGE;

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
        mRefreshLayout = bind(R.id.swipe_refresh_layout);
        mListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                mCollections.clear();
                mPresenter.loadCollections(mPage,PER_PAGE,mType);
            }
        };
        mRefreshLayout.setOnRefreshListener(mListener);
        mRecyclerView = bind(R.id.recycler_view);
        mCollections = new ArrayList<>();
        mAdapter = new CollectionListAdapter(mCollections,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setRecycledViewPool(mRecycledViewPool);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListeners() {
        mAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                mPresenter.loadCollections(mPage,PER_PAGE,mType);
            }
        });
    }


    @Override
    protected void initData() {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
        mListener.onRefresh();
    }


    public static CollectionListFragment newInstance(int type, RecyclerView.RecycledViewPool pool){
        CollectionListFragment fragment = new CollectionListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE,type);
        fragment.setArguments(bundle);
        fragment.mRecycledViewPool = pool;
        return fragment;
    }


    @Override
    public void showCollections(List<CollectionListBean> collections) {
        mRefreshLayout.setRefreshing(false);
        mCollections.addAll(collections);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    protected void attach() {
        mPresenter = new CollectionListPresenter(this);
    }

    @Override
    protected void detach() {
        if(mPresenter != null){
            mPresenter.onUnsubscribe();
        }
    }
}
