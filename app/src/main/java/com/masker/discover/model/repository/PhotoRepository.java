package com.masker.discover.model.repository;

import com.masker.discover.model.api.PhotoService;
import com.masker.discover.model.entity.LikeResponseBean;
import com.masker.discover.model.entity.PhotoListBean;
import com.masker.discover.model.entity.PhotoBean;
import com.masker.discover.model.http.ApiClient;

import java.util.List;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */


public class PhotoRepository {

    public static Observable<List<PhotoListBean>> getPhoto(int page, int perPage, String orderBy){
        return  ApiClient.getClient()
                .create(PhotoService.class)
                .getPhotos(page,perPage,orderBy);
    }

    public static Observable<PhotoBean> getPhotoInfo(String id){
        return ApiClient.getClient()
                .create(PhotoService.class)
                .getPhotoInfo(id);
    }


    public static Observable<LikeResponseBean> likePhoto(String id){
        return ApiClient.getClient()
                .create(PhotoService.class)
                .likePhoto(id);
    }

    public static Observable<LikeResponseBean> unlikePhoto(String id){
        return ApiClient.getClient()
                .create(PhotoService.class)
                .unlikePhoto(id);
    }
}
