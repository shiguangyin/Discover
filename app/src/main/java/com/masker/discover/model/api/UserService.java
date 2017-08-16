package com.masker.discover.model.api;

import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.UserInfoBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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


    @PUT("/me")
    Observable<UserInfoBean> updateMyInfo(@Query("username")String userName,
                                          @Query("first_name")String firstName,
                                          @Query("last_name")String lastName,
                                          @Query("email")String email,
                                          @Query("url")String url,
                                          @Query("location")String location,
                                          @Query("bio")String bio,
                                          @Query("instagram_username")String instagramUsername);

    @GET("/users/{username}")
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

    /*
     * follow an user
     */
    @POST("/napi/users/{user}/follow")
    Call<ResponseBody> followUser(@Path("user")String userName);

    /*
     * delete follow
     */
    @DELETE("/napi/users/{user}/follow")
    Call<ResponseBody> deleteFollow(@Path("user")String userName);
}
