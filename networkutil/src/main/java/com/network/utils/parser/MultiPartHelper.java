package com.network.utils.parser;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.network.utils.listener.NetWorkResultListener;
import com.network.utils.retrofit.APIClient;
import com.network.utils.retrofit.APIInterface;
import com.network.utils.upload.FileUpload;
import com.network.utils.upload.UploadService;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultiPartHelper {
    NetWorkResultListener resultListener;
    Activity activity;
    LocalBroadcastManager broadcastManager;

    public MultiPartHelper(Activity activity,String Url, String method, List<FileUpload> files, NetWorkResultListener netWorkResultListener)
    {
        broadcastManager = LocalBroadcastManager.getInstance(activity);
        broadcastManager.registerReceiver(receiver, new IntentFilter("Url"));

        this.resultListener=netWorkResultListener;

        this.activity=activity;
        uploadMultipleFile(Url,files);


    }
    void uploadMultipleFile(String Url,List<FileUpload> uploadList)

    {
        Intent intent = new Intent(activity, UploadService.class);
        intent.putExtra("url",Url);
        intent.putExtra("requestBody", (Serializable) uploadList);
        activity.startService(intent);
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String str = intent.getStringExtra("response");
                // get all your data from intent and do what you want
                resultListener.onSuccess(str);
            }

            broadcastManager.unregisterReceiver(receiver);

        }
    };





}
