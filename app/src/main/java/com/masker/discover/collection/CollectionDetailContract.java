package com.masker.discover.collection;

import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/2
 * Description:
 */

public interface CollectionDetailContract {

    interface View extends BaseView{
        void showCollection(CollectionBean bean);
        void showCollectionPhotos(List<PhotoListBean> photos);
    }

    interface Presenter{
        void loadCollection(int id);
        void loadCollectionPhotos(int id,int page,int perPage);
        void loadCuratedCollection(int id);
        void loadCuratedCollectionPhotos(int id,int page,int perPage);
    }
}
