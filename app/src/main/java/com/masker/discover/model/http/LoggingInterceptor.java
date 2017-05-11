package com.masker.discover.model.http;

import android.util.Log;

import com.google.gson.Gson;
import com.masker.discover.model.entity.Token;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: add loggin with okhttp client
 */


public class LoggingInterceptor implements Interceptor{
    private static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.i(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);
        Log.i(TAG, "intercept: "+response.code());

        //print response;
//        ResponseBody responseBody = response.body();
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        String strRes = buffer.clone().readString(Charset.forName("UTF-8"));
//        Log.i(TAG, "intercept: "+"response:" + strRes);


//        long t2 = System.nanoTime();
//        Log.i(TAG, String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
