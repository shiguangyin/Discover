package com.masker.discover.model.repository;

import com.masker.discover.model.api.SearchService;
import com.masker.discover.model.entity.CollectionSearchBean;
import com.masker.discover.model.entity.PhotoSearchBean;
import com.masker.discover.model.entity.UserSearchBean;
import com.masker.discover.model.http.HttpClient;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/6/6
 * Description:
 */

public class SearchRepository {

    public static Observable<PhotoSearchBean> searchPhotos(String key, int page, int perPage){
        return HttpClient.getClient().create(SearchService.class)
                .searchPhotos(key,page,perPage);
    }


    public static Observable<CollectionSearchBean> searchCollections(String key,int page,int perPage){
        return HttpClient.getClient().create(SearchService.class)
                .searchCollections(key,page,perPage);
    }

    public static Observable<UserSearchBean> searchUsers(String key,int page,int perPage){
        return HttpClient.getClient().create(SearchService.class)
                .searchUsers(key,page,perPage);
    }

}
