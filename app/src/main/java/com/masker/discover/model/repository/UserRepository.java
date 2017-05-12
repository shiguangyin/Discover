package com.masker.discover.model.repository;

import com.masker.discover.model.api.UserService;
import com.masker.discover.model.entity.MyInfo;
import com.masker.discover.model.http.ApiClient;

import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/5/12
 * Description:
 */

public class UserRepository {

   /*
    * get my info
    */
    public static Observable<MyInfo> getMyInfo(){
        return ApiClient.getClient()
                .create(UserService.class)
                .getMyInfo();
    }
}
