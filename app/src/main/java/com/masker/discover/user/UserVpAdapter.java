package com.masker.discover.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.masker.discover.model.entity.User;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserVpAdapter extends FragmentPagerAdapter{
    private User mUser;

    public UserVpAdapter(FragmentManager fm, User user) {
        super(fm);
        mUser = user;
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
                return UserListFragment.newInstance(UserListFragment.TYPE_PHOTOS,mUser.getUserName());
            case 1:
                return UserListFragment.newInstance(UserListFragment.TYPE_LIKES,mUser.getUserName());
            default:
                return UserListFragment.newInstance(UserListFragment.TYPE_COLLECTIONS,mUser.getUserName());
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = mUser.getTotalPhotos()+" PHOTOS";
        }
        else if(position == 1){
            title = mUser.getTotalLikes()+" LIKES";
        }
        else{
            title = mUser.getTotalCollections()+" COLLECTIONS";
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
