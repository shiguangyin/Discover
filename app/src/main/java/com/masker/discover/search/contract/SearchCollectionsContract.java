package com.masker.discover.search.contract;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.CollectionSearchBean;


/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public interface SearchCollectionsContract {
    interface View extends BaseView{
        void showCollections(CollectionSearchBean result);
    }

    interface Presenter extends BasePresenter{
        void searchCollections(String key,int page,int perPage);
    }
}
