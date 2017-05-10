package com.masker.discover.collection;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.Collection;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public interface CollectionContract {

    interface View extends BaseView<Presenter>{
        void showCollections(List<Collection> collections);
        void showError();
    }

    interface Presenter extends BasePresenter{
        void loadCollections(int page,int perPage,int type);
    }
}
