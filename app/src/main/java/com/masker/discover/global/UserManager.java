package com.masker.discover.global;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.utils.SpUtils;
import com.orhanobut.logger.Logger;

/**
 * CreatedBy: masker
 * Date: 2017/6/4
 * Description: user manager
 */

public class UserManager {
    public static final String KEY_TOKEN = "token";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_BIO = "bio";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_AVATOR = "avator";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TOTAL_PHOTOS = "total_photos";
    public static final String KEY_TOTAL_LIKES = "total_likes";
    public static final String KEY_TOTAL_COLLECTIONS = "total_collections";


    private String token;
    private User user;

    private UserManager(){}

    private static volatile UserManager instance;

    public static UserManager getInstance(){
        if(instance == null){
            synchronized (UserManager.class){
                if(instance == null){
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public void setToken(String token){
        this.token = token;
        SpUtils.putString(SpUtils.USER,KEY_TOKEN,token);
    }

    public String getToken(){
        return SpUtils.getString(SpUtils.USER,KEY_TOKEN);
    }

    public boolean isLogin(){
        return !TextUtils.isEmpty(getToken());
    }



    public void setUser(User user){
        if(user == null){
            return ;
        }
        this.user = user;
        SharedPreferences sp = App.getApp().getSharedPreferences(SpUtils.USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_ID,user.getId());
        editor.putString(KEY_NAME,user.getName());
        editor.putString(KEY_USER_NAME,user.getUserName());
        editor.putString(KEY_BIO,user.getBio());
        editor.putString(KEY_AVATOR,user.getAvatorUrl());
        editor.putString(KEY_LOCATION,user.getLocation());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putInt(KEY_TOTAL_PHOTOS,user.getTotalPhotos());
        editor.putInt(KEY_TOTAL_LIKES,user.getTotalLikes());
        editor.putInt(KEY_TOTAL_COLLECTIONS,user.getTotalCollections());
        editor.commit();
    }

    public User getUser(){
        if(user != null){
            return user;
        }
        else{
            SharedPreferences sp = App.getApp().getSharedPreferences(SpUtils.USER,Context.MODE_PRIVATE);
            if(sp.getString(KEY_NAME,null) == null){
                return null;
            }
            User user = new User();
            user.setId(sp.getString(KEY_ID,null));
            user.setName(sp.getString(KEY_NAME,null));
            user.setUserName(sp.getString(KEY_USER_NAME,null));
            user.setAvatorUrl(sp.getString(KEY_AVATOR,null));
            user.setBio(sp.getString(KEY_BIO,null));
            user.setEmail(sp.getString(KEY_EMAIL,null));
            user.setLocation(sp.getString(KEY_LOCATION,null));
            user.setTotalPhotos(sp.getInt(KEY_TOTAL_PHOTOS,0));
            user.setTotalLikes(sp.getInt(KEY_TOTAL_LIKES,0));
            user.setTotalCollections(sp.getInt(KEY_TOTAL_COLLECTIONS,0));
            return user;
        }
    }

    public void writeMyInfo(UserInfoBean bean){
        if(bean == null){
            return;
        }
        User user = new User();
        user.setId(bean.getId());
        user.setName(bean.getName());
        user.setUserName(bean.getUsername());
        user.setLocation(bean.getLocation());
        user.setEmail(bean.getEmail());
        user.setBio(bean.getBio());
        user.setAvatorUrl(bean.getProfile_image().getLarge());
        user.setTotalPhotos(bean.getTotal_photos());
        user.setTotalLikes(bean.getTotal_likes());
        user.setTotalCollections(bean.getTotal_collections());
        setUser(user);
        Logger.i(" "+user.getTotalLikes()+" "+user.getTotalCollections());
    }

    public void logOut(){
        SharedPreferences sp = App.getApp().getSharedPreferences(SpUtils.USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sp.edit();
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_ID);
        editor.remove(KEY_NAME);
        editor.remove(KEY_USER_NAME);
        editor.remove(KEY_AVATOR);
        editor.remove(KEY_BIO);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_LOCATION);
        editor.remove(KEY_TOTAL_PHOTOS);
        editor.remove(KEY_TOTAL_LIKES);
        editor.remove(KEY_TOTAL_COLLECTIONS);
        editor.commit();
        Logger.i("is login = "+isLogin());
    }


    public static User transform(PhotoListBean.UserBean userBean){
        User user = new User();
        user.setName(userBean.getName());
        user.setUserName(userBean.getUsername());
        user.setAvatorUrl(userBean.getProfile_image().getLarge());
        user.setLocation(userBean.getLocation());
        user.setBio(userBean.getBio());
        user.setTotalPhotos(userBean.getTotal_photos());
        user.setTotalLikes(userBean.getTotal_likes());
        user.setTotalCollections(userBean.getTotal_collections());
        return user;
    }

    public static User transform(CollectionListBean.UserBeanX userBean){
        User user = new User();
        user.setName(userBean.getName());
        user.setUserName(userBean.getUsername());
        user.setAvatorUrl(userBean.getProfile_image().getLarge());
        user.setLocation(userBean.getLocation());
        user.setBio(userBean.getBio());
        user.setTotalPhotos(userBean.getTotal_photos());
        user.setTotalLikes(userBean.getTotal_likes());
        user.setTotalCollections(userBean.getTotal_collections());
        return user;
    }


    public static User transform(PhotoBean.RelatedCollectionsBean.ResultsBean.UserBeanX userBean){
        User user = new User();
        user.setName(userBean.getName());
        user.setUserName(userBean.getUsername());
        user.setAvatorUrl(userBean.getProfile_image().getLarge());
        user.setLocation(userBean.getLocation());
        user.setBio(userBean.getBio());
        user.setTotalPhotos(userBean.getTotal_photos());
        user.setTotalLikes(userBean.getTotal_likes());
        user.setTotalCollections(userBean.getTotal_collections());
        return user;
    }


}
