package com.masker.discover.search;

import com.masker.discover.base.BaseLikeView;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public interface SearchContract {
    interface View extends BaseLikeView{
        void showList(Object obj, boolean refresh);
        void showLoading();
        void hideLoading();
    }


    interface Presenter{
        void searchCollections(String key,int page,int perPage,boolean refresh);
        void searchPhotos(String key,int page,int perPage,boolean refresh);
        void searchUsers(String key,int page,int perPage,boolean refresh);
    }
}
