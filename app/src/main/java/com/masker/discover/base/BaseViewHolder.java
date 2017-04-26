package com.masker.discover.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder{

    private View itemView;

    private SparseArrayCompat<View> views;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArrayCompat<>();
    }

    public <T extends View> T getView(int resId){
        if(views.get(resId)!=null){
            return (T) views.get(resId);
        }
        T view = (T) itemView.findViewById(resId);
        views.put(resId,view);
        return view;
    }

}
