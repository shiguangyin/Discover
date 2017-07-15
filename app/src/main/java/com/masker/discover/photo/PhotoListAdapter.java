package com.masker.discover.photo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ScreenUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: adapter for photo recyclerview
 */


public class PhotoListAdapter extends BaseAdapter<PhotoListBean> {

    public static final int STATE_NORMAL = 100000;
    public static final int STATE_LOADING = 100001;



    public PhotoListAdapter(List<PhotoListBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    public int getmLayoutId() {
        return R.layout.rv_item_photo;
    }

    @Override
    public void convert(BaseViewHolder holder, final int position, PhotoListBean data) {
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int width = ScreenUtils.getScreenWidth(mContext);
        int picWidth = data.getWidth();
        int picHeight = data.getHeight();
        int height = (width*picHeight)/picWidth;

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
        lp.height = height;
        ivPhoto.setLayoutParams(lp);
        String url = data.getUrls().getRegular();
        String color = data.getColor();
        ImgLoader.loadWithColor(mContext,url,ivPhoto,color);

        String avatarUrl = data.getUser().getProfile_image().getLarge();
        CircleImageView ivAvator = holder.getView(R.id.iv_avatar);
        ImgLoader.loadAvator(mContext,avatarUrl,ivAvator);

        String name = data.getUser().getName();
        holder.setText(R.id.tv_name,name);

        String likes = String.valueOf( data.getLikes());
        holder.setText(R.id.tv_likes,likes);

        ImageView ivLikes = holder.getView(R.id.iv_likes);
        if(data.isLiked_by_user()){
            Drawable drawable = ContextCompat.getDrawable(mContext,R.drawable.ic_like_red_24dp);
            ivLikes.setImageDrawable(drawable);
        }
        else{
            Drawable drawable = ContextCompat.getDrawable(mContext,R.drawable.ic_like_white_24dp);
            ivLikes.setImageDrawable(drawable);
        }
        ivLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLikeListener != null){
                    mLikeListener.onLike(v,position);
                }
            }
        });

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        if(payloads == null ||payloads.size() == 0){
            onBindViewHolder(holder,position);
        }
        else{
            //local refresh
            if(payloads.get(0) instanceof Integer){
                int state = (int) payloads.get(0);
                if(state == STATE_NORMAL){
                    holder.getView(R.id.like_progress).setVisibility(View.GONE);

                    ImageView ivLikes = holder.getView(R.id.iv_likes);
                    ivLikes.setVisibility(View.VISIBLE);
                    PhotoListBean data = mDatas.get(position);
                    if(data.isLiked_by_user()){
                        Drawable drawable = ContextCompat.getDrawable(mContext,R.drawable.ic_like_red_24dp);
                        ivLikes.setImageDrawable(drawable);
                    }
                    else{
                        Drawable drawable = ContextCompat.getDrawable(mContext,R.drawable.ic_like_white_24dp);
                        ivLikes.setImageDrawable(drawable);
                    }
                    String likes = String.valueOf(data.getLikes());
                    holder.setText(R.id.tv_likes,likes);
                }
                else if(state == STATE_LOADING){
                    holder.getView(R.id.like_progress).setVisibility(View.VISIBLE);
                    holder.getView(R.id.iv_likes).setVisibility(View.GONE);
                }
            }
        }
    }

    public interface OnLikeListener{
        void onLike(View view,int position);
    }

    private OnLikeListener mLikeListener;

    public void setOnLikeListener(OnLikeListener listener){
        mLikeListener = listener;
    }

}
