package com.masker.discover.base;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public  class BasePresenter<T extends BaseView>{
    protected T mView;
    protected CompositeSubscription mSubscriptions;

    public BasePresenter(@NonNull T view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }


    public void onUnsubscribe(){
        mView = null;
        mSubscriptions.clear();
    }
}
