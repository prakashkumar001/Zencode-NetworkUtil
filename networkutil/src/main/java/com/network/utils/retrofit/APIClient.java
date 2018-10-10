package com.network.utils.retrofit;


import com.network.utils.common.NetWorkUtilBaseUrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                 Request.Builder builder = chain.request().newBuilder();

                for (Map.Entry<String, String> entry : NetWorkUtilBaseUrl.HEADER_PARAMS.entrySet()) {
                     builder.addHeader(entry.getKey(), entry.getValue()).build();

                }
                return chain.proceed(builder.build());
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(NetWorkUtilBaseUrl.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();



        return retrofit;
    }



}
