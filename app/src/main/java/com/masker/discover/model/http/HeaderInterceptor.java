package com.masker.discover.model.http;


import android.util.Log;

import com.masker.discover.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by masker on 2017/4/26.
 */

public class HeaderInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        String value = "Client-ID "+ AppConstants.APP_ID;
        Request newRequest = originRequest.newBuilder()
                .addHeader("Authorization",value)
                .build();
        return chain.proceed(newRequest);
    }
}
