package com.masker.discover.user;

import android.support.annotation.NonNull;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.api.UserService;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.http.HttpClient;
import com.masker.discover.model.repository.UserRepository;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {

    public UserInfoPresenter(@NonNull UserInfoContract.View view) {
        super(view);
    }

    @Override
    public void loadMyInfo() {
        Subscription subscription = HttpClient.getClient()
                .create(UserService.class)
                .getMyInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean infoBean) {
                        mView.showMyInfo(infoBean);
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
    public void loadUserInfo(String userName) {
        Subscription subscription = UserRepository.getUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean userInfoBean) {
                        mView.showUserInfo(userInfoBean);
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
