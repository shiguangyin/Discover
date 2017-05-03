package com.masker.discover.base;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.masker.discover.R;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public abstract class BaseAdpater<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> datas;
    protected int layoutId;
    protected Context context;
    protected View loadMoreView;
    protected boolean enableLoadMore = true;
    protected LoadMoreListener loadMoreListener;
    protected OnItemClickListener onItemClickListener;


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
        else if(viewType == TYPE_FOOTER && enableLoadMore){
            if(loadMoreView == null){
                loadMoreView = LayoutInflater.from(context).inflate(R.layout.layout_load_more,parent,false);
            }
            itemView = loadMoreView;
        }
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if(holder.getItemViewType() == TYPE_DATA){
            convert(holder,position,datas.get(position));
            if(onItemClickListener != null){
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onClick(v,position);
                    }
                });
            }
        }
        if(holder.getItemViewType() == TYPE_FOOTER){
            if(enableLoadMore  && !datas.isEmpty()){
                holder.getItemView().setVisibility(View.VISIBLE);
                if(loadMoreListener != null){
                    loadMoreListener.onLoadMore();
                }
            }
            else{
                holder.getItemView().setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return enableLoadMore?(datas.size()+1):(datas.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(position == datas.size()){
            return TYPE_FOOTER;
        }
        return TYPE_DATA;
    }

    public abstract void convert(BaseViewHolder holder, int position, T data);


    public void enableLoadMore(boolean loadMore){
        enableLoadMore = loadMore;
    }

    public interface LoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(LoadMoreListener listener){
        this.loadMoreListener = listener;
    }

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }


}

