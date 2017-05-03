package com.masker.discover.model.api;

import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.entity.PhotoInfo;
import com.masker.discover.model.entity.PhotoStats;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description: api about photo
 */


public interface PhotoService {
    String LATEST = "latest";
    String OLDEST = "oldest";
    String POPULAR = "popular";

    @GET("/photos")
    Observable<List<Photo>> getPhotos(@Query("page")int page,
                                      @Query("per_page") int perPage,
                                      @Query("order_by") String orderBy);

    @GET("/photos/{id}/info")
    Observable<PhotoInfo> getPhotoInfo(@Path("id")String id);

    @GET("/photos/{id}/stats")
    Observable<PhotoStats> getPhotoStats(@Path("id")String id);

    @GET("/photos/{id}/download")
    Observable<String> getPhotoDownloadUrl(@Path("id")String id);


}
