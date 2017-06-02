package com.masker.discover.model.api;

import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.PhotoStatsBean;

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
    Observable<List<PhotoListBean>> getPhotos(@Query("page")int page,
                                              @Query("per_page") int perPage,
                                              @Query("order_by") String orderBy);

    @GET("/photos/{id}/info")
    Observable<PhotoBean> getPhotoInfo(@Path("id")String id);

    @GET("/photos/{id}/stats")
    Observable<PhotoStatsBean> getPhotoStats(@Path("id")String id);

    @GET("/photos/{id}/download")
    Observable<String> getPhotoDownloadUrl(@Path("id")String id);


}
