package com.masker.discover.tag;

import android.content.Context;
import android.widget.ImageView;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.TagBean;
import com.masker.discover.utils.ImgLoader;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description: adpter for tag recyclerview
 */

public class TagListAdapter extends BaseAdapter<TagBean> {
    public TagListAdapter(List<TagBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    public int getmLayoutId() {
        return R.layout.rv_item_tag;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, TagBean data) {
        String url = data.getUrl();
        ImageView ivCover = holder.getView(R.id.iv_cover);
        ImgLoader.loadTag(mContext,url,ivCover);

        String tag = data.getTitle().toUpperCase();
        holder.setText(R.id.tv_tag,tag);
    }
}
