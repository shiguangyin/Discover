package com.masker.discover.rx.event;

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
