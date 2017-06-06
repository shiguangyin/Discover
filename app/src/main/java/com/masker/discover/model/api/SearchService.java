package com.masker.discover.model.api;

import com.masker.discover.model.entity.CollectionSearchBean;
import com.masker.discover.model.entity.PhotoSearchBean;
import com.masker.discover.model.entity.UserSearchBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public interface SearchService {

    @GET("/search/photos")
    Observable<PhotoSearchBean> searchPhotos(@Query("query")String key,
                                             @Query("page")int page,
                                             @Query("per_page")int perPage);


    @GET("/search/collections")
    Observable<CollectionSearchBean> searchCollections(@Query("query")String key,
                                                       @Query("page")int page,
                                                       @Query("per_page")int perPage);

    @GET("/search/users")
    Observable<UserSearchBean> searchUsers(@Query("query")String key,
                                           @Query("page")int page,
                                           @Query("per_page")int perPage);


}
