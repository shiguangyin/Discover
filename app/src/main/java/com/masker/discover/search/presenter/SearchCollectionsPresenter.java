package com.masker.discover.search.presenter;


import com.masker.discover.model.entity.CollectionSearchBean;
import com.masker.discover.model.repository.SearchRepository;
import com.masker.discover.search.contract.SearchCollectionsContract;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public class SearchCollectionsPresenter implements SearchCollectionsContract.Presenter {
    private CompositeSubscription mSubscriptions;
    private SearchCollectionsContract.View mView;

    public SearchCollectionsPresenter(SearchCollectionsContract.View view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onUnsubscribe() {
        mView = null;
        mSubscriptions.clear();
    }

    @Override
    public void searchCollections(String key, int page, int perPage) {
        Subscription subscription = SearchRepository.searchCollections(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CollectionSearchBean>() {
                    @Override
                    public void call(CollectionSearchBean collectionSearchBean) {
                        mView.showCollections(collectionSearchBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                        mView.showError();
                    }
                });
        mSubscriptions.add(subscription);
    }
}
