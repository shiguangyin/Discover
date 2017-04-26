package com.masker.discover.base;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masker.discover.contract.HomeContract;

import java.util.List;

/**
 * Created by masker on 2017/4/26.
 */

public abstract class BaseAdpater<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> datas;
    protected int layoutId;
    protected Context context;

    public BaseAdpater(List<T> datas,int layoutId,Context context){
        this.datas = datas;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(layoutId,null);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder,position,datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public abstract void convert(BaseViewHolder holder,int position,T data);

}

