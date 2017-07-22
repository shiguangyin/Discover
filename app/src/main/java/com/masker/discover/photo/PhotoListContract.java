package com.masker.discover.photo;

import com.masker.discover.base.BaseLikeView;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/5/10
 * Description:
 */


public interface PhotoListContract {
    interface View extends BaseLikeView {
        void showPhotos(List<PhotoListBean> photos);
    }

    interface Presenter{
        void loadPhotos(int page,int perPage,String orderBy);
        void likePhoto(String id);
        void unlikePhoto(String id);
    }
}
