package com.masker.discover.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.masker.discover.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

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
        Glide.with(context).load(url).dontAnimate().into(iv);
    }

    public static void loadBlurBackgroud(Context context, String url, final View view){
        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context,25))
                .animate(R.anim.anim_load_photo)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        view.setBackground(resource);
                    }
                });
    }
}
