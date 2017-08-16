package com.masker.discover.user.contract;

import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.UserInfoBean;

/**
 * Author: masker
 * Date: 2017/8/16
 * Description:
 */

public interface EditProfileContract {
    public interface View extends BaseView{
        void updateSuccess(UserInfoBean userInfo);
    }

    public interface Presenter{
        void updateMyInfo(String userName, String firstName, String lastName,
                          String email, String url, String location, String bio,
                          String instagramUsername);
    }
}
