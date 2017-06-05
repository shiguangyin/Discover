package com.masker.discover.photo;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/5/10
 * Description:
 */


public interface PhotoListContract {
    interface View extends BaseView {
        void showPhotos(List<PhotoListBean> photos);
        void updatePhoto(LikeResponseBean bean);
        void showLikeError(String message);
    }

    interface Presenter extends BasePresenter{
        void loadPhotos(int page,int perPage,String orderBy);
        void likePhoto(String id);
        void unlikePhoto(String id);
    }
}
