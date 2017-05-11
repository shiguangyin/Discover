package com.masker.discover.photo;

import android.util.Log;

import com.masker.discover.model.entity.PhotoInfo;
import com.masker.discover.model.repository.PhotoRepository;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */

public class PhotoInfoPresenter implements PhotoInfoContract.Presenter {
    private static final String TAG = "PhotoInfoPresenter";

    private CompositeSubscription mCompositeSubscription;
    private PhotoInfoContract.View mView;

    public PhotoInfoPresenter(PhotoInfoContract.View view){
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onUnsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void loadPhotoInfo(String id) {
        Subscription subscription = PhotoRepository.getPhotoInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoInfo>() {
                    @Override
                    public void call(PhotoInfo info) {
                        mView.showPhotoInfo(info);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
