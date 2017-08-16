package com.masker.discover.collection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.user.view.UserInfoActivity;
import com.masker.discover.utils.ImgLoader;
import com.masker.discover.utils.ScreenUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: collection recylcerview adapter
 */

public class CollectionListAdapter extends BaseAdapter<CollectionListBean> {

    public CollectionListAdapter(List<CollectionListBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    public int getmLayoutId() {
        return R.layout.rv_item_collection;
    }

    @Override
    public void convert(BaseViewHolder holder, final int position, final CollectionListBean data) {
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int width = ScreenUtils.getScreenWidth(mContext);
        CollectionListBean.CoverPhotoBean cover = data.getCover_photo();
        int height = 600;
        if(cover != null){
            int picWidth = data.getCover_photo().getWidth();
            int picHeight = data.getCover_photo().getHeight();
            height = (width*picHeight)/picWidth;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
        lp.height = height;
        ivPhoto.setLayoutParams(lp);
        if(cover != null){
            String url = cover.getUrls().getRegular();
            String color = cover.getColor();
            ImgLoader.loadWithColor(mContext,url,ivPhoto,color);
        }
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = data.getId();
                int total = data.getTotal_photos();
                String title =  data.getTitle();
                CollectionDetailActivity.start(mContext,id,data.isCurated(),total,title);
            }
        });

        CircleImageView ivAvatar = holder.getView(R.id.iv_avatar);
        String avatorUrl = data.getUser().getProfile_image().getLarge();
        Glide.with(mContext).load(avatorUrl).into(ivAvatar);
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = UserManager.transform(data.getUser());
                UserInfoActivity.start(mContext,user,UserInfoActivity.USER_OTHER);
            }
        });

        String name = data.getUser().getName();
        holder.setText(R.id.tv_name,name);

        String title = data.getTitle().toUpperCase();
        holder.setText(R.id.tv_title,title);

        int num = data.getTotal_photos();
        String strNum = num + " "+ mContext.getString(R.string.photos);
        holder.setText(R.id.tv_num,strNum);

    }
}
