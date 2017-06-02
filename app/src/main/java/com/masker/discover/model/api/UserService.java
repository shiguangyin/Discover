package com.masker.discover.model.api;

import com.masker.discover.model.entity.MyInfoBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/5/12
 * Description: api about user
 */

public interface UserService {

    @GET("/me")
    Observable<MyInfoBean> getMyInfo();
}
