package com.masker.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.masker.discover.base.BaseViewHolder;
import com.masker.discover.model.entity.PhotoInfo;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/5/7
 * Description: photo info adpater, muti item
 */

public class PhotoInfoAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_TITLE = 1;
    private static final int TYPE_COLLECTION = 2;
    private static final int TYPE_TAG = 3;


    private List<Object> mDatas;
    private Context mContext;


    public PhotoInfoAdapter(Context context, List<Object> objects){
        mContext = context;
        mDatas = objects;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size(); //header 1
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
