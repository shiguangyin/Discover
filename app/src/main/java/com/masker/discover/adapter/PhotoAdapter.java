package com.masker.discover.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.print.PrintHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdpater;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.utils.ScreenUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by masker on 2017/4/26.
 */

public class PhotoAdapter extends BaseAdpater<Photo>{
    private static final String TAG = "PhotoAdapter";

    public PhotoAdapter(List<Photo> datas, int layoutId, Context context) {
        super(datas, layoutId, context);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, Photo data) {
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int width = ScreenUtils.getScreenWidth(context)-ScreenUtils.dp2px(context,20);
        int picWidth = data.getWidth();
        int picHeight = data.getHeight();
        int height = (width*picHeight)/picWidth;

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
        lp.height = height;
        ivPhoto.setLayoutParams(lp);
        String url = data.getUrls().getRegular();
        ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(context,
                android.R.color.darker_gray));
        Glide.with(context).load(url).placeholder(colorDrawable).crossFade().into(ivPhoto);

        String avatorUrl = data.getUser().getProfile_image().getLarge();
        CircleImageView ivAvator = holder.getView(R.id.iv_avator);
        Glide.with(context).load(avatorUrl).into(ivAvator);

        String name = data.getUser().getName();
        holder.setText(R.id.tv_name,name);


    }
}
