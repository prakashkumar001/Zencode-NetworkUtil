package com.zencode.guru.network;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.google.gson.Gson;
import com.network.utils.parser.MultiPartHelper;
import com.network.utils.parser.NetworkParserHelper;
import com.network.utils.listener.NetWorkResultListener;
import com.network.utils.upload.FileUpload;
import com.zencode.guru.network.request.Login;
import com.zencode.guru.network.response.LoginResponse;
import com.zencode.guru.network.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import siclo.com.ezphotopicker.api.EZPhotoPick;
import siclo.com.ezphotopicker.api.EZPhotoPickStorage;
import siclo.com.ezphotopicker.api.models.EZPhotoPickConfig;
import siclo.com.ezphotopicker.api.models.PhotoSource;

public class MainActivity extends BaseActivity {

    NetworkParserHelper networkParserHelper;
    MultiPartHelper multiPartParserHelper;
    Login login;

    EZPhotoPickStorage storage;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    File file;
    private String userChoosenTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = new Login("deepika1@gmail.com", "123456");

        //callLogin();
        storage=new EZPhotoPickStorage(this);

       // selectImage();

        callLogin();

    }

       void callLogin()
        {
            try {
                networkParserHelper=new NetworkParserHelper("api/login.php","POST", login, new NetWorkResultListener() {
                    @Override
                    public void onSuccess(Object object) {


                        Gson gson=new Gson();
                        LoginResponse loginResponse=gson.fromJson(new Gson().toJson(object),LoginResponse.class);

                        showMessage(loginResponse.getFirstname());

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void uploadMultipleImage(List<FileUpload> files)
        {
            multiPartParserHelper=new MultiPartHelper(this,"/upload/Multipartupload.php","POST", files, new NetWorkResultListener() {
                @Override
                public void onSuccess(Object object) {

                   showMessage(object.toString());


                }

                @Override
                public void onFailure(String error) {

                }
            });
        }






    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(MainActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == EZPhotoPick.PHOTO_PICK_GALLERY_REQUEST_CODE) {
                try {

                    ArrayList<String> pickedPhotoNames =
                            data.getStringArrayListExtra(EZPhotoPick.PICKED_PHOTO_NAMES_KEY);
                    showPickedPhotos(getString(R.string.app_name), pickedPhotoNames);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void galleryIntent() {

        EZPhotoPickConfig config = new EZPhotoPickConfig();
        config.photoSource = PhotoSource.GALLERY;
        config.needToExportThumbnail = true;
        config.isAllowMultipleSelect = true;
        config.storageDir = getString(R.string.app_name);
        config.exportingThumbSize = 200;
        config.exportingSize = 1000;
        EZPhotoPick.startPhotoPickActivity(MainActivity.this, config);
    }



    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPickedPhotos(String photoDir, List<String> photoNames) throws IOException {
        List<FileUpload> files=new ArrayList<>();

        for (String photoName : photoNames) {

            String path=storage.getAbsolutePathOfStoredPhoto(photoDir,photoName);
            files.add(new FileUpload(null,null,"media",path));
        }

        uploadMultipleImage(files);
    }


}
