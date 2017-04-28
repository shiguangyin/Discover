package com.masker.discover.model.repository;

import com.masker.discover.model.api.CollectionService;
import com.masker.discover.model.entity.Collection;
import com.masker.discover.model.http.ApiClient;

import java.util.List;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public class CollectionRepository {
    /*
    * generate an observable
     */
    public static Observable<List<Collection>> getCollections(int page,int perPage,int type){
        Observable<List<Collection>> observable = null;
        switch (type){
            //all
            case CollectionService.ALL:
                return ApiClient.getClient()
                        .create(CollectionService.class)
                        .getCollections(page,perPage);
            //curated
            case CollectionService.CURATED:
                return ApiClient.getClient()
                        .create(CollectionService.class)
                        .getCuratedCollections(page,perPage);
            //featured
            case CollectionService.FEATURED:
                return ApiClient.getClient()
                        .create(CollectionService.class)
                        .getFeaturedCollections(page,perPage);
            default:
                break;
        }
        return observable;
    }
}
