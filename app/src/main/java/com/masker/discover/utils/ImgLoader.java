package com.masker.discover.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * CreatedBy: masker
 * Date: 2017/6/5
 * Description:
 */

public class ImgLoader {

    public static void load(Context context,String url,ImageView iv){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv);
    }

    public static void loadDontAnimate(Context context,String url,ImageView iv){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .into(iv);
    }


    public static void loadAvatar(Context context, String url, ImageView iv){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(new ColorDrawable(Color.LTGRAY))
                .dontAnimate()
                .into(iv);
    }

    public static void loadAvatar(Context context,String url,ImageView iv,boolean usePlaceholder){
        if(usePlaceholder){
            loadAvatar(context,url,iv);
        }
        else{
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate()
                    .into(iv);
        }
    }


    public static void loadBlurBackground(Context context, String url, final View view){
        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context,25),
                new ColorFilterTransformation(context,Color.parseColor("#44888888")))
                .crossFade()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        view.setBackground(resource);
                    }
                });
    }

    public static void loadTag(Context context,String url,ImageView iv){
        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void loadWithColoAndThumb(Context context,String url,String thumbUrl,String color,ImageView iv){
        int duration = 1500;
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(color));
        if(url.equals(thumbUrl)){
            Glide.with(context).load(url).placeholder(colorDrawable).crossFade(duration).into(iv);
        }
        else{
            DrawableRequestBuilder<String> request = Glide.with(context)
                    .load(thumbUrl)
                    .bitmapTransform(new GrayscaleTransformation(context));
            Glide.with(context).load(url).thumbnail(request)
                    .placeholder(colorDrawable)
                    .crossFade(duration)
                    .into(iv);
        }
    }
}
