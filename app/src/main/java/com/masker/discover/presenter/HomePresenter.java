package com.masker.discover.presenter;

import android.content.Context;
import android.util.EventLogTags;
import android.util.Log;

import com.masker.discover.contract.HomeContract;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.repository.PhotoRepository;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public class HomePresenter implements HomeContract.Presenter{

    private  HomeContract.View mView;

    private CompositeSubscription mSubscriptions;


    public HomePresenter(HomeContract.View view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }


    @Override
    public void onUnsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void loadPhotos(int page, int perPage, String orderBy) {
        Subscription subscription = PhotoRepository.getPhoto(page,perPage,orderBy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Photo>>() {
                    @Override
                    public void call(List<Photo> photos) {
                        mView.showPhotos(photos);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }
                });
        mSubscriptions.add(subscription);
    }
}
