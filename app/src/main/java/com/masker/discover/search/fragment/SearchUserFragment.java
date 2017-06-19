package com.masker.discover.search.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.masker.discover.base.BaseAdapter;
import com.masker.discover.model.entity.UserListBean;
import com.masker.discover.model.entity.UserSearchBean;
import com.masker.discover.search.UserListAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchUserFragment extends BaseResultFragment{
    public static final int PER_PAGE = 20;

    private List<UserListBean> mUsers;
    private UserListAdapter mAdapter;


    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);

        mUsers = new ArrayList<>();
        mAdapter = new UserListAdapter(mUsers,getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchUsers(mKey,mPage,PER_PAGE);
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
                    mIsLoadMore = true;
                    mPresenter.searchUsers(mKey,mPage,PER_PAGE);
                }
            }
        });

    }

    @Override
    public void showLists(Object obj) {
        UserSearchBean bean = (UserSearchBean) obj;
        mTotalCount = bean.getTotal();
        if(mIsLoadMore){
            mUsers.addAll(bean.getResults());
            mIsLoadMore = false;
            Logger.i("user load more size = "+bean.getResults().size());
        }
        else{
            mUsers.clear();
            mUsers.addAll(bean.getResults());
            Logger.i("user refresh size ");
        }
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
        mPage++;
        Logger.i(String.format("total users = %d,musers.size = %d",mTotalCount,mUsers.size()));
        if(mUsers.size() == mTotalCount){
            mAdapter.enableLoadMore(false);
        }
        else{
            mAdapter.enableLoadMore(true);
        }
    }

    @Override
    protected void startSearch(String key) {
        mKey = key;
        mPresenter.searchUsers(key,mPage,PER_PAGE);
        showLoading();
    }


    public static SearchUserFragment newInstance(){
        return new SearchUserFragment();
    }
}
