package com.masker.discover.home;

import android.util.Log;

import com.masker.discover.model.entity.MyInfo;
import com.masker.discover.model.repository.UserRepository;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description:
 */

public class HomePresenter implements HomeContract.Presenter{
    private static final String TAG = "HomePresenter";

    private HomeContract.View mView;
    private CompositeSubscription mCompositeSubscription;

    public HomePresenter(HomeContract.View view){
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onUnsubscribe() {
        mView = null;
        mCompositeSubscription.clear();
    }


    @Override
    public void getMyInfo() {
        Subscription subscription = UserRepository.getMyInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MyInfo>() {
                    @Override
                    public void call(MyInfo myInfo) {
                        mView.updateMyInfo(myInfo);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                        Log.i(TAG, "call: "+throwable.getMessage());
                        Log.i(TAG, "call: "+throwable.getStackTrace());

                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
