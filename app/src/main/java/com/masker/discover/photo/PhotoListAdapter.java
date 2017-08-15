package com.masker.discover.photo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.global.Constans;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.user.UserInfoActivity;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ScreenUtils;
import com.masker.discover.utils.SpUtils;

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
    public void convert(BaseViewHolder holder, final int position, final PhotoListBean data) {
        final ImageView ivPhoto = holder.getView(R.id.iv_photo);
        final int width = ScreenUtils.getScreenWidth(mContext);
        int picWidth = data.getWidth();
        int picHeight = data.getHeight();
        final int height = (width*picHeight)/picWidth;

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
        lp.height = height;
        ivPhoto.setLayoutParams(lp);
        String loadQuality = SpUtils.getString(Constans.SP_SETTINGS,
                mContext.getString(R.string.key_load_quality));
        String url = null;
        if(TextUtils.isEmpty(loadQuality) || loadQuality.equals(Constans.REGULAR)){
            url = data.getUrls().getRegular();
        }
        else if(loadQuality.equals(Constans.RAW)){
            url = data.getUrls().getRaw();
        }
        else if(loadQuality.equals(Constans.FULL)){
            url = data.getUrls().getFull();
        }
        else if(loadQuality.equals(Constans.SMALL)){
            url = data.getUrls().getSmall();
        }
        else{
            url = data.getUrls().getThumb();
        }
        String color = data.getColor();
        ImgLoader.loadWithColor(mContext,url,ivPhoto,color);

        final String finalUrl = url;
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = data.getId();
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) mContext,ivPhoto,"photo");
                PhotoInfoActivity.start(mContext,options.toBundle(),
                        id, finalUrl,width,height);
            }
        });

        String avatarUrl = data.getUser().getProfile_image().getLarge();
        CircleImageView ivAvator = holder.getView(R.id.iv_avatar);
        ImgLoader.loadAvator(mContext,avatarUrl,ivAvator);
        ivAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = UserManager.getInstance().transform(data.getUser());
                UserInfoActivity.start(mContext,user,UserInfoActivity.USER_OTHER);
            }
        });

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
