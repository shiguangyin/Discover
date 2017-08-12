package com.masker.discover.downloads;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.masker.discover.R;

/**
 * Author: masker
 * Date: 2017/8/11
 * Description:
 */

public class DownloadsPagerAdapter extends FragmentPagerAdapter{
    private Context mContext;
    public DownloadsPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new DownloadingFragment();
        }
        else if(position == 1){
            return new FinishedFragment();
        }
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return mContext.getString(R.string.title_downloading);
        }
        else if(position == 1){
            return mContext.getString(R.string.title_finished);
        }
        return super.getPageTitle(position);
    }
}
