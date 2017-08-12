package com.masker.discover.downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.global.Constans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    }
}
