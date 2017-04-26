package com.masker.discover.base;

/**
 * Created by masker on 2017/4/26.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
