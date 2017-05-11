package com.masker.discover.photo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;

import android.util.Log;
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

        String avatorUrl = data.getUser().getProfile_image().getLarge();
        CircleImageView ivAvator = holder.getView(R.id.iv_avator);
        Glide.with(context).load(avatorUrl).into(ivAvator);

        String name = data.getUser().getName();
        holder.setText(R.id.tv_name,name);

        String likes = String.valueOf( data.getLikes());
        holder.setText(R.id.tv_likes,likes);

    }


}
