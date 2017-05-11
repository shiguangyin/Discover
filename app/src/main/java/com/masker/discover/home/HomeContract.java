package com.masker.discover.home;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: home contract
 */

public interface HomeContract {
    interface View extends BaseView {
        void updateMyInfo();
    }

    interface Presenter extends BasePresenter {
        void getMe();
    }
}
