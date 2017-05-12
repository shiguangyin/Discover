package com.masker.discover.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdpater;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.Photo;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public class PhotoFragment extends BaseFragment implements PhotoContract.View{

    private static final int START_PAGE = 1;


    private SwipeRefreshLayout mRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;
    private RecyclerView mRecyclerView;
    private List<Photo> mPhotos;
    private PhotoAdapter mPhotoAdapter;
    private int mPage = START_PAGE;
    private int mPerPage = 20;
    private String mOrder = PhotoService.LATEST;


    private PhotoContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void initViews(View contentView) {
        mRefreshLayout = getViewById(R.id.swipe_refresh_layout);
        mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                mPhotos.clear();
                mPresenter.loadPhotos(mPage,mPerPage,mOrder);
            }
        };
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        mRecyclerView = getViewById(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(mPhotos,R.layout.rv_item_photo,getContext());
        mPhotoAdapter.setLoadMoreListener(new BaseAdpater.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadPhotos(mPage,mPerPage,mOrder);
            }
        });
        mPhotoAdapter.setOnItemClickListener(new BaseAdpater.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Photo photo = mPhotos.get(position);
                String id = photo.getId();
                String imgUrl = photo.getUrls().getRegular();
                int width = photo.getWidth();
                int height = photo.getHeight();

                ImageView ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(getActivity(),ivPhoto,"photo");
                PhotoInfoActivity.start(getContext(),options.toBundle(),
                        id,imgUrl,width,height);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    protected void initData() {
        mPresenter = new PhotoPresenter(this);
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
    public void showPhotos(List<Photo> photos) {
        mRefreshLayout.setRefreshing(false);
        mPage ++;
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    public static PhotoFragment newInstance(){
        return new PhotoFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_latest:
                if(!mOrder.equals(PhotoService.LATEST)){
                    mOrder = PhotoService.LATEST;
                    initData();
                }
                break;
            case R.id.action_oldest:
                if(!mOrder.equals(PhotoService.OLDEST)){
                    mOrder = PhotoService.OLDEST;
                    initData();
                }
                break;
            case R.id.action_popular:
                if(!mOrder.equals(PhotoService.POPULAR)){
                    mOrder = PhotoService.POPULAR;
                    initData();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onUnsubscribe();
    }
}
