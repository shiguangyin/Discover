package com.masker.discover.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.masker.discover.App;
import com.masker.discover.model.entity.MyInfoBean;
import com.masker.discover.model.entity.User;
import com.masker.discover.utils.SpUtils;
import com.orhanobut.logger.Logger;

/**
 * CreatedBy: masker
 * Date: 2017/6/4
 * Description: user manager
 */

public class UserManager {
    public static final String KEY_TOKEN = "token";
    public static final String KEY_NAME = "name";
    public static final String KEY_BIO = "bio";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_AVATOR = "avator";
    public static final String KEY_EMAIL = "email";


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
        Logger.i("setToken");
    }

    public String getToken(){
        if(token != null){
            return token;
        }
        else{
            token = SpUtils.getString(SpUtils.USER,KEY_TOKEN);
        }
        Logger.i(" "+(token == null));
        return token;
    }

    public boolean isLogin(){
        return !TextUtils.isEmpty(getToken());
    }



    public void setUser(User user){
        if(user == null){
            return ;
        }
        this.user = user;
        SharedPreferences sp = App.getAppContext().getSharedPreferences(SpUtils.USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_NAME,user.getUserName());
        editor.putString(KEY_BIO,user.getBio());
        editor.putString(KEY_AVATOR,user.getAvatorUrl());
        editor.putString(KEY_LOCATION,user.getLocation());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.commit();
    }

    public User getUser(){
        if(user != null){
            return user;
        }
        else{
            SharedPreferences sp = App.getAppContext().getSharedPreferences(SpUtils.USER,Context.MODE_PRIVATE);
            if(sp.getString(KEY_NAME,null) == null){
                return null;
            }
            User user = new User();
            user.setUserName(sp.getString(KEY_NAME,null));
            user.setAvatorUrl(sp.getString(KEY_AVATOR,null));
            user.setBio(sp.getString(KEY_BIO,null));
            user.setEmail(sp.getString(KEY_EMAIL,null));
            user.setLocation(sp.getString(KEY_LOCATION,null));
            return user;
        }
    }

    public void writeMyInfo(MyInfoBean bean){
        if(bean == null){
            return;
        }
        User user = new User();
        user.setUserName(bean.getName());
        user.setLocation(bean.getLocation());
        user.setEmail(bean.getEmail());
        user.setBio(bean.getBio());
        user.setAvatorUrl(bean.getProfile_image().getLarge());
        setUser(user);
    }
}
