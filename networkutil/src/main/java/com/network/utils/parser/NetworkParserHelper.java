package com.network.utils.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.network.utils.ZencodeErrorCodes;
import com.network.utils.listener.NetWorkResultListener;
import com.network.utils.retrofit.APIClient;
import com.network.utils.retrofit.APIInterface;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkParserHelper {

    NetWorkResultListener netWorkResultListener;


    public NetworkParserHelper(String Url, String method, Object object, final NetWorkResultListener netWorkResultListener) throws IOException {
        this.netWorkResultListener = netWorkResultListener;

        Call call = null;

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        if (method.equalsIgnoreCase("POST")) {
            call = apiInterface.callPostMethod(Url, object);
        } else {
            call = apiInterface.callGetMethod(Url);
        }


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() != 200) {
                    String error = ZencodeErrorCodes.getErrorMessage(response.code());
                    netWorkResultListener.onFailure(error);

                }


                netWorkResultListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                netWorkResultListener.onFailure(t.getMessage());
                call.cancel();

            }
        });

    }
}
