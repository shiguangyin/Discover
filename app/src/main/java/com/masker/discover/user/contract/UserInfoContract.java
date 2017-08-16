package com.masker.discover.user.contract;

import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.UserInfoBean;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserInfoContract {
    public interface View extends BaseView{
        void showMyInfo(UserInfoBean infoBean);
        void showUserInfo(UserInfoBean infoBean);
        void showFollowStatusChange();
    }
    public interface Presenter{
        void loadMyInfo();
        void loadUserInfo(String userName);
        void followUser(String userName);
        void deleteFollow(String userName);
    }
}
