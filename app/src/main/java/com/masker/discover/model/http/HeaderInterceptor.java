package com.masker.discover.model.http;


import android.text.TextUtils;
import android.util.Log;

import com.masker.discover.App;
import com.masker.discover.AppConstants;
import com.masker.discover.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: add header
 */


public class HeaderInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request.Builder newBuilder = originRequest.newBuilder();

        String token = SpUtils.getUser(App.getAppContext(),SpUtils.TOKEN);
        if(!TextUtils.isEmpty(token)){
            String val = "Bearer "+token;
            newBuilder.addHeader("Authorization",val);
        }
        else{
            String value = "Client-ID "+ AppConstants.APP_ID;
            newBuilder.addHeader("Authorization",value);
        }
        Request newRequest = newBuilder.build();
        return chain.proceed(newRequest);
    }
}
