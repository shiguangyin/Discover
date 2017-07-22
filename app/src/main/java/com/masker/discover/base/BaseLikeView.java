package com.masker.discover.base;

import com.masker.discover.model.entity.LikeResponseBean;

/**
 * Author: masker
 * Date: 2017/7/22
 * Description:
 */

public interface BaseLikeView extends BaseView{
    void updatePhoto(LikeResponseBean bean);
    void showLikeError(String message,String id);
}
