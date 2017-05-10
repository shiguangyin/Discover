package com.masker.discover.tag;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.Tag;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:for tag fragment
 */

public interface TagContract {
    interface View extends BaseView<Presenter>{
        void showTags(List<Tag> tags);
        void showError();
    }

    interface Presenter extends BasePresenter{
        void loadTags();
    }
}
