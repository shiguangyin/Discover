package com.masker.discover.search;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.global.UserManager;
import com.masker.discover.model.entity.User;
import com.masker.discover.model.entity.UserListBean;
import com.masker.discover.user.view.UserInfoActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class UserListAdapter extends BaseAdapter<UserListBean> {
    public UserListAdapter(List<UserListBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    public int getmLayoutId() {
        return R.layout.rv_item_user;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, final UserListBean data) {
        final CircleImageView ivAvator = holder.getView(R.id.iv_avatar);
        String url = data.getProfile_image().getLarge();
        Glide.with(mContext).load(url).into(ivAvator);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = UserManager.transform(data);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) mContext,ivAvator,"avatar");
                UserInfoActivity.start(mContext,user,UserInfoActivity.USER_OTHER,options.toBundle());
            }
        });

        holder.setText(R.id.tv_name,data.getName());
        holder.setText(R.id.tv_user,"@"+data.getUsername());

        int photos = data.getTotal_photos();
        int collections = data.getTotal_collections();
        int likes = data.getTotal_likes();
        String countMsg = mContext.getString(R.string.user_msg,photos,collections,likes);
        holder.setText(R.id.tv_count,countMsg);
    }
}
