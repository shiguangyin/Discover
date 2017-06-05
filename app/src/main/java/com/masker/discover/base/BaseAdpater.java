package com.masker.discover.base;

import android.content.Context;
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
    protected String TAG = getClass().getSimpleName();

    protected List<T> mDatas;
    protected int mLayoutId;
    protected Context mContext;
    protected View mFooterView;
    protected boolean mEnableLoadMore = true;
    protected boolean mNoMoreData = false;
    protected LoadMoreListener mLoadMoreListener;
    protected OnItemClickListener mItemClickListener;


    protected static final int TYPE_DATA = 0;
    protected static final int TYPE_FOOTER = 1;


    public BaseAdpater(List<T> datas,Context context){
        this.mDatas = datas;
        this.mLayoutId = getmLayoutId();
        this.mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(viewType == TYPE_DATA){
            itemView = LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
        }
        else if(viewType == TYPE_FOOTER && mEnableLoadMore){
            if(mFooterView == null){
                mFooterView = LayoutInflater.from(mContext).inflate(R.layout.layout_footer,parent,false);
            }
            itemView = mFooterView;
        }
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if(holder.getItemViewType() == TYPE_DATA){
            convert(holder,position, mDatas.get(position));
            if(mItemClickListener != null){
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onClick(v,position);
                    }
                });
            }
        }
        if(holder.getItemViewType() == TYPE_FOOTER){
            if(mEnableLoadMore && !mDatas.isEmpty()){
                holder.getItemView().setVisibility(View.VISIBLE);
                if(mNoMoreData){
                    holder.getView(R.id.tv_no_more).setVisibility(View.VISIBLE);
                    holder.getView(R.id.progress_bar).setVisibility(View.GONE);
                }
                else{
                    holder.getView(R.id.progress_bar).setVisibility(View.VISIBLE);
                    holder.getView(R.id.tv_no_more).setVisibility(View.GONE);
                }
                if(mLoadMoreListener != null){
                    mLoadMoreListener.onLoadMore();
                }
            }
            else{
                holder.getItemView().setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mEnableLoadMore ?(mDatas.size()+1):(mDatas.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mDatas.size()){
            return TYPE_FOOTER;
        }
        return TYPE_DATA;
    }


    public abstract int getmLayoutId();
    public abstract void convert(BaseViewHolder holder, int position, T data);


    public void enableLoadMore(boolean loadMore){
        mEnableLoadMore = loadMore;
    }

    public interface LoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(LoadMoreListener listener){
        this.mLoadMoreListener = listener;
    }

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }


    public void setNoMore(boolean noMore){
        mNoMoreData = noMore;
    }
}

