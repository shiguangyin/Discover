package com.masker.discover.search.fragment;



import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.masker.discover.base.BaseAdapter;
import com.masker.discover.collection.CollectionListAdapter;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.CollectionSearchBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchCollectionFragment extends BaseResultFragment{

    public static final int PER_PAGE = 10;

    private List<CollectionListBean> mCollections;
    private CollectionListAdapter mAdapter;


    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        mCollections = new ArrayList<>();
        mAdapter = new CollectionListAdapter(mCollections,getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchCollections(mKey,mPage,PER_PAGE,true);
                }
                else{
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
        mAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchCollections(mKey,mPage,PER_PAGE,false);
                }
            }
        });
    }

    @Override
    public void showLists(Object obj,boolean refresh) {
        CollectionSearchBean bean = (CollectionSearchBean) obj;
        mTotalCount = bean.getTotal();
        if(refresh){
            mCollections.clear();
        }
        mCollections.addAll(bean.getResults());
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
        mPage++;
        Logger.i(String.format("total = %d size = %d",mTotalCount,mCollections.size()));
        if(mCollections.size() >= mTotalCount){
            mAdapter.enableLoadMore(false);
        }
        else{
            mAdapter.enableLoadMore(true);
        }
    }

    @Override
    protected void startSearch(String key) {
        mKey = key;
        mPage = START_PAGE;
        mPresenter.searchCollections(key,mPage,PER_PAGE,true);
        showLoading();
    }


    public static SearchCollectionFragment newInstance(){
        return new SearchCollectionFragment();
    }
}
