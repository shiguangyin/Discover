package com.masker.discover.user.presenter;

import android.support.annotation.NonNull;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.api.UserService;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.http.HttpClient;
import com.masker.discover.model.repository.UserRepository;
import com.masker.discover.user.contract.UserInfoContract;
import com.orhanobut.logger.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
                        Logger.e(throwable.getMessage());
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
                        Logger.e(throwable.getMessage());
                        mView.showError();
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void followUser(final String userName) {
        HttpClient.create(UserService.class)
                .followUser(userName)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //mView.showFollowStatusChange();
                        loadUserInfo(userName);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mView.showError();
                    }
                });
    }

    @Override
    public void deleteFollow(final String userName) {
        HttpClient.create(UserService.class)
                .deleteFollow(userName)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                         //mView.showFollowStatusChange();
                        loadUserInfo(userName);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mView.showError();
                    }
                });
    }
}
