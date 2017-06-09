package com.masker.discover.rxbus;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchEvent {
    private String key;

    public SearchEvent(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
