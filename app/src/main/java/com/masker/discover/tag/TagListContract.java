package com.masker.discover.tag;

import com.masker.discover.base.BasePresenter;
import com.masker.discover.base.BaseView;
import com.masker.discover.model.entity.TagBean;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:for tag fragment
 */

public interface TagListContract {
    interface View extends BaseView{
        void showTags(List<TagBean> tags);
    }

    interface Presenter extends BasePresenter{
        void loadTags();
    }
}
