package com.masker.discover.collection;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.activity.LoginActivity;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.photo.PhotoInfoActivity;
import com.masker.discover.photo.PhotoListAdapter;
import com.masker.discover.user.UserInfoActivity;
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
    private PhotoListAdapter mPhotoAdapter;
    private RelativeLayout mRlHeader;
    private List<PhotoListBean> mPhotos;
    private int mPage = START_PAGE;


    private CollectionDetailPresenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_detail;
    }

    @Override
    protected void initViews() {
        mTvTitle = bind(R.id.tv_title);
        mTvDesc = bind(R.id.tv_desc);
        mIvAvator = bind(R.id.iv_avatar);
        mTvFrom = bind(R.id.tv_from);
        mToolbar = bind(R.id.tool_bar);
        mRlHeader = bind(R.id.rl_header);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mProgressBar = bind(R.id.progress_bar);
        mIvCover = bind(R.id.iv_cover);
//        resetSize();
//        Glide.with(this).load(mImgUrl)
//                .bitmapTransform(new BlurTransformation(this))
//                .into(mIvCover);

        mRecyclerView = bind(R.id.recycler_view);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoListAdapter(mPhotos,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoAdapter.setLoadMoreListener(new BaseAdapter.LoadMoreListener() {
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


    @Override
    protected void initListeners() {
        mPhotoAdapter.setOnLikeListener(new PhotoListAdapter.OnLikeListener() {
            @Override
            public void onLike(View view, int position) {
                onClickLike(position);
            }
        });
        mPhotoAdapter.setOnAvatarClickListener(new PhotoListAdapter.OnAvatarClickListener() {
            @Override
            public void onAvatarClick(View view, int position) {
                User user = UserManager.getInstance().transform(mPhotos.get(position).getUser());
                UserInfoActivity.start(CollectionDetailActivity.this,user,UserInfoActivity.USER_OTHER);
            }
        });
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
                        .makeSceneTransitionAnimation(CollectionDetailActivity.this,ivPhoto,"photo");
                PhotoInfoActivity.start(CollectionDetailActivity.this,options.toBundle(),
                        id,imgUrl,width,height);
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
            startActivity(new Intent(this, LoginActivity.class));
        }
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
    protected void initData() {
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
        String backgroudUrl = bean.getUser().getProfile_image().getSmall();
        ImgLoader.loadAvator(this,url,mIvAvator);
        ImgLoader.loadBlurBackgroud(this,backgroudUrl,mRlHeader);
        String from = getString(R.string.from)+" "+bean.getUser().getName();
        mTvFrom.setText(from);
    }

    @Override
    public void showCollectionPhotos(List<PhotoListBean> photos) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mPhotos.addAll(photos);
        mPhotoAdapter.notifyDataSetChanged();
        if(mPhotos.size() == mTotalCount){
            mPhotoAdapter.enableLoadMore(false);
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
        else if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
        //Snackbar.make(mRlContent,message,Snackbar.LENGTH_SHORT).show();
        //stop progress
        for (int i = 0; i < mPhotos.size(); i++) {
            PhotoListBean bean = mPhotos.get(i);
            if(bean.getId().equals(id)){
                mPhotoAdapter.notifyItemChanged(i,PhotoListAdapter.STATE_NORMAL);
            }
        }
    }
}
