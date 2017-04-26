package com.masker.discover.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdpater;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.Photo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by masker on 2017/4/26.
 */

public class PhotoAdapter extends BaseAdpater<Photo>{
    public PhotoAdapter(List<Photo> datas, int layoutId, Context context) {
        super(datas, layoutId, context);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, Photo data) {
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int width = ivPhoto.getMeasuredWidth();
        int picWidth = data.getWidth();
        int picHeight = data.getHeight();
        int height = (width*picHeight)/picWidth;
        ivPhoto.setMinimumHeight(height);
        String url = data.getUrls().getRegular();
        Glide.with(context).load(url).into(ivPhoto);

    }
}
