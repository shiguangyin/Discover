package com.masker.discover.collection;

import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.repository.CollectionRepository;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/6/2
 * Description:
 */

public class CollectionDetailPresenter implements CollectionDetailContract.Presenter {


    private CollectionDetailContract.View mView;
    private CompositeSubscription mSubsciptions;

    public CollectionDetailPresenter(CollectionDetailContract.View view){
        mView = view;
        mSubsciptions = new CompositeSubscription();
    }

    @Override
    public void onUnsubscribe() {
        mView = null;
        mSubsciptions.clear();
    }

    @Override
    public void loadCollection(int id) {
        Subscription subscription = CollectionRepository.getCollection(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CollectionBean>() {
                    @Override
                    public void call(CollectionBean collectionBean) {
                        if(collectionBean != null){
                            mView.showCollection(collectionBean);
                        }
                        else{
                            mView.showError();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.i(throwable.getMessage());
                        mView.showError();
                    }
                });
        mSubsciptions.add(subscription);
    }

    @Override
    public void loadCollectionPhotos(int id, int page, int perPage) {
        Subscription subscription = CollectionRepository.getCollectionPhotos(id,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PhotoListBean>>() {
                    @Override
                    public void call(List<PhotoListBean> photoListBeen) {
                        if(photoListBeen != null && photoListBeen.size() > 0){
                            mView.showCollectionPhotos(photoListBeen);
                        }
                        else if(photoListBeen == null){
                            mView.showError();
                        }
                        else{
                            mView.showEmpty();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                        mView.showError();
                    }
                });
        mSubsciptions.add(subscription);
    }

    @Override
    public void loadCuratedCollection(int id) {
        Subscription subscription = CollectionRepository.getCuratedCollection(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CollectionBean>() {
                    @Override
                    public void call(CollectionBean collectionBean) {
                        if(collectionBean != null){
                            mView.showCollection(collectionBean);
                        }
                        else{
                            mView.showError();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.i(throwable.getMessage());
                        mView.showError();
                    }
                });
        mSubsciptions.add(subscription);
    }

    @Override
    public void loadCuratedCollectionPhotos(int id, int page, int perPage) {
        Subscription subscription = CollectionRepository.getCuratedCollectionPhotos(id,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PhotoListBean>>() {
                    @Override
                    public void call(List<PhotoListBean> photoListBeen) {
                        if(photoListBeen != null && photoListBeen.size() > 0){
                            mView.showCollectionPhotos(photoListBeen);
                        }
                        else if(photoListBeen == null){
                            mView.showError();
                        }
                        else{
                            mView.showEmpty();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                        mView.showError();
                    }
                });
        mSubsciptions.add(subscription);
    }
}
