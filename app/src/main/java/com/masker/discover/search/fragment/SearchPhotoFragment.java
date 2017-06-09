package com.masker.discover.search.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.masker.discover.base.BaseAdpater;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.PhotoSearchBean;
import com.masker.discover.photo.PhotoListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchPhotoFragment extends BaseResultFragment{

    public static final int PER_PAGE = 10;

    private List<PhotoListBean> mPhotos;
    private PhotoListAdapter mAdapter;


    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        mPhotos = new ArrayList<>();
        mAdapter = new PhotoListAdapter(mPhotos,getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                mPage = START_PAGE;
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchPhotos(mKey,mPage,PER_PAGE);
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
    protected void startSearch(String key) {
        if(TextUtils.isEmpty(mKey) || !mKey.equals(key)){
            mIsRefresh = true;
            mPage = 1;
        }
        mKey = key;
        mPresenter.searchPhotos(key,mPage,PER_PAGE);
        showLoading();
    }

    @Override
    public void showLists(Object obj) {
        PhotoSearchBean bean = (PhotoSearchBean) obj;
        mTotalCount = bean.getTotal();
        if(mIsRefresh){
            mPhotos.clear();
            mRefreshLayout.setRefreshing(false);
        }
        mPhotos.addAll(bean.getResults());
        mAdapter.notifyDataSetChanged();
        mPage++;
        if(mPhotos.size() == mTotalCount){
            mAdapter.enableLoadMore(false);
        }
        else{
            mAdapter.enableLoadMore(true);
        }
    }

    public static SearchPhotoFragment newInstance(){
        return new SearchPhotoFragment();
    }

}
