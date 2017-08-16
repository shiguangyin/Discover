package com.masker.discover.user.presenter;

import android.support.annotation.NonNull;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.repository.UserRepository;
import com.masker.discover.rx.RxTransformer;
import com.masker.discover.user.contract.EditProfileContract;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Author: masker
 * Date: 2017/8/16
 * Description:
 */

public class EditProfilePresenter extends BasePresenter<EditProfileContract.View> implements EditProfileContract.Presenter{

    public EditProfilePresenter(@NonNull EditProfileContract.View view) {
        super(view);
    }

    @Override
    public void updateMyInfo(String userName, String firstName, String lastName, String email,
                             String url, String location, String bio, String instagramUsername) {
        Subscription subscription = UserRepository
                .updateMyInfo(userName,firstName,lastName,email,url,location,bio,instagramUsername)
                .compose(RxTransformer.<UserInfoBean>ioMain())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean infoBean) {
                        mView.updateSuccess(infoBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.getMessage());
                    }
                });

    }
}
