package com.masker.discover.photo;

import android.support.annotation.NonNull;

import com.masker.discover.base.BaseLikePresenter;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.repository.PhotoRepository;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public class PhotoListPresenter extends BaseLikePresenter<PhotoListContract.View> implements PhotoListContract.Presenter{


    public PhotoListPresenter(@NonNull PhotoListContract.View view) {
        super(view);
    }

    @Override
    public void loadPhotos(int page, int perPage, String orderBy) {
        Subscription subscription = PhotoRepository.getPhoto(page,perPage,orderBy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PhotoListBean>>() {
                    @Override
                    public void call(List<PhotoListBean> photos) {
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
