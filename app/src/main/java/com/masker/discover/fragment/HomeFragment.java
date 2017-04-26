package com.masker.discover.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.contract.HomeContract;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.Photo;

import java.util.List;

/**
 * Created by masker on 2017/4/26.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{

    private static final String TAG = "HomeFragment";

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private HomeContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View contentView) {
        mRefreshLayout = getViewById(R.id.swipe_refresh_layout);
        mRecyclerView = getViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        presenter.loadPhotos(1,10, PhotoService.POPULAR);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showPhotos(List<Photo> photos) {
        
    }

    @Override
    public void showError() {

    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

}
