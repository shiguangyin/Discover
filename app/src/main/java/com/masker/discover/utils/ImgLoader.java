package com.masker.discover.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;

/**
 * CreatedBy: masker
 * Date: 2017/6/5
 * Description:
 */

public class ImgLoader {
    public static void loadWithColor(Context context, String url, ImageView iv,String color){
        int col = Color.parseColor(color);
        ColorDrawable placeHolder = new ColorDrawable(col);
        Glide.with(context).load(url).placeholder(placeHolder)
                .animate(R.anim.anim_load_photo)
                .into(iv);
    }


    public static void loadAvator(Context context, String url, ImageView iv){
        Glide.with(context).load(url).into(iv);
    }
}
