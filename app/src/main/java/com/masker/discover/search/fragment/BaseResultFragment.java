package com.masker.discover.search.fragment;

import android.inputmethodservice.Keyboard;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;

import com.masker.discover.R;
import com.masker.discover.base.BaseMvpFragment;
import com.masker.discover.rxbus.RxBus;
import com.masker.discover.rxbus.SearchEvent;
import com.masker.discover.search.SearchContract;
import com.masker.discover.search.SearchPresenter;

import rx.Subscription;
import rx.functions.Action1;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public  abstract class BaseResultFragment extends BaseMvpFragment
        implements SearchContract.View{
    public static final int START_PAGE = 1;

    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected ProgressBar mProgressBar;

    protected SearchPresenter mPresenter;

    protected int mPage = START_PAGE;
    protected String mKey;
    protected boolean mIsRefresh;
    protected int mTotalCount;

    //rxbus
    protected Subscription mSubscription;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }

    @Override
    protected void initViews(View contentView) {
        mRefreshLayout = find(R.id.swipe_refresh_layout);
        mRecyclerView = find(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProgressBar = find(R.id.progress_bar);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void attach() {
        mPresenter = new SearchPresenter(this);
        mSubscription = RxBus.toObservable(SearchEvent.class)
                .subscribe(new Action1<SearchEvent>() {
                    @Override
                    public void call(SearchEvent searchEvent) {
                        startSearch(searchEvent.getKey());
                    }
                });
    }

    @Override
    protected void detach() {
        mPresenter.onUnsubscribe();
    }


    protected abstract void startSearch(String key);


    @Override
    public void showEmpty() {}

    @Override
    public void showError() {}

    @Override
    protected void initData() {}
}
