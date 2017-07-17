package com.masker.discover.model.http;

import com.masker.discover.global.AppConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: create a retrofit client
 */


public class HttpClient {
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
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                 .build();
        }
        return retrofit;
    }
}
