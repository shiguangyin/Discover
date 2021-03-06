package com.masker.discover.photo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.collection.CollectionDetailActivity;
import com.masker.discover.global.Constans;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.TagBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.search.SearchActivity;
import com.masker.discover.user.view.UserInfoActivity;
import com.masker.discover.utils.FormatUtils;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ScreenUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * CreatedBy: masker
 * Date: 2017/5/7
 * Description: photo info adpater, muti item
 */

public class PhotoInfoAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_TITLE = 1;
    private static final int TYPE_COLLECTION = 2;
    private static final int TYPE_TAG = 3;


    private List<Object> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;


    public PhotoInfoAdapter(Context context, List<Object> objects){
        mContext = context;
        mDatas = objects;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(viewType == TYPE_HEADER){
            itemView = mInflater.inflate(R.layout.rv_item_photoinfo_header,parent,false);
        }
        else if(viewType == TYPE_TITLE){
            itemView = mInflater.inflate(R.layout.rv_item_photoinfo_title,parent,false);
        }
        else if(viewType == TYPE_COLLECTION){
            itemView = mInflater.inflate(R.layout.rv_item_collection,parent,false);
        }
        else if(viewType == TYPE_TAG){
            itemView = mInflater.inflate(R.layout.rv_item_tag,parent,false);
        }
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case TYPE_HEADER:
                bindHeader(holder,position);
                break;
            case TYPE_TITLE:
                bindTitle(holder,position);
                break;
            case TYPE_COLLECTION:
                bindCollections(holder,position);
                break;
            case TYPE_TAG:
                bindTags(holder,position);
                break;
        }
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private void bindHeader(BaseViewHolder holder,int position){
        final PhotoBean info = (PhotoBean) mDatas.get(position);

        final CircleImageView ivAvatar = holder.getView(R.id.iv_avatar);
        String avatarUrl = info.getUser().getProfile_image().getLarge();
        ImgLoader.loadAvatar(mContext,avatarUrl,ivAvatar);
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = UserManager.getInstance().transform(info.getUser());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) mContext,ivAvatar,Constans.TRANSITION_AVATAR);
                UserInfoActivity.start(mContext,user,UserInfoActivity.USER_OTHER,optionsCompat.toBundle());
            }
        });

        RelativeLayout rlHeader = holder.getView(R.id.rl_header);
        ImgLoader.loadBlurBackground(mContext,info.getUser().getProfile_image().getSmall(),rlHeader);

        String name = String.format("By %s",info.getUser().getName());
        holder.setText(R.id.tv_name,name);
        holder.setText(R.id.tv_time, FormatUtils.transform(info.getUpdated_at()));

        if(!TextUtils.isEmpty(info.getStory().getDescription())){
            TextView tvStory = holder.getView(R.id.tv_story);
            tvStory.setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_story,info.getStory().getDescription());
            holder.setText(R.id.tv_story_title,info.getStory().getTitle());
        }

        holder.setText(R.id.tv_views_num,FormatUtils.getNum(info.getViews()));
        holder.setText(R.id.tv_download_num,FormatUtils.getNum(info.getDownloads()));
        holder.setText(R.id.tv_likes_num,FormatUtils.getNum(info.getLikes()));
    }

    private void bindTitle(BaseViewHolder holder,int position){
        String title = (String) mDatas.get(position);
        holder.setText(R.id.tv_title,title);
    }

    private void bindCollections(BaseViewHolder holder,int position){
        final PhotoBean.RelatedCollectionsBean.ResultsBean data
                = (PhotoBean.RelatedCollectionsBean.ResultsBean) mDatas.get(position);
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int width = ScreenUtils.getScreenWidth(mContext);
        PhotoBean.RelatedCollectionsBean.ResultsBean.CoverPhotoBean cover = data.getCover_photo();

        int height = 600;
        if(cover != null){
            int picWidth = data.getCover_photo().getWidth();
            int picHeight = data.getCover_photo().getHeight();
            height  = (width*picHeight)/picWidth;
        }

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
        lp.height = height;
        ivPhoto.setLayoutParams(lp);

        if(cover != null ){
            String url = cover.getUrls().getRegular();
            String thumbUrl = cover.getUrls().getThumb();
            String color = cover.getColor();
            ImgLoader.loadWithColoAndThumb(mContext,url,thumbUrl,color,ivPhoto);
        }

        final CircleImageView ivAvatar = holder.getView(R.id.iv_avatar);
        String avatarUrl = data.getUser().getProfile_image().getLarge();
        Glide.with(mContext).load(avatarUrl).into(ivAvatar);
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = UserManager.transform(data.getUser());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) mContext,ivAvatar,Constans.TRANSITION_AVATAR);
                UserInfoActivity.start(mContext,user,UserInfoActivity.USER_OTHER, optionsCompat.toBundle());
            }
        });

        String name = data.getUser().getName();
        holder.setText(R.id.tv_name,name);

        String title = data.getTitle().toUpperCase();
        holder.setText(R.id.tv_title,title);

        int num = data.getTotal_photos();
        String strNum = num + " "+mContext.getString(R.string.photos);
        holder.setText(R.id.tv_num,strNum);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = data.getId();
                int total = data.getTotal_photos();
                String title =  data.getTitle();
                String link = data.getLinks().getHtml();
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) mContext,ivAvatar, Constans.TRANSITION_AVATAR);
                CollectionDetailActivity.start(mContext,id,data.isCurated(),total,title,
                        data.getDescription(),data.getUser().getProfile_image().getLarge(),data.getUser().getName(),
                        data.getUser().getProfile_image().getSmall(),link,options.toBundle());
            }
        });
    }

    private void bindTags(BaseViewHolder holder, int position) {
        TagBean data = (TagBean) mDatas.get(position);
        String url = data.getUrl()+ Constans.TAG_SUFFIX;
        ImageView ivCover = holder.getView(R.id.iv_cover);
        ImgLoader.loadTag(mContext,url,ivCover);
        final String tag = data.getTitle().toUpperCase();
        holder.setText(R.id.tv_tag,tag);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.start(mContext,tag);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas.get(position) instanceof PhotoBean){
            return TYPE_HEADER;
        }
        else if(mDatas.get(position) instanceof String){
            return TYPE_TITLE;
        }
        else if(mDatas.get(position) instanceof PhotoBean.RelatedCollectionsBean.ResultsBean){
            return TYPE_COLLECTION;
        }
        else if(mDatas.get(position) instanceof TagBean){
            return TYPE_TAG;
        }
        return TYPE_TITLE;
    }

}
