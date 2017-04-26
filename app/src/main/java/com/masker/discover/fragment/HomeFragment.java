package com.masker.discover.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.adapter.PhotoAdapter;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.contract.HomeContract;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masker on 2017/4/26.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{

    private static final String TAG = "HomeFragment";

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<Photo> mPhotos;
    private PhotoAdapter mPhotoAdapter;


    private HomeContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View contentView) {
        mRefreshLayout = getViewById(R.id.swipe_refresh_layout);
        mRecyclerView = getViewById(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(mPhotos,R.layout.rv_item_home,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    protected void initData() {
        presenter.loadPhotos(1,30, PhotoService.POPULAR);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showPhotos(List<Photo> photos) {
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
