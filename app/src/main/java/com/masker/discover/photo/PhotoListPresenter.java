package com.masker.discover.photo;

import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;
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


public class PhotoListPresenter implements PhotoListContract.Presenter{

    private  PhotoListContract.View mView;

    private CompositeSubscription mSubscriptions;


    public PhotoListPresenter(PhotoListContract.View view){
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

    @Override
    public void likePhoto(String id) {
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
                        mView.showLikeError(throwable.getMessage());
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void unlikePhoto(String id) {
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
                        mView.showLikeError(throwable.getMessage());
                    }
                });
        mSubscriptions.add(subscription);
    }

}
