package com.masker.discover.downloads;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Author: masker
 * Date: 2017/8/11
 * Description:
 */

public class FinishedListAdapter extends BaseAdapter<FinishedBean>{
    public FinishedListAdapter(List<FinishedBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    public int getmLayoutId() {
        return R.layout.rv_item_finished;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, FinishedBean data) {
        ImageView iv = holder.getView(R.id.iv_photo);
        Logger.i(data.getTitle());
        Glide.with(mContext).load(data.getLocalUri()).into(iv);
        int index = data.getTitle().indexOf("_quality=");
        if(index > 0){
            String id = data.getTitle().substring(0,index);
            holder.setText(R.id.tv_id,id);
        }
    }
}
