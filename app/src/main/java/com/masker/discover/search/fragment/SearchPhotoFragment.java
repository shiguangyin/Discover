package com.masker.discover.search.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.masker.discover.activity.LoginActivity;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.LikeResponseBean;
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
    public static final int INDEX = 0;

    private List<PhotoListBean> mPhotos;
    private PhotoListAdapter mAdapter;


    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        mPhotos = new ArrayList<>();
        mAdapter = new PhotoListAdapter(mPhotos,getContext());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initListeners() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                if(!TextUtils.isEmpty(mKey)){
                    mPresenter.searchPhotos(mKey,mPage,PER_PAGE,true);
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
                    mPresenter.searchPhotos(mKey,mPage,PER_PAGE,false);
                }
            }
        });
        mAdapter.setOnLikeListener(new PhotoListAdapter.OnLikeListener() {
            @Override
            public void onLike(View view, int position) {
                if(UserManager.getInstance().isLogin()){
                    PhotoListBean photo = mPhotos.get(position);
                    boolean isLike = photo.isLiked_by_user();
                    mAdapter.notifyItemChanged(position,PhotoListAdapter.STATE_LOADING);
                    if(isLike){
                        mPresenter.unlikePhoto(photo.getId());
                    }
                    else{
                        mPresenter.likePhoto(photo.getId());
                    }
                }
                else{
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
            }
        });
    }

    @Override
    protected void startSearch(String key) {
        mKey = key;
        mPage = START_PAGE;
        mPresenter.searchPhotos(key,mPage,PER_PAGE,true);
        showLoading();
    }

    @Override
    public void showList(Object obj, boolean refresh) {
        PhotoSearchBean bean = (PhotoSearchBean) obj;
        mTotalCount = bean.getTotal();
        if(refresh){
            mPhotos.clear();
        }
        mPhotos.addAll(bean.getResults());
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
        mPage++;
        if(mPhotos.size() == mTotalCount){
            mAdapter.enableLoadMore(false);
        }
        else{
            mAdapter.enableLoadMore(true);
        }
    }

    @Override
    public void updatePhoto(LikeResponseBean bean) {
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean photo = mPhotos.get(i);
            if(photo.getId().equals(bean.getPhoto().getId())){
                boolean like = bean.getPhoto().isLiked_by_user();
                photo.setLiked_by_user(like);
                photo.setLikes(bean.getPhoto().getLikes());
                mAdapter.notifyItemChanged(i,PhotoListAdapter.STATE_NORMAL);
                break;
            }
        }
    }

    @Override
    public void showLikeError(String message, String id) {
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean bean = mPhotos.get(i);
            if(bean.getId().equals(id)){
                mAdapter.notifyItemChanged(i,PhotoListAdapter.STATE_NORMAL);
            }
        }
    }

    public static SearchPhotoFragment newInstance(){
        return new SearchPhotoFragment();
    }

}
