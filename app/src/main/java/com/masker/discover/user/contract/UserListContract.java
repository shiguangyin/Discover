package com.masker.discover.user.contract;

import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserListContract {
    public interface View extends BaseView{
        void showPhotos(List<PhotoListBean> photos);
        void showLikedPhotos(List<PhotoListBean> photos);
        void showCollections(List<CollectionListBean> collections);
    }

    public interface Presenter{
        void loadPhotos(String userName,int page,int perPage);
        void loadLikedPhotos(String userName,int page,int perPage);
        void loadCollections(String userName,int page,int perPage);
    }

}
