package com.masker.discover.search;

import android.support.annotation.NonNull;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.entity.CollectionSearchBean;
import com.masker.discover.model.entity.PhotoSearchBean;
import com.masker.discover.model.entity.UserSearchBean;
import com.masker.discover.model.repository.SearchRepository;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{


    public SearchPresenter(@NonNull SearchContract.View view) {
        super(view);
    }

    @Override
    public void searchCollections(String key, int page, int perPage, final boolean refresh) {
        Subscription subscription = SearchRepository.searchCollections(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CollectionSearchBean>() {
                    @Override
                    public void call(CollectionSearchBean collectionSearchBean) {
                        mView.showLists(collectionSearchBean,refresh);
                        mView.hideLoading();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                        mView.showError();
                        mView.hideLoading();
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void searchPhotos(String key, int page, int perPage, final boolean refresh) {
        Subscription subscription = SearchRepository.searchPhotos(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoSearchBean>() {
                    @Override
                    public void call(PhotoSearchBean photoSearchBean) {
                        mView.showLists(photoSearchBean,refresh);
                        mView.hideLoading();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                        mView.showError();
                        mView.hideLoading();
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void searchUsers(String key, int page, int perPage, final boolean refresh) {
        Subscription subscription = SearchRepository.searchUsers(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserSearchBean>() {
                    @Override
                    public void call(UserSearchBean userSearchBean) {
                        mView.showLists(userSearchBean,refresh);
                        mView.hideLoading();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                        mView.showError();
                        mView.hideLoading();
                    }
                });
        mSubscriptions.add(subscription);
    }
}
