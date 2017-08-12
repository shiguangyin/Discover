package com.masker.discover.downloads;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;

import java.util.List;

/**
 * Author: masker
 * Date: 2017/8/12
 * Description:
 */

public class DownloadingListAdapter extends BaseAdapter<DownloadingBean>{

    public DownloadingListAdapter(List<DownloadingBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    public int getmLayoutId() {
        return R.layout.rv_item_downloading;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, DownloadingBean data) {
        ImageView iv =  holder.getView(R.id.iv_photo);
        Glide.with(mContext).load(data.getUri()).into(iv);
        int progress = (int) ((data.getCurBytes()*100)/data.getTotalBytes());
        holder.setText(R.id.tv_progress,String.valueOf(progress)+"%");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        if(payloads != null && payloads.size() > 0){
            if(payloads.get(0) instanceof Integer){
                DownloadingBean data = mDatas.get(position);
                int progress = (int) ((data.getCurBytes()*100)/data.getTotalBytes());
                holder.setText(R.id.tv_progress,String.valueOf(progress)+"%");
            }
        }
        else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }
}
