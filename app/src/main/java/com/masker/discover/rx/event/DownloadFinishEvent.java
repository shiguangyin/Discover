package com.masker.discover.rx.event;

/**
 * Author: masker
 * Date: 2017/8/14
 * Description:
 */

public class DownloadFinishEvent {
    private long id;

    public DownloadFinishEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
