package com.masker.discover.photo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdpater;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.utils.ScreenUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: adapter for photo recyclerview
 */


public class PhotoAdapter extends BaseAdpater<Photo>{

    public PhotoAdapter(List<Photo> datas, int layoutId, Context context) {
        super(datas, layoutId, context);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, Photo data) {
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int width = ScreenUtils.getScreenWidth(context);
        int picWidth = data.getWidth();
        int picHeight = data.getHeight();
        int height = (width*picHeight)/picWidth;

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
        lp.height = height;
        ivPhoto.setLayoutParams(lp);
        String url = data.getUrls().getRegular();
        Glide.with(context).load(url).crossFade().into(ivPhoto);

        String avatarUrl = data.getUser().getProfile_image().getLarge();
        CircleImageView ivAvator = holder.getView(R.id.iv_avatar);
        Glide.with(context).load(avatarUrl).into(ivAvator);

        String name = data.getUser().getName();
        holder.setText(R.id.tv_name,name);

        String likes = String.valueOf( data.getLikes());
        holder.setText(R.id.tv_likes,likes);

        ImageView ivLikes = holder.getView(R.id.iv_likes);
        if(data.isLiked_by_user()){
            Drawable drawable = ContextCompat.getDrawable(context,R.drawable.ic_like_red_24dp);
            ivLikes.setImageDrawable(drawable);
        }
        else{
            Drawable drawable = ContextCompat.getDrawable(context,R.drawable.ic_like_white_24dp);
            ivLikes.setImageDrawable(drawable);
        }

    }


}
