package com.masker.discover.model.repository;

import com.masker.discover.model.api.UserService;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.http.HttpClient;

import java.util.List;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/5/12
 * Description:
 */

public class UserRepository {

   /*
    * get my info
    */
    public static Observable<UserInfoBean> getMyInfo(){
        return HttpClient.getClient()
                .create(UserService.class)
                .getMyInfo();
    }


    public static Observable<UserInfoBean> updateMyInfo(String userName, String firstName, String lastName,
                                                        String email, String url, String location, String bio, String instagramUsername){
        return HttpClient.getClient()
                .create(UserService.class)
                .updateMyInfo(userName,firstName,lastName,email,url,location,bio,instagramUsername);
    }

    public static Observable<UserInfoBean> getUserInfo(String userName){
        return HttpClient.getClient()
                .create(UserService.class)
                .getUserInfo(userName);
    }

    public static Observable<List<PhotoListBean>> getUserPhotos(String userName,int page,int perPage){
        return HttpClient.getClient()
                .create(UserService.class)
                .getUserPhotos(userName,page,perPage);
    }


    public static Observable<List<PhotoListBean>> getUserLikedPhotos(String useName,int page,int perPage){
        return HttpClient.getClient()
                .create(UserService.class)
                .getUserLikedPhotos(useName,page,perPage);
    }

    public static Observable<List<CollectionListBean>> getUserCollections(String userName,int page,int perPage){
        return HttpClient.getClient()
                .create(UserService.class)
                .getUserCollections(userName,page,perPage);
    }



}
