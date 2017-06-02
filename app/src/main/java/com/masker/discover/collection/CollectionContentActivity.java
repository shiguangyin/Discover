package com.masker.discover.collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.masker.discover.R;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

public class CollectionContentActivity extends BaseMvpActivity
        implements CollectionContentContract.View{


    private CollectionContentContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_content;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showCollection(CollectionBean bean) {

    }

    @Override
    public void showCollectionPhotos(List<PhotoListBean> photos) {

    }

    @Override
    protected void attach() {
        mPresenter = new CollectionContentPresenter(this);
    }

    @Override
    protected void detach() {
        if(mPresenter != null){
            mPresenter.onUnsubscribe();
            mPresenter = null;
        }
    }


}