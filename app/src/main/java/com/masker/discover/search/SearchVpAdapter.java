package com.masker.discover.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.masker.discover.R;
import com.masker.discover.search.fragment.SearchCollectionFragment;
import com.masker.discover.search.fragment.SearchPhotoFragment;
import com.masker.discover.search.fragment.SearchUserFragment;

/**
 * CreatedBy: masker
 * Date: 2017/6/9
 * Description:
 */

public class SearchVpAdapter extends FragmentPagerAdapter{
    private Context mContext;

    public SearchVpAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return SearchPhotoFragment.newInstance();
        }
        else if(position == 1){
            return SearchCollectionFragment.newInstance();
        }
        else if(position == 2){
            return SearchUserFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return mContext.getString(R.string.tab_photo);
        }
        else if(position == 1){
            return mContext.getString(R.string.tab_collection);
        }
        else if(position == 2){
            return mContext.getString(R.string.tab_user);
        }
        return super.getPageTitle(position);
    }
}
