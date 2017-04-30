package com.masker.discover.contract;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.PhotoInfo;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */

public interface PhotoInfoContract {
    interface View extends BaseView<Presenter>{
        void showPhotoInfo(PhotoInfo info);
        void showError();
    }

    interface Presenter extends BasePresenter{
        void loadPhotoInfo(String id);
    }
}
