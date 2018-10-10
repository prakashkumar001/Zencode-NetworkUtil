package com.network.utils.retrofit;


import com.google.gson.JsonObject;
import com.network.utils.common.NetWorkUtilBaseUrl;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

/*
    @POST("/api/nearbyhotels.php")
    public Call<RestaurentResponse> getRestaurent(@Body RestaurentRequest restaurentRequest);

    @POST("/upload/Multipartupload.php")
    Call<ResponseBody> createUser(@Body RequestBody files);

    @GET("/api/users?")
    Call<String> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<String> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/

    @GET
    Call<Object> callGetMethod(@Url String url);

    @POST
    public Call<Object> callPostMethod(@Url String url, @Body Object request);

    @POST
    Call<ResponseBody> multiPartUpload(@Url String url,@Body RequestBody files);

}
