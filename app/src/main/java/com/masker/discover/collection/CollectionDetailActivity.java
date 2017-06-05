package com.masker.discover.collection;

import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdpater;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.photo.PhotoListAdapter;
import com.masker.discover.utils.ImgLoader;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CollectionDetailActivity extends BaseMvpActivity
        implements CollectionDetailContract.View{

    public static final String ID = "id";
    public static final int START_PAGE = 1;
    public static final int PER_PAGE = 20;

    private int mId;

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private TextView mTvDesc;
    private CircleImageView mIvAvator;
    private TextView mTvFrom;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private PhotoListAdapter mAdapter;
    private List<PhotoListBean> mPhotos;
    private int mPage = START_PAGE;


    private CollectionDetailContract.Presenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_detail;
    }

    @Override
    protected void initViews() {
        mTvTitle = find(R.id.tv_title);
        mTvDesc = find(R.id.tv_desc);
        mIvAvator = find(R.id.iv_avatar);
        mTvFrom = find(R.id.tv_from);
        mToolbar = find(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mProgressBar = find(R.id.progress_bar);

        mRecyclerView = find(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mAdapter = new PhotoListAdapter(mPhotos,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreListener(new BaseAdpater.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                mPresenter.loadCollectionPhotos(mId,mPage,PER_PAGE);
            }
        });
    }

    @Override
    protected void initDatas() {
        if(mId != -1){
            mPresenter.loadCollection(mId);
            mPresenter.loadCollectionPhotos(mId, mPage,PER_PAGE);
        }
    }


    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showCollection(CollectionBean bean) {
        mTvTitle.setText(bean.getTitle());
        if(bean.getDescription() != null){
            mTvDesc.setText(bean.getDescription());
        }
        String url = bean.getUser().getProfile_image().getLarge();
        ImgLoader.loadAvator(this,url,mIvAvator);
        String from = getString(R.string.from)+" "+bean.getUser().getName();
        mTvFrom.setText(from);
    }

    @Override
    public void showCollectionPhotos(List<PhotoListBean> photos) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mPhotos.addAll(photos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void attach() {
        mPresenter = new CollectionDetailPresenter(this);
    }

    @Override
    protected void detach() {
        if(mPresenter != null){
            mPresenter.onUnsubscribe();
            mPresenter = null;
        }
    }


    @Override
    protected void handleIntent() {
        mId = getIntent().getIntExtra(ID,-1);
    }

    public static void start(Context context, int id){
        Intent intent = new Intent(context,CollectionDetailActivity.class);
        intent.putExtra(ID,id);
        context.startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_share){
            //Snackbar.make(mRecyclerView,"under developing",Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
