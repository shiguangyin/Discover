package com.masker.discover.photo;

import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.repository.PhotoRepository;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */


public class PhotoPresenter implements PhotoContract.Presenter{

    private  PhotoContract.View mView;

    private CompositeSubscription mSubscriptions;


    public PhotoPresenter(PhotoContract.View view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void onUnsubscribe() {
        mView = null;
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
