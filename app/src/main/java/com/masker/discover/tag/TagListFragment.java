package com.masker.discover.tag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.masker.discover.R;
import com.masker.discover.base.BaseAdapter;
import com.masker.discover.base.BaseMvpFragment;
import com.masker.discover.model.entity.TagBean;
import com.masker.discover.search.SearchActivity;

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
        mRecyclerView = bind(R.id.recycler_view);
        mTags = new ArrayList<>();
        mAdapter = new TagListAdapter(mTags,getContext());
        mAdapter.enableLoadMore(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                String tag = mTags.get(position).getTitle();
                SearchActivity.start(getContext(),tag);
            }
        });
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
