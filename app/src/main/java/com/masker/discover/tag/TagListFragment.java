package com.masker.discover.tag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.base.BaseMvpFragment;
import com.masker.discover.model.entity.TagBean;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:tag fragment
 */

public class TagListFragment extends BaseMvpFragment implements TagListContract.View{
    private RecyclerView mRecyclerView;
    private TagListAdapter mAdapter;
    private List<TagBean> mTags;

    private TagListContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }



    @Override
    protected void initViews(View contentView) {
        mRecyclerView = getViewById(R.id.recycler_view);
        mTags = new ArrayList<>();
        mAdapter = new TagListAdapter(mTags,R.layout.rv_item_tag,getContext());
        mAdapter.enableLoadMore(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.loadTags();
    }



    @Override
    public void showTags(List<TagBean> tags) {
        mTags.addAll(tags);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }


    public static TagListFragment newInstance(){

        return new TagListFragment();
    }



    @Override
    protected void attach() {
        mPresenter = new TagListPresenter(this,getContext());
    }

    @Override
    protected void detach() {
        if(mPresenter != null){
            mPresenter.onUnsubscribe();
            mPresenter = null;
        }
    }
}
