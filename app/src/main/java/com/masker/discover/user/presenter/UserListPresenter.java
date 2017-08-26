package com.masker.discover.user.presenter;

import android.support.annotation.NonNull;

import com.masker.discover.base.BaseLikePresenter;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.repository.UserRepository;
import com.masker.discover.user.contract.UserListContract;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserListPresenter extends BaseLikePresenter<UserListContract.View> implements UserListContract.Presenter{


    public UserListPresenter(@NonNull UserListContract.View view) {
        super(view);
    }

    @Override
    public void loadPhotos(String userName, int page, int perPage) {
        Subscription subscription = UserRepository.getUserPhotos(userName,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PhotoListBean>>() {
                    @Override
                    public void call(List<PhotoListBean> photoListBeen) {
                        mView.showPhotos(photoListBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.i(throwable.getMessage());
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void loadLikedPhotos(String userName, int page, int perPage) {
        Subscription subscription = UserRepository.getUserLikedPhotos(userName,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PhotoListBean>>() {
                    @Override
                    public void call(List<PhotoListBean> photoListBeen) {
                        mView.showLikedPhotos(photoListBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.i(throwable.getMessage());
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void loadCollections(String userName, int page, int perPage) {
        Logger.i("userName = "+userName+"  page = "+page+"  perPage "+perPage);
        Subscription subscription = UserRepository.getUserCollections(userName,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CollectionListBean>>() {
                    @Override
                    public void call(List<CollectionListBean> collectionListBeen) {
                        mView.showCollections(collectionListBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.i(throwable.getMessage());
                    }
                });
    }
}
