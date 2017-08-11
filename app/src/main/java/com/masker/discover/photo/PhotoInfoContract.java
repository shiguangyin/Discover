package com.masker.discover.photo;

import com.masker.discover.base.BaseLikeView;
import com.masker.discover.model.entity.PhotoBean;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */

public interface PhotoInfoContract {
    interface View extends BaseLikeView{
        void showPhotoInfo(PhotoBean info);
    }

    interface Presenter{
        void loadPhotoInfo(String id);
    }
}
