package com.hitapps.sdksystem;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hitapps.sdk.FCMMessaging;
import com.hitapps.sdk.dialog.DialogAdsAppList;
import com.hitapps.sdk.dialog.DialogAdsAppSingle;
import com.hitapps.sdk.helper.AppHelper;
import com.hitapps.sdk.helper.RetrofitClient;
import com.hitapps.sdk.helper.SystemInfor;
import com.hitapps.sdk.model.UserInfor;
import com.hitapps.sdk.model.ads.AdsApp;
import com.hitapps.sdk.model.ads.AdsNoti;
import com.hitapps.sdk.retrofitInterface.RetrofitGet;
import com.hitapps.sdk.retrofitInterface.RetrofitPost;
import com.hitapps.sdk.service.DuplicateShowService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private static final int REQUEST_CODE_EMAIL = 1;
    private static final int WRITE_REQUEST_CODE = 100;

    SystemInfor systemInfor;
    UserInfor userInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        systemInfor = new SystemInfor();
        userInfor = new UserInfor();
        userInfor.setPackage_name(getPackageName());
        userInfor.setAndroid_id(systemInfor.getAndroidId(MainActivity.this));
        userInfor.setCarrier(systemInfor.getCarrier(MainActivity.this));// nhà mạng
        userInfor.setCountry_name(Locale.getDefault().getISO3Country());
        userInfor.setVersion(systemInfor.getAndroidVersion());
        String installed_at = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date());
        userInfor.setInstall_at(installed_at);
        userInfor.setInstall_via("chplay");
        userInfor.setIp(systemInfor.getLocalIpAddress());
        userInfor.setLatitude("0.0");
        userInfor.setLongitude("0.0");
//        RetrofitPost retrofitPost = RetrofitClient.getClient().create(RetrofitPost.class);
//        Call<UserInfor> userInforCall = retrofitPost.postUserInfor(userInfor);
//        userInforCall.enqueue(new Callback<UserInfor>() {
//            @Override
//            public void onResponse(Call<UserInfor> call, Response<UserInfor> response) {
//                Log.e("aaaa", "onResponse: " + response.code() + " " + response.isSuccessful());
//            }
//
//            @Override
//            public void onFailure(Call<UserInfor> call, Throwable t) {
//                Log.e("aaaa", "aaaaaaaaa: ");
//            }
//        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitGet retrofitGet = retrofit.create(RetrofitGet.class);
        Call<AdsNoti> adsNotiCall = retrofitGet.getNoti("v2/58759e9c0f0000cc1dec8d79");
        adsNotiCall.enqueue(new Callback<AdsNoti>() {
            @Override
            public void onResponse(Call<AdsNoti> call, Response<AdsNoti> response) {
                Log.e("sssssss", "onResponse: " + response.code());
                AdsNoti adsNoti = response.body();
                if (adsNoti != null) {
                    sendNotification(adsNoti.getAppArrayList(),adsNoti);
                }
            }

            @Override
            public void onFailure(Call<AdsNoti> call, Throwable t) {

            }
        });
//        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions, WRITE_REQUEST_CODE);
//        } else {
//            startService(new Intent(MainActivity.this, DuplicateShowService.class));
//        }
    }


    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case WRITE_REQUEST_CODE:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    startService(new Intent(this, DuplicateShowService.class));
//                } else {
//                    //Permission denied.
//                    finish();
//                }
//                break;
//        }
//    }
    private void sendNotification( ArrayList<AdsApp> adsAppList,AdsNoti adsNoti) {
        AdsApp adsAppGG = null;
        AdsApp adsAppDialog = null;
        ArrayList<AdsApp> adsAppsList = new ArrayList<>();
        for (int position = 0; position < adsAppList.size(); position++) {
            if (adsAppList.get(position).getDisplay_type().equals("list")) {
                adsAppsList.add(adsAppList.get(position));
            } else if (adsAppList.get(position).getDisplay_type().equals("dialog")) {
                adsAppDialog = adsAppList.get(position);
            } else {
                adsAppGG = adsAppList.get(position);
            }
        }
        Intent intent = null;
        if (adsNoti.getDisplay_type().trim().equals("list")) {
            intent = new Intent(this, DialogAdsAppList.class);
            intent.putParcelableArrayListExtra("list", adsAppsList);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else if (adsNoti.getDisplay_type().trim().equals("dialog")) {
            intent = new Intent(this, DialogAdsAppSingle.class);
            intent.putExtra("dialog", adsAppDialog);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else {
            assert adsAppGG != null;
            AppHelper.openMarket(MainActivity.this, adsAppGG.getPackage_name());
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.BigPictureStyle no = new NotificationCompat.BigPictureStyle();
        no.bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(com.hitapps.sdk.R.mipmap.ic_launcher)// set logo hear
                .setContentTitle(adsNoti.getTitle_noti())
                .setContentText(adsNoti.getDesc_noti())
                .setStyle(no)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
