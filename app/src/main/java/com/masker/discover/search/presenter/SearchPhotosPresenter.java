package com.masker.discover.search.presenter;

import com.masker.discover.model.entity.PhotoSearchBean;
import com.masker.discover.model.repository.SearchRepository;
import com.masker.discover.search.contract.SearchPhotosContract;
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

public class SearchPhotosPresenter implements SearchPhotosContract.Presenter {
    private CompositeSubscription mSubscriptions;
    private SearchPhotosContract.View mView;

    public SearchPhotosPresenter(SearchPhotosContract.View view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onUnsubscribe() {
        mView = null;
        mSubscriptions.clear();
    }

    @Override
    public void searchPhotos(String key, int page, int perPage) {
        Subscription subscription = SearchRepository.searchPhotos(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoSearchBean>() {
                    @Override
                    public void call(PhotoSearchBean photoSearchBean) {
                        mView.showPhotos(photoSearchBean);
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
