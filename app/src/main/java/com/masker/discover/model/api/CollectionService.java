package com.masker.discover.model.api;

import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.entity.PhotoListBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public interface CollectionService {


    @GET("/collections")
    Observable<List<CollectionListBean>> getCollectionList(@Query("page")int page,
                                                           @Query("per_page")int perPage);

    @GET("/collections/curated")
    Observable<List<CollectionListBean>> getCuratedCollectionList(@Query("page")int page,
                                                                  @Query("per_page")int perPage);

    @GET("/collections/featured")
    Observable<List<CollectionListBean>> getFeaturedCollectionList(@Query("page")int page,
                                                                   @Query("per_page")int perPage);

    @GET("/collections/{id}")
    Observable<CollectionBean> getCollection(@Path("id")int id);

    @GET("/collections/{id}/photos")
    Observable<List<PhotoListBean>> getCollectionPhotos(@Path("id")int id,
                                                        @Query("page") int page,
                                                        @Query("per_page")int perPage);
}
