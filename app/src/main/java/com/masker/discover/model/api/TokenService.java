package com.masker.discover.model.api;

import com.masker.discover.model.entity.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: get token
 */

public interface TokenService {

    @FormUrlEncoded
    @POST("https://unsplash.com/oauth/token")
    Observable<Token> getToken(@Field("client_id") String clientId,
                               @Field("client_secret")String secret,
                               @Field("redirect_uri")String redirectUri,
                               @Field("code") String code,
                               @Field("grant_type")String grantType);
}
