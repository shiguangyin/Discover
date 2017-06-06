package com.masker.discover.model.entity;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public class PhotoSearchBean {

    private int total;
    private int total_pages;
    private List<PhotoListBean> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<PhotoListBean> getResults() {
        return results;
    }

    public void setResults(List<PhotoListBean> results) {
        this.results = results;
    }


}
