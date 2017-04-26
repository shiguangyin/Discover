package com.masker.discover.model.repository;

import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.http.ApiClient;

import java.util.List;

import rx.Observable;

/**
 * Created by masker on 2017/4/26.
 */

public class PhotoRepository {

    public static Observable<List<Photo>> getPhoto(int page,int perPage,String orderBy){
        Observable<List<Photo>> observable = ApiClient.getClient()
                .create(PhotoService.class)
                .getPhotos(page,perPage,orderBy);
        return observable;
    }
}
