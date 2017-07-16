package com.masker.discover.collection;

import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.CollectionListBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public interface CollectionListContract {

    interface View extends BaseView{
        void showCollections(List<CollectionListBean> collections);
    }

    interface Presenter {
        void loadCollections(int page,int perPage,int type);
    }
}
