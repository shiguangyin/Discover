package com.masker.discover.model.http;

import com.masker.discover.AppConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by masker on 2017/4/26.
 */

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if( retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderInterceptor())
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                 .baseUrl(AppConstants.BASE_URL)
                 .client(client)
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                 .build();
        }
        return retrofit;
    }
}
