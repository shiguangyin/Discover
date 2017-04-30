package com.masker.discover.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.util.LogTime;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description: base fragment
 */


public abstract class BaseFragment extends Fragment{

    protected View mContentView;
    protected String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }
}
