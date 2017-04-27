package com.masker.discover.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by masker on 2017/4/26.
 */

public abstract class BaseAdpater<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> datas;
    protected int layoutId;
    protected Context context;
    protected View loadMoreView;
    protected boolean enableLoadMore = true;
    protected LoadMoreListener listener;


    protected static final int TYPE_DATA = 0;
    protected static final int TYPE_FOOTER = 1;

    public BaseAdpater(List<T> datas,int layoutId,Context context){
        this.datas = datas;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(viewType == TYPE_DATA){
            itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        }
        if(viewType == TYPE_FOOTER && enableLoadMore == true){
            if(loadMoreView == null){
                loadMoreView = LayoutInflater.from(context).inflate(layoutId,parent,false);
            }
            itemView = loadMoreView;
        }
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder.getItemViewType() == TYPE_DATA){
            convert(holder,position,datas.get(position));
        }
        if(holder.getItemViewType() == TYPE_FOOTER){
            if(enableLoadMore == true && !datas.isEmpty()){
                if(listener != null){
                    listener.onLoadMore();
                }
            }
            else{
                holder.getItemView().setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == datas.size()){
            return TYPE_FOOTER;
        }
        return TYPE_DATA;
    }

    public abstract void convert(BaseViewHolder holder, int position, T data);

    public void setLoadMoreView(View view){
        loadMoreView = view;
    }

    public void enableLoadMore(boolean loadMore){
        enableLoadMore = loadMore;
    }

    public interface LoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(LoadMoreListener listener){
        this.listener = listener;
    }

}

