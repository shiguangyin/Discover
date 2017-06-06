package com.masker.discover.search.contract;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.UserListBean;
import com.masker.discover.model.entity.UserSearchBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public interface SearchUsersContract {
    interface View extends BaseView{
        void showUsers(UserSearchBean result);
    }

    interface Presenter extends BasePresenter{
        void searchUsers(String key,int page,int perPage);
    }

}
