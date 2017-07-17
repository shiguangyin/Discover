package com.masker.discover.home;

import android.support.annotation.NonNull;

import com.masker.discover.global.UserManager;
import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.model.repository.UserRepository;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description:
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{


    public HomePresenter(@NonNull HomeContract.View view) {
        super(view);
    }

    @Override
    public void getMyInfo() {
        Subscription subscription = UserRepository.getMyInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean myInfo) {
                        UserManager.getInstance().writeMyInfo(myInfo);
                        User user = UserManager.getInstance().getUser();
                        mView.updateMyInfo(user);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                        Logger.e(throwable.getMessage());
                    }
                });
        mSubscriptions.add(subscription);
    }
}
