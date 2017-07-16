package com.masker.discover.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserVpAdapter extends FragmentPagerAdapter{
    private String mUserName;

    public UserVpAdapter(FragmentManager fm,String userName) {
        super(fm);
        mUserName = userName;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return UserListFragment.newInstance(UserListFragment.TYPE_PHOTOS,mUserName);
            case 1:
                return UserListFragment.newInstance(UserListFragment.TYPE_LIKES,mUserName);
            default:
                return UserListFragment.newInstance(UserListFragment.TYPE_COLLECTIONS,mUserName);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = "PHOTOS";
        }
        else if(position == 1){
            title = "LIKES";
        }
        else{
            title = "COLLECTIONS";
        }
        return title;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }
}
