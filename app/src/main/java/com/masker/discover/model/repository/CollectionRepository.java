package com.masker.discover.model.repository;

import com.masker.discover.model.api.CollectionService;
import com.masker.discover.model.entity.CollectionBean;
import com.masker.discover.model.entity.CollectionListBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.http.HttpClient;

import java.util.List;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public class CollectionRepository {

    public static final int ALL = 0;
    public static final int CURATED = 1;
    public static final int FEATURED = 2;


    /*
     * get a collection list
     */
    public static Observable<List<CollectionListBean>> getCollectionList(int page, int perPage, int type){
        Observable<List<CollectionListBean>> observable = null;
        switch (type){
            //all
            case ALL:
                return HttpClient.getClient()
                        .create(CollectionService.class)
                        .getCollectionList(page,perPage);
            //curated
            case CURATED:
                return HttpClient.getClient()
                        .create(CollectionService.class)
                        .getCuratedCollectionList(page,perPage);
            //featured
            case FEATURED:
                return HttpClient.getClient()
                        .create(CollectionService.class)
                        .getFeaturedCollectionList(page,perPage);
            default:
                break;
        }
        return observable;
    }

    /*
     * get a collection
     */
    public static Observable<CollectionBean> getCollection(int id){
        return HttpClient.getClient().create(CollectionService.class)
                .getCollection(id);
    }



    /*
     * get photos from the collection
     */
    public static Observable<List<PhotoListBean>> getCollectionPhotos(int id,int page,int perPage){
        return HttpClient.getClient().create(CollectionService.class)
                .getCollectionPhotos(id,page,perPage);
    }



    /*
    * get a curated collection
    */
    public static Observable<CollectionBean> getCuratedCollection(int id){
        return HttpClient.getClient().create(CollectionService.class)
                .getCuratedCollection(id);
    }


    /*
    * get photos from the curated collection
    */
    public static Observable<List<PhotoListBean>> getCuratedCollectionPhotos(int id,int page,int perPage){
        return HttpClient.getClient().create(CollectionService.class)
                .getCuratedCollectionPhotos(id,page,perPage);
    }

}
