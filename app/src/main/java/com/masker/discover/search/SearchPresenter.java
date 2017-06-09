package com.masker.discover.search;

import com.masker.discover.model.entity.CollectionSearchBean;
import com.masker.discover.model.entity.PhotoSearchBean;
import com.masker.discover.model.entity.UserSearchBean;
import com.masker.discover.model.repository.SearchRepository;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchPresenter implements SearchContract.Presenter{

    private CompositeSubscription mSubscriptions;
    private SearchContract.View mView;


    public SearchPresenter(SearchContract.View view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void searchCollections(String key, int page, int perPage) {
        Subscription subscription = SearchRepository.searchCollections(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CollectionSearchBean>() {
                    @Override
                    public void call(CollectionSearchBean collectionSearchBean) {
                        mView.showLists(collectionSearchBean);
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
    public void searchPhotos(String key, int page, int perPage) {
        Subscription subscription = SearchRepository.searchPhotos(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoSearchBean>() {
                    @Override
                    public void call(PhotoSearchBean photoSearchBean) {
                        mView.showLists(photoSearchBean);
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
    public void searchUsers(String key, int page, int perPage) {
        Subscription subscription = SearchRepository.searchUsers(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserSearchBean>() {
                    @Override
                    public void call(UserSearchBean userSearchBean) {
                        mView.showLists(userSearchBean);
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
    public void onUnsubscribe() {
        mView = null;
        mSubscriptions.clear();
    }
}
