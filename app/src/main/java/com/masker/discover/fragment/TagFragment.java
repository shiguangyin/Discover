package com.masker.discover.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.masker.discover.R;
import com.masker.discover.adapter.TagAdapter;
import com.masker.discover.base.BaseFragment;
import com.masker.discover.contract.HomeContract;
import com.masker.discover.contract.TagContract;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.entity.Tag;
import com.masker.discover.presenter.TagPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:tag fragment
 */

public class TagFragment extends BaseFragment implements TagContract.View{
    private RecyclerView mRecyclerView;
    private TagAdapter mAdapter;
    private List<Tag> mTags;

    private TagContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void initViews(View contentView) {
        mRecyclerView = getViewById(R.id.recycler_view);
        mTags = new ArrayList<>();
        mAdapter = new TagAdapter(mTags,R.layout.rv_item_tag,getContext());
        mAdapter.enableLoadMore(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.loadTags();
    }



    @Override
    public void showTags(List<Tag> tags) {
        mTags.addAll(tags);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void setPresenter(TagContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static TagFragment newInstance(){

        return new TagFragment();
    }
}
