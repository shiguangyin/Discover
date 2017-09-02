package com.masker.discover.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;

import com.masker.discover.model.entity.User;
import com.masker.discover.user.view.UserListFragment;

/**
 * Author: masker
 * Date: 2017/7/16
 * Description:
 */

public class UserVpAdapter extends FragmentPagerAdapter{
    private User mUser;
    private RecyclerView.RecycledViewPool mRecycledViewPool;

    public UserVpAdapter(FragmentManager fm, User user) {
        super(fm);
        mUser = user;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
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
                return UserListFragment.newInstance(UserListFragment.TYPE_PHOTOS,mUser.getUserName(),mUser.getTotalPhotos(),mRecycledViewPool);
            case 1:
                return UserListFragment.newInstance(UserListFragment.TYPE_LIKES,mUser.getUserName(),mUser.getTotalLikes(),mRecycledViewPool);
            default:
                return UserListFragment.newInstance(UserListFragment.TYPE_COLLECTIONS,mUser.getUserName(),mUser.getTotalCollections(),mRecycledViewPool);
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
