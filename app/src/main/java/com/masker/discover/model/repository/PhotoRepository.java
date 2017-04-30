package com.masker.discover.model.repository;

import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.Photo;
import com.masker.discover.model.entity.PhotoInfo;
import com.masker.discover.model.http.ApiClient;

import java.util.List;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */


public class PhotoRepository {

    public static Observable<List<Photo>> getPhoto(int page,int perPage,String orderBy){
        return  ApiClient.getClient()
                .create(PhotoService.class)
                .getPhotos(page,perPage,orderBy);
    }

    public static Observable<PhotoInfo> getPhotoInfo(String id){
        return ApiClient.getClient()
                .create(PhotoService.class)
                .getPhotoInfo(id);
    }
}
