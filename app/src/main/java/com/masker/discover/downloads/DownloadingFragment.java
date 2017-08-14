package com.masker.discover.downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.global.Constans;
import com.masker.discover.rxbus.DownloadFinishEvent;
import com.masker.discover.rxbus.RxBus;
import com.masker.discover.utils.SpUtils;

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

public class DownloadingFragment extends BaseFragment{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<DownloadingBean> mDownloadingBeen;
    private DownloadingListAdapter mAdapter;

    private Unbinder mUnbinder;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            refresh();
            mHandler.sendEmptyMessageDelayed(1,1000);
        }
    };
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Subscription subscription = RxBus.toObservable(DownloadFinishEvent.class)
                .subscribe(new Action1<DownloadFinishEvent>() {
                    @Override
                    public void call(DownloadFinishEvent downloadFinishEvent) {
                        long id = downloadFinishEvent.getId();
                        int pos = remove(id);
                        mAdapter.notifyItemRemoved(pos);
                        SpUtils.removeString(Constans.SP_LOADING_URL,String.valueOf(id));
                    }
                });
    }


    private void refresh(){
        DownloadManager dm = (DownloadManager) getContext()
                .getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query()
                .setFilterByStatus(DownloadManager.STATUS_RUNNING);
        Cursor cursor = dm.query(query);
        cursor.moveToFirst();
        if(cursor != null) {
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                DownloadingBean bean = new DownloadingBean();
                long id = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                bean.setId(id);
                String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                bean.setLocalUri(localUri);
                String uri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI));
                bean.setUri(uri);
                String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                bean.setTitle(title);
                long total = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                bean.setTotalBytes(total);
                long cur = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                bean.setCurBytes(cur);
                if(!contains(bean)){
                    mDownloadingBeen.add(bean);
                    mAdapter.notifyItemInserted(mDownloadingBeen.size()-1);
                }
                else{
                    mDownloadingBeen.set(getPosition(bean),bean);
                    mAdapter.notifyItemChanged(getPosition(bean),1);
                }
            }
            cursor.close();
        }
    }

    private int getPosition(DownloadingBean bean){
        for (int i = 0;i < mDownloadingBeen.size();i++){
            if(mDownloadingBeen.get(i).getId() == bean.getId()){
                return i;
            }
        }
        return -1;
    }

    private boolean contains(DownloadingBean bean){
        for (int i = 0; i < mDownloadingBeen.size(); i++) {
            if(mDownloadingBeen.get(i).getId() == bean.getId()){
                return true;
            }
        }
        return false;
    }

    private int remove(long id){
        for (int i = 0; i < mDownloadingBeen.size(); i++) {
            if(mDownloadingBeen.get(i).getId() == id){
                mDownloadingBeen.remove(i);
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_downloading;
    }

    @Override
    protected void initViews(View contentView) {
        mUnbinder = ButterKnife.bind(this,contentView);
        mDownloadingBeen = new ArrayList<>();
        mAdapter = new DownloadingListAdapter(mDownloadingBeen,getContext());
        mAdapter.enableLoadMore(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        try {
            DownloadManager dm = (DownloadManager) getContext()
                    .getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Query query = new DownloadManager.Query()
                    .setFilterByStatus(DownloadManager.STATUS_RUNNING);
            Cursor cursor = dm.query(query);
            cursor.moveToFirst();
            if(cursor != null) {
                for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    DownloadingBean bean = new DownloadingBean();
                    long id = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                    bean.setId(id);
                    String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    bean.setLocalUri(localUri);
                    String uri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI));
                    bean.setUri(uri);
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    bean.setTitle(title);
                    long total = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    bean.setTotalBytes(total);
                    long cur = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    bean.setCurBytes(cur);
                    mDownloadingBeen.add(bean);
                }
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();
        mHandler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mHandler.removeCallbacksAndMessages(null);
        mSubscriptions.clear();
    }
}
