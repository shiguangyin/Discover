package com.masker.discover.presenter;

import com.masker.discover.contract.CollectionContract;
import com.masker.discover.model.entity.Collection;
import com.masker.discover.model.repository.CollectionRepository;

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

public class CollectionPresenter implements CollectionContract.Presenter{

    private CollectionContract.View mView;

    private CompositeSubscription mCompositeSubscription;


    public CollectionPresenter(CollectionContract.View view){
        mView = view;
        mView.setPresenter(this);
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override
    public void onSubscribe() {

    }

    @Override
    public void onUnsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void loadCollections(int page, int perPage, int type) {
        Subscription subscription = CollectionRepository.getCollections(page,perPage,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Collection>>() {
                    @Override
                    public void call(List<Collection> collections) {
                        mView.showCollections(collections);
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
