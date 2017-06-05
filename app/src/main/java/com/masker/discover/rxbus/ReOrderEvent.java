package com.masker.discover.rxbus;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public class ReOrderEvent {
    private String order;

    public ReOrderEvent(String order){
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
