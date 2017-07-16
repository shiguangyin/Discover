package com.masker.discover.collection;

import android.support.annotation.NonNull;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.repository.CollectionRepository;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public class CollectionListPresenter extends BasePresenter<CollectionListContract.View> implements CollectionListContract.Presenter{


    public CollectionListPresenter(@NonNull CollectionListContract.View view) {
        super(view);
    }

    @Override
    public void loadCollections(int page, int perPage, int type) {
        Subscription subscription = CollectionRepository.getCollectionList(page,perPage,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CollectionListBean>>() {
                    @Override
                    public void call(List<CollectionListBean> collections) {
                        //filter
                        Observable.from(collections)
                                .filter(new Func1<CollectionListBean, Boolean>() {
                                    @Override
                                    public Boolean call(CollectionListBean collection) {
                                        return collection.getCover_photo() != null;
                                    }
                                })
                                .toList()
                                .subscribe(new Action1<List<CollectionListBean>>() {
                                    @Override
                                    public void call(List<CollectionListBean> collections) {
                                        mView.showCollections(collections);
                                    }
                                });
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
