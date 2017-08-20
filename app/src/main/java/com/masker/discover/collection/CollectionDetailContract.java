package com.masker.discover.collection;

import com.masker.discover.base.BaseLikeView;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/2
 * Description:
 */

public interface CollectionDetailContract {

    interface View extends BaseLikeView{
        void showCollectionPhotos(List<PhotoListBean> photos);
    }

    interface Presenter{
        void loadCollectionPhotos(int id,int page,int perPage);
        void loadCuratedCollectionPhotos(int id,int page,int perPage);
    }
}
