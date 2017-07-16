package com.masker.discover.photo;

import android.support.annotation.NonNull;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.repository.PhotoRepository;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */

public class PhotoInfoPresenter extends BasePresenter<PhotoInfoContract.View> implements PhotoInfoContract.Presenter {


    public PhotoInfoPresenter(@NonNull PhotoInfoContract.View view) {
        super(view);
    }

    @Override
    public void loadPhotoInfo(String id) {
        Subscription subscription = PhotoRepository.getPhotoInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoBean>() {
                    @Override
                    public void call(PhotoBean info) {
                        mView.showPhotoInfo(info);
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
