package com.masker.discover.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by masker on 2017/4/26.
 */

public abstract class BaseFragment extends Fragment{

    protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getLayoutId() != 0){
            mContentView = inflater.inflate(getLayoutId(),container,false);
        }
        initViews(mContentView);
        initData();
        return mContentView;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View contentView);

    protected abstract void initData();

    protected <T extends View> T getViewById(int resId){
        return (T) mContentView.findViewById(resId);
    }
}
