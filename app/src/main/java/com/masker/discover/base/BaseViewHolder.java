package com.masker.discover.base;

import android.graphics.Bitmap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseViewHolder extends RecyclerView.ViewHolder{

    private View itemView;

    private SparseArrayCompat<View> views;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArrayCompat<>();
    }

    /*
     * get view by resId
     */
    public <T extends View> T getView(int resId){
        if(views.get(resId)!=null){
            return (T) views.get(resId);
        }
        T view = (T) itemView.findViewById(resId);
        views.put(resId,view);
        return view;
    }

    public View getItemView(){
        return itemView;
    }

    /*
     * set text for textview
     */
    public void setText(int resId,String text){
        TextView tv = getView(resId);
        tv.setText(text);
    }

    /*
     * set onclick listener
     */
    public void setOnClickListener(int resId,View.OnClickListener listener){
        View view = getView(resId);
        view.setOnClickListener(listener);
    }

    /*
     * set bitmap
     */
    public void setBitmap(int resId, Bitmap bitmap){
        ImageView iv = getView(resId);
        iv.setImageBitmap(bitmap);
    }
}
