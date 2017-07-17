package com.masker.discover.user;

import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.UserInfoBean;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserInfoContract {
    interface View extends BaseView{
        void showMyInfo(UserInfoBean infoBean);
        void showUserInfo(UserInfoBean infoBean);
    }
    interface Presenter{
        void loadMyInfo();
        void loadUserInfo(String userName);
    }
}
