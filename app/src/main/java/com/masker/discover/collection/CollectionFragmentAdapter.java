package com.masker.discover.collection;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;

import com.masker.discover.R;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public class CollectionFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private RecyclerView.RecycledViewPool mRecycledViewPool;

    public CollectionFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return CollectionListFragment.newInstance(CollectionListFragment.ALL,mRecycledViewPool);
            case 1:
                return CollectionListFragment.newInstance(CollectionListFragment.CURATED,mRecycledViewPool);
            case 2:
                return CollectionListFragment.newInstance(CollectionListFragment.FEATURED,mRecycledViewPool);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0:
                title = mContext.getString(R.string.all);
                break;
            case 1:
                title = mContext.getString(R.string.curated);
                break;
            case 2:
                title = mContext.getString(R.string.featured);
                break;
            default:
                title = "default";
        }
        return title;
    }
}
