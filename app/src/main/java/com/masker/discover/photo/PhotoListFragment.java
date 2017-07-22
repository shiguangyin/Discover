package com.masker.discover.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.rxbus.ReOrderEvent;
import com.masker.discover.rxbus.RxBus;
import com.masker.discover.user.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public class PhotoListFragment extends BaseFragment implements PhotoListContract.View{

    private static final int START_PAGE = 1;


    private RelativeLayout mRlContent;
    private SwipeRefreshLayout mRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;
    private RecyclerView mRecyclerView;
    private List<PhotoListBean> mPhotos;
    private PhotoListAdapter mPhotoAdapter;
    private int mPage = START_PAGE;
    private int mPerPage = 20;
    private String mOrder = PhotoService.LATEST;


    private PhotoListPresenter mPresenter;
    private Subscription mSubcription;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubcription = RxBus.toObservable(ReOrderEvent.class)
                .subscribe(new Action1<ReOrderEvent>() {
                    @Override
                    public void call(ReOrderEvent reOrderEvent) {
                        if(!mOrder.equals(reOrderEvent.getOrder())){
                            mOrder = reOrderEvent.getOrder();
                            initData();
                        }
                    }
                });
        //setHasOptionsMenu(true);
    }

    @Override
    protected void initViews(View contentView) {
        mRlContent = bind(R.id.rl_content);
        mRefreshLayout = bind(R.id.swipe_refresh_layout);
        mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                mPhotos.clear();
                mPresenter.loadPhotos(mPage,mPerPage,mOrder);
            }
        };
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        mRecyclerView = bind(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoListAdapter(mPhotos,getContext());
        mPhotoAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadPhotos(mPage,mPerPage,mOrder);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }


    @Override
    protected void initListeners() {
        mPhotoAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                PhotoListBean photo = mPhotos.get(position);
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

        mPhotoAdapter.setOnAvatarClickListener(new PhotoListAdapter.OnAvatarClickListener() {
            @Override
            public void onAvatarClick(View view, int position) {
            User user = UserManager.getInstance()
                  .transform(mPhotos.get(position).getUser());
                UserInfoActivity.start(getContext(),user,UserInfoActivity.USER_OTHER);

            }
        });

        mPhotoAdapter.setOnLikeListener(new PhotoListAdapter.OnLikeListener() {
            @Override
            public void onLike(View view, int position) {
                onClickLike(position);
            }
        });
    }

    /*
     * on like button clicked
     */
    private void onClickLike(int position){
       if(UserManager.getInstance().isLogin()){
           PhotoListBean photo = mPhotos.get(position);
           boolean isLike = photo.isLiked_by_user();
           mPhotoAdapter.notifyItemChanged(position,PhotoListAdapter.STATE_LOADING);
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


    @Override
    protected void initData() {
        mPresenter = new PhotoListPresenter(this);
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
    public void showPhotos(List<PhotoListBean> photos) {
        mRefreshLayout.setRefreshing(false);
        mPage ++;
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void updatePhoto(LikeResponseBean bean) {
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean photo = mPhotos.get(i);
            if(photo.getId().equals(bean.getPhoto().getId())){
                boolean like = bean.getPhoto().isLiked_by_user();
                photo.setLiked_by_user(like);
                photo.setLikes(bean.getPhoto().getLikes());
                mPhotoAdapter.notifyItemChanged(i,PhotoListAdapter.STATE_NORMAL);
                break;
            }
        }
    }

    @Override
    public void showLikeError(String message, String id) {
        Snackbar.make(mRlContent,message,Snackbar.LENGTH_SHORT).show();
        //stop progress
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean bean = mPhotos.get(i);
            if(bean.getId().equals(id)){
                mPhotoAdapter.notifyItemChanged(i,PhotoListAdapter.STATE_NORMAL);
            }
        }
    }


    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    public static PhotoListFragment newInstance(){
        return new PhotoListFragment();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onUnsubscribe();
        if(!mSubcription.isUnsubscribed()){
            mSubcription.unsubscribe();
        }
    }
}
