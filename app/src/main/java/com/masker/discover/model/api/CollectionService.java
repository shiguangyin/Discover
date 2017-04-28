package com.masker.discover.model.api;

import com.masker.discover.model.entity.Collection;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public interface CollectionService {

    int ALL = 0;
    int CURATED = 1;
    int FEATURED = 2;

    @GET("/collections")
    Observable<List<Collection>> getCollections(@Query("page")int page,
                                                @Query("per_page")int perPage);

    @GET("/collections/curated")
    Observable<List<Collection>> getCuratedCollections(@Query("page")int page,
                                                       @Query("per_page")int perPage);

    @GET("/collections/featured")
    Observable<List<Collection>> getFeaturedCollections(@Query("page")int page,
                                                        @Query("per_page")int perPage);

}
