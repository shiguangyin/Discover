package com.masker.discover.search.contract;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.PhotoSearchBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public interface SearchPhotosContract {
    interface View extends BaseView{
        void showPhotos(PhotoSearchBean result);
    }

    interface Presenter extends BasePresenter{
        void searchPhotos(String key,int page,int perPage);
    }

}
