package com.masker.discover.photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.entity.PhotoInfo;
import com.masker.discover.model.entity.Tag;
import com.masker.discover.utils.DateUtils;
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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder.getItemViewType() == TYPE_HEADER){
            PhotoInfo info = (PhotoInfo) mDatas.get(position);

            CircleImageView ivAvator = holder.getView(R.id.iv_avator);
            String avatorUrl = info.getUser().getProfile_image().getLarge();
            Glide.with(mContext).load(avatorUrl).into(ivAvator);

            holder.setText(R.id.tv_name,info.getUser().getName());
            holder.setText(R.id.tv_time, DateUtils.transform(info.getUpdated_at()));

            holder.setText(R.id.tv_views_num, String.valueOf(info.getViews()));
            holder.setText(R.id.tv_download_num,String.valueOf(info.getDownloads()));
            holder.setText(R.id.tv_likes_num,String.valueOf(info.getLikes()));
        }

        else if(holder.getItemViewType() == TYPE_TITLE){
            String title = (String) mDatas.get(position);
            holder.setText(R.id.tv_title,title);
        }
        else if(holder.getItemViewType() == TYPE_COLLECTION){
            PhotoInfo.RelatedCollectionsBean.ResultsBean data
                    = (PhotoInfo.RelatedCollectionsBean.ResultsBean) mDatas.get(position);
            ImageView ivPhoto = holder.getView(R.id.iv_photo);
            int width = ScreenUtils.getScreenWidth(mContext);
            int picWidth = data.getCover_photo().getWidth();
            int picHeight = data.getCover_photo().getHeight();
            int height = (width*picHeight)/picWidth;

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
            lp.height = height;
            ivPhoto.setLayoutParams(lp);

            String url = data.getCover_photo().getUrls().getRegular();
            Glide.with(mContext).load(url).into(ivPhoto);

            CircleImageView ivAvator = holder.getView(R.id.iv_avator);
            String avatorUrl = data.getUser().getProfile_image().getLarge();
            Glide.with(mContext).load(avatorUrl).into(ivAvator);

            String name = data.getUser().getName();
            holder.setText(R.id.tv_name,name);

            String title = data.getTitle().toUpperCase();
            holder.setText(R.id.tv_title,title);

            int num = data.getTotal_photos();
            String strNum = num + " "+mContext.getString(R.string.photos);
            holder.setText(R.id.tv_num,strNum);
        }
        else if(holder.getItemViewType() == TYPE_TAG){
            Tag data = (Tag) mDatas.get(position);
            String url = data.getUrl();
            ImageView ivCover = holder.getView(R.id.iv_cover);
            Glide.with(mContext).load(url).centerCrop().into(ivCover);

            String tag = data.getTitle().toUpperCase();
            holder.setText(R.id.tv_tag,tag);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size(); //header 1
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas.get(position) instanceof PhotoInfo){
            return TYPE_HEADER;
        }
        else if(mDatas.get(position) instanceof String){
            return TYPE_TITLE;
        }
        else if(mDatas.get(position) instanceof PhotoInfo.RelatedCollectionsBean.ResultsBean){
            return TYPE_COLLECTION;
        }
        else if(mDatas.get(position) instanceof Tag){
            return TYPE_TAG;
        }
        return TYPE_TITLE;
    }
}
