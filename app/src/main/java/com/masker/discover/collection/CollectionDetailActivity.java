package com.masker.discover.collection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.photo.PhotoListAdapter;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CollectionDetailActivity extends BaseMvpActivity
        implements CollectionDetailContract.View{

    public static final String ID = "id";
    public static final String CURATED =  "curated";
    public static final String TOTAL = "total";
    public static final String HEIGHT = "height";
    public static final String WIDTH = "width";
    public static final String IMG_URL = "img_url";

    public static final int START_PAGE = 1;
    public static final int PER_PAGE = 20;

    private int mId;
    private boolean mIsCurated;
    private int mTotalCount;
    private int mImgHeight;
    private int mImgWidth;
    private String mImgUrl;

    private Toolbar mToolbar;
    private ImageView mIvCover;
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
        mIvCover = find(R.id.iv_cover);
//        resetSize();
//        Glide.with(this).load(mImgUrl)
//                .bitmapTransform(new BlurTransformation(this))
//                .into(mIvCover);

        mRecyclerView = find(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mAdapter = new PhotoListAdapter(mPhotos,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                if(mIsCurated){
                    mPresenter.loadCollectionPhotos(mId,mPage,PER_PAGE);
                }
                else{
                    mPresenter.loadCollectionPhotos(mId,mPage,PER_PAGE);
                }
            }
        });
    }

    /*
    * reset size of imageview
     */
    private void resetSize(){
        ViewGroup.LayoutParams lp = mIvCover.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(this);
        lp.height = (lp.width*mImgHeight)/mImgWidth;
        mIvCover.setLayoutParams(lp);
    }


    @Override
    protected void initDatas() {
        if(mId != -1){
            if(mIsCurated){
                mPresenter.loadCuratedCollection(mId);
                mPresenter.loadCuratedCollectionPhotos(mId,mPage,PER_PAGE);
            }
            else{
                mPresenter.loadCollection(mId);
                mPresenter.loadCollectionPhotos(mId, mPage,PER_PAGE);
            }
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
        if(mPhotos.size() == mTotalCount){
            mAdapter.enableLoadMore(false);
        }
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
        Intent intent = getIntent();
        mId = intent.getIntExtra(ID,-1);
        mIsCurated = intent.getBooleanExtra(CURATED,false);
        mTotalCount = intent.getIntExtra(TOTAL,0);
        mImgHeight = intent.getIntExtra(HEIGHT,0);
        mImgWidth = intent.getIntExtra(WIDTH,0);
        mImgUrl = intent.getStringExtra(IMG_URL);
    }

    public static void start(Context context, int id,boolean curated,int total,
                             int height,int width,String url){
        Intent intent = new Intent(context,CollectionDetailActivity.class);
        intent.putExtra(ID,id);
        intent.putExtra(CURATED,curated);
        intent.putExtra(TOTAL,total);
        intent.putExtra(HEIGHT,height);
        intent.putExtra(WIDTH,width);
        intent.putExtra(IMG_URL,url);
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
