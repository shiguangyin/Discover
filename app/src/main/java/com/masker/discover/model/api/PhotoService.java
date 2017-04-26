package com.masker.discover.model.api;

import com.masker.discover.model.entity.Photo;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by masker on 2017/4/26.
 */

public interface PhotoService {
    String LATEST = "latest";
    String OLDEST = "oldest";
    String POPULAR = "popular";

    @GET("/photos")
    Observable<List<Photo>> getPhotos(@Query("page")int page,
                                      @Query("per_page") int perPage,
                                      @Query("order_by") String orderBy);


    @GET("/photos")
    Observable<List<Photo>> getPhotos(@Query("page")int page,
                                      @Query("order_by")String orderBy);

    @GET("/photos")
    Observable<List<Photo>> getPhotos(@Query("page")int page);


}
