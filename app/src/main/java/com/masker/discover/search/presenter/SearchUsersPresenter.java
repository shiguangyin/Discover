package com.masker.discover.search.presenter;

import com.masker.discover.model.entity.UserSearchBean;
import com.masker.discover.model.repository.SearchRepository;
import com.masker.discover.search.contract.SearchPhotosContract;
import com.masker.discover.search.contract.SearchUsersContract;
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

public class SearchUsersPresenter implements SearchUsersContract.Presenter{
    private CompositeSubscription mSubscriptions;
    private SearchUsersContract.View mView;

    public SearchUsersPresenter(SearchUsersContract.View view){
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onUnsubscribe() {
        mView = null;
        mSubscriptions.clear();
    }

    @Override
    public void searchUsers(String key, int page, int perPage) {
        Subscription subscription = SearchRepository.searchUsers(key,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserSearchBean>() {
                    @Override
                    public void call(UserSearchBean userSearchBean) {
                        mView.showUsers(userSearchBean);
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
