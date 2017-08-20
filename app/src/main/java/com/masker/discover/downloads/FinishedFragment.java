package com.masker.discover.downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.global.Constans;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.repository.PhotoRepository;
import com.masker.discover.photo.PhotoInfoActivity;
import com.masker.discover.rx.RxBus;
import com.masker.discover.rx.RxTransformer;
import com.masker.discover.rx.event.DownloadFinishEvent;
import com.masker.discover.widget.LoadingDialog;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: masker
 * Date: 2017/8/11
 * Description:
 */

public class FinishedFragment extends BaseFragment{


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    Unbinder mUnbinder;

    private List<FinishedBean> mFinishedBeen;
    private FinishedListAdapter mAdapter;
    private CompositeSubscription mSubscriptions  = new CompositeSubscription();
    private LoadingDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Subscription subscription = RxBus.toObservable(DownloadFinishEvent.class)
                .subscribe(new Action1<DownloadFinishEvent>() {
                    @Override
                    public void call(DownloadFinishEvent downloadFinishEvent) {
                        initData();
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finished;
    }

    @Override
    protected void initViews(View contentView) {
        mUnbinder = ButterKnife.bind(this,contentView);
        mFinishedBeen = new ArrayList<>();
        mAdapter = new FinishedListAdapter(mFinishedBeen,getContext());
        mAdapter.enableLoadMore(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mDialog = new LoadingDialog();

    }


    @Override
    protected void initListeners() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                FinishedBean data = mFinishedBeen.get(position);
                int index = data.getTitle().indexOf("_quality=");
                if(index > 0){
                    mDialog.show(getChildFragmentManager(),null);
                    final String id = data.getTitle().substring(0,index);
                    Subscription subscription = PhotoRepository.getPhotoInfo(id)
                            .compose(RxTransformer.<PhotoBean>ioMain())
                            .subscribe(new Action1<PhotoBean>() {
                                @Override
                                public void call(PhotoBean photoBean) {
                                    mDialog.dismiss();
                                    PhotoInfoActivity.start(getContext(),id,photoBean.getUrls().getRegular(),
                                            photoBean.getWidth(),photoBean.getHeight());
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    mDialog.dismiss();
                                }
                            });
                    mSubscriptions.add(subscription);
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            initData();
            Logger.i("init data");
        }
    }

    @Override
    protected void initData() {
        DownloadManager dm = (DownloadManager) getContext()
                .getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query()
                .setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
        Cursor cursor = dm.query(query);
        cursor.moveToFirst();
        if(cursor != null) {
            mFinishedBeen.clear();
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                FinishedBean bean = new FinishedBean();
                String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                bean.setLocalUri(localUri);
                String uri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI));
                bean.setUri(uri);
                String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                bean.setTitle(title);
                File file = new File(Environment.getExternalStoragePublicDirectory(Constans.DOWNLOAD_DIR), title);
                if(file.exists()){
                    mFinishedBeen.add(bean);
                }
            }
            cursor.close();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mSubscriptions.clear();
    }
}
