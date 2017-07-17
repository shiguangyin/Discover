package com.masker.discover.model.api;

import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/5/12
 * Description: api about user
 */

public interface UserService {

    @GET("/me")
    Observable<UserInfoBean> getMyInfo();

    @GET("/user/{username}")
    Observable<UserInfoBean> getUserInfo(@Path("username")String userName);


    @GET("/users/{username}/photos")
    Observable<List<PhotoListBean>> getUserPhotos(@Path("username")String userName,
                                                  @Query("page")int page,
                                                  @Query("per_page")int perPage);

    @GET("/users/{username}/likes")
    Observable<List<PhotoListBean>> getUserLikedPhotos(@Path("username")String userName,
                                                       @Query("page")int page,
                                                       @Query("per_page")int perPage);

    @GET("/users/{username}/collections")
    Observable<List<CollectionListBean>> getUserCollections(@Path("username")String userName,
                                                            @Query("page")int page,
                                                            @Query("per_page")int perPage);
}
