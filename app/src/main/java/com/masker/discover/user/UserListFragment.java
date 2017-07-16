package com.masker.discover.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseMvpFragment;
import com.masker.discover.collection.CollectionListAdapter;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.photo.PhotoListAdapter;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description: list fragment for userinfo activity,to show photos,likes,and collections
 */

public class UserListFragment extends BaseMvpFragment implements UserListContract.View{
    public static final int TYPE_PHOTOS = 1;
    public static final int TYPE_LIKES = 2;
    public static final int TYPE_COLLECTIONS = 3;
    public static final int PER_PAGE = 10;

    private int mType = TYPE_PHOTOS;
    private UserListPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;

    private List<PhotoListBean> mPhotos =  new ArrayList<>();
    private List<CollectionListBean> mCollections = new ArrayList<>();
    private PhotoListAdapter mPhotoAdapter;
    private CollectionListAdapter mCollectionAdapter;

    private String mUserName;
    private int mPage = 1;

    @Override
    protected void attach() {
        mPresenter = new UserListPresenter(this);
    }

    @Override
    protected void detach() {
        mPresenter.onUnsubscribe();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_list;
    }

    @Override
    protected void initViews(View contentView) {
        ButterKnife.bind(this,contentView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(mType == TYPE_PHOTOS || mType == TYPE_LIKES){
            mPhotoAdapter = new PhotoListAdapter(mPhotos,getContext());
            mRecyclerView.setAdapter(mPhotoAdapter);
        }
        else{
            mCollectionAdapter = new CollectionListAdapter(mCollections,getContext());
            mRecyclerView.setAdapter(mCollectionAdapter);
        }

    }


    @Override
    protected void initData() {
        mLoadingView.smoothToShow();
        if(TextUtils.isEmpty(mUserName)){
            showError();
            return ;
        }
        if(mType == TYPE_PHOTOS ){
            mPresenter.loadPhotos(mUserName,mPage,PER_PAGE);
        }
        else if(mType == TYPE_LIKES){
            mPresenter.loadLikedPhotos(mUserName,mPage,PER_PAGE);
        }
        else if(mType == TYPE_COLLECTIONS){
            mPresenter.loadLikedPhotos(mUserName,mPage,PER_PAGE);
        }
    }

    @Override
    public void showError() {
        Logger.e("error");
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showPhotos(List<PhotoListBean> photos) {
        mLoadingView.smoothToHide();
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLikedPhotos(List<PhotoListBean> photos) {
        mLoadingView.smoothToHide();
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollections(List<CollectionListBean> collections) {
        mLoadingView.smoothToHide();
        mCollections.addAll(collections);
        mCollectionAdapter.notifyDataSetChanged();
    }


    @Override
    protected void handleArgs(Bundle args) {
        mType = args.getInt("type",TYPE_PHOTOS);
        mUserName = args.getString("userName",null);
    }

    public static UserListFragment newInstance(int type,String userName){
        Bundle args = new Bundle();
        args.putInt("type",type);
        args.putString("userName",userName);
        UserListFragment fragment = new UserListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
