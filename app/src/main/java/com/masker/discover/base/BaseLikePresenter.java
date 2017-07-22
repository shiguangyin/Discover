package com.masker.discover.base;

import android.support.annotation.NonNull;

import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.repository.PhotoRepository;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author: masker
 * Date: 2017/7/22
 * Description:
 */

public class BaseLikePresenter<T extends BaseLikeView> extends BasePresenter<T> {
    public BaseLikePresenter(@NonNull T view) {
        super(view);
    }


    public void likePhoto(final String id) {
        Subscription subscription = PhotoRepository.likePhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LikeResponseBean>() {
                    @Override
                    public void call(LikeResponseBean bean) {
                        mView.updatePhoto(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showLikeError(throwable.getMessage(),id);
                    }
                });
        mSubscriptions.add(subscription);
    }


    public void unlikePhoto(final String id) {
        Subscription subscription = PhotoRepository.unlikePhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LikeResponseBean>() {
                    @Override
                    public void call(LikeResponseBean bean) {
                        mView.updatePhoto(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showLikeError(throwable.getMessage(),id);

                    }
                });
        mSubscriptions.add(subscription);
    }


}
