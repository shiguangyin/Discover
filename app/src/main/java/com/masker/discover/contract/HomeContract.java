package com.masker.discover.contract;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.Photo;

import java.util.List;

/**
 * Created by masker on 2017/4/26.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showPhotos(List<Photo> photos);
        void showError();
    }

    interface Presenter extends BasePresenter{
        void loadPhotos(int page,int perPage,String orderBy);
    }
}
