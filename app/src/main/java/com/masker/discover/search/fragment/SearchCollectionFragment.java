package com.masker.discover.search.fragment;



import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.masker.discover.base.BaseAdpater;
import com.masker.discover.collection.CollectionListAdapter;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.CollectionSearchBean;

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
                mIsRefresh = true;
                mPage = START_PAGE;
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchCollections(mKey,mPage,PER_PAGE);
                }
                else{
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
        mAdapter.setLoadMoreListener(new BaseAdpater.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchPhotos(mKey,mPage,PER_PAGE);
                }
            }
        });
    }

    @Override
    public void showLists(Object obj) {
        CollectionSearchBean bean = (CollectionSearchBean) obj;
        mTotalCount = bean.getTotal();
        if(mIsRefresh){
            mCollections.clear();
            mRefreshLayout.setRefreshing(false);
        }
        mCollections.addAll(bean.getResults());
        mAdapter.notifyDataSetChanged();
        mPage++;
        if(mCollections.size() == mTotalCount){
            mAdapter.enableLoadMore(false);
        }
        else{
            mAdapter.enableLoadMore(true);
        }
    }

    @Override
    protected void startSearch(String key) {
        if(TextUtils.isEmpty(mKey) || !mKey.equals(key)){
            mIsRefresh = true;
            mPage = 1;
        }
        mKey = key;
        mPresenter.searchCollections(key,mPage,PER_PAGE);
        showLoading();
    }


    public static SearchCollectionFragment newInstance(){
        return new SearchCollectionFragment();
    }
}
