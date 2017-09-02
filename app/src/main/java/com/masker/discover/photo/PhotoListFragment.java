package com.masker.discover.photo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseMvpFragment;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.rx.RxBus;
import com.masker.discover.rx.event.LikeEvent;
import com.masker.discover.rx.event.ReOrderEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public class PhotoListFragment extends BaseMvpFragment implements PhotoListContract.View {

    private static final int START_PAGE = 1;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
    Unbinder unbinder;


    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;
    private List<PhotoListBean> mPhotos;
    private PhotoListAdapter mPhotoAdapter;
    private int mPage = START_PAGE;
    private int mPerPage = 20;
    private String mOrder = PhotoService.LATEST;


    private PhotoListPresenter mPresenter;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo;
    }


    @Override
    protected void initViews(View contentView) {
        unbinder = ButterKnife.bind(this,mContentView);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoListAdapter(mPhotos, getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }


    @Override
    protected void initListeners() {
        mPhotoAdapter.setOnLikeListener(new PhotoListAdapter.OnLikeListener() {
            @Override
            public void onLike(View view, int position) {
                onClickLike(position);
            }
        });
        mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = START_PAGE;
                mPresenter.loadPhotos(mPage, mPerPage, mOrder);
            }
        };
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        mPhotoAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadPhotos(mPage, mPerPage, mOrder);
            }
        });
    }

    @Override
    protected void attach() {
        mPresenter = new PhotoListPresenter(this);
        Subscription reorderSubscription = RxBus.toObservable(ReOrderEvent.class)
                .subscribe(new Action1<ReOrderEvent>() {
                    @Override
                    public void call(ReOrderEvent reOrderEvent) {
                        if (!mOrder.equals(reOrderEvent.getOrder())) {
                            mOrder = reOrderEvent.getOrder();
                            initData();
                        }
                    }
                });
        mSubscriptions.add(reorderSubscription);
        Subscription likeSubscription = RxBus.toObservable(LikeEvent.class)
                .subscribe(new Action1<LikeEvent>() {
                    @Override
                    public void call(LikeEvent likeEvent) {
                        if (likeEvent.getLikeResponse() != null) {
                            updatePhoto(likeEvent.getLikeResponse());
                        }
                    }
                });
        mSubscriptions.add(likeSubscription);
    }

    @Override
    protected void detach() {
        mPresenter.onUnsubscribe();
        mSubscriptions.clear();
        unbinder.unbind();
    }


    private void onClickLike(int position) {
        if (UserManager.getInstance().isLogin()) {
            PhotoListBean photo = mPhotos.get(position);
            boolean isLike = photo.isLiked_by_user();
            mPhotoAdapter.notifyItemChanged(position, PhotoListAdapter.STATE_LOADING);
            if (isLike) {
                mPresenter.unlikePhoto(photo.getId());
            } else {
                mPresenter.likePhoto(photo.getId());
            }
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }


    @Override
    protected void initData() {
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
        if (mPage == START_PAGE) {
            mPhotos.clear();
        }
        mPhotos.addAll(photos);
        mPage++;
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void updatePhoto(LikeResponseBean bean) {
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean photo = mPhotos.get(i);
            if (photo.getId().equals(bean.getPhoto().getId())) {
                boolean like = bean.getPhoto().isLiked_by_user();
                photo.setLiked_by_user(like);
                photo.setLikes(bean.getPhoto().getLikes());
                mPhotoAdapter.notifyItemChanged(i, PhotoListAdapter.STATE_NORMAL);
                break;
            }
        }
    }


    @Override
    public void showLikeError(String message, String id) {
        Snackbar.make(mRlContent, message, Snackbar.LENGTH_SHORT).show();
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean bean = mPhotos.get(i);
            if (bean.getId().equals(id)) {
                mPhotoAdapter.notifyItemChanged(i, PhotoListAdapter.STATE_NORMAL);
            }
        }
    }


    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }


    public static PhotoListFragment newInstance() {
        return new PhotoListFragment();
    }

}
