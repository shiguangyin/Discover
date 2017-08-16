package com.masker.discover.rx.event;

import com.masker.discover.model.entity.UserInfoBean;

/**
 * Author: masker
 * Date: 2017/8/16
 * Description:
 */

public class ProfileChangeEvent {
    private UserInfoBean info;

    public ProfileChangeEvent(UserInfoBean info) {
        this.info = info;
    }

    public UserInfoBean getInfo() {
        return info;
    }

    public void setInfo(UserInfoBean info) {
        this.info = info;
    }
}
