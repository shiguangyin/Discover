package com.masker.discover.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        if(getLayoutId() != 0){
            mContentView = inflater.inflate(getLayoutId(),container,false);
        }
        initViews(mContentView);
        initListeners();
        initData();
        return mContentView;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View contentView);

    protected void initListeners(){}

    protected abstract void initData();

    protected <T extends View> T bind(int resId){
        return (T) mContentView.findViewById(resId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            handleArgs(args);
        }
    }

    protected void handleArgs(Bundle args){}

    public void showSnackBar(View view,String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
