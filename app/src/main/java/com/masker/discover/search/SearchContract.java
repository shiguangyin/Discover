package com.masker.discover.search;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public interface SearchContract {
    interface View extends BaseView{
        void showLists(Object obj);
        void showLoading();
        void hideLoading();
    }

    interface Presenter extends BasePresenter{
        void searchCollections(String key,int page,int perPage);
        void searchPhotos(String key,int page,int perPage);
        void searchUsers(String key,int page,int perPage);
    }
}
