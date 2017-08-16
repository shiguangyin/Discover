package com.masker.discover.rx.event;

import com.masker.discover.model.entity.LikeResponseBean;

/**
 * Author: masker
 * Date: 2017/8/11
 * Description:
 */

public class LikeEvent {
    private LikeResponseBean likeResponse;

    public LikeEvent(LikeResponseBean likeResponse) {
        this.likeResponse = likeResponse;
    }

    public LikeResponseBean getLikeResponse() {
        return likeResponse;
    }

    public void setLikeResponse(LikeResponseBean likeResponse) {
        this.likeResponse = likeResponse;
    }
}
