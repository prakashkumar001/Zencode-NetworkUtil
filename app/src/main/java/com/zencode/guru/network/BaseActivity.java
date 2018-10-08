package com.zencode.guru.network;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;



public class BaseActivity extends AppCompatActivity implements BaseView {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }




    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgessDialog(String title, String message) {
        progressDialog=new ProgressDialog(BaseActivity.this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();

    }

    @Override
    public void hideProgressDialog() {
     progressDialog.dismiss();
    }

    @Override
    public void addFragment(int containerViewId, @NonNull Fragment fragment, @NonNull String fragmentTag) {

    }


    @Override
    public void startActivityIntent(Context a, Class b, Bundle bundle) {
        Intent i=new Intent(a,b);
        i.putExtra("data",bundle);
        startActivity(i);
    }



}
