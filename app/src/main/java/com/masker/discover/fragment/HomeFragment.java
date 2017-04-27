package com.masker.discover.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.adapter.PhotoAdapter;
import com.masker.discover.base.BaseAdpater;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.contract.HomeContract;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by masker on 2017/4/26.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{

    private static final String TAG = "HomeFragment";
    private static final int START_PAGE = 1;

    private SwipeRefreshLayout mRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;
    private RecyclerView mRecyclerView;
    private List<Photo> mPhotos;
    private PhotoAdapter mPhotoAdapter;
    private int mPage = START_PAGE;


    private HomeContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View contentView) {
        mRefreshLayout = getViewById(R.id.swipe_refresh_layout);
        mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                mPhotos.clear();
                presenter.loadPhotos(mPage,20,PhotoService.LATEST);
            }
        };
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        mRecyclerView = getViewById(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(mPhotos,R.layout.rv_item_home,getContext());
        mPhotoAdapter.setLoadMoreListener(new BaseAdpater.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadPhotos(mPage,20,PhotoService.LATEST);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    protected void initData() {
        // auto refresh
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
        mRefreshListener.onRefresh();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showPhotos(List<Photo> photos) {
        mRefreshLayout.setRefreshing(false);
        mPage ++;
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

}
