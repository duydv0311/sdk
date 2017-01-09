package com.hitapps.sdksystem;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.hitapps.sdk.helper.SystemInfor;
import com.hitapps.sdk.service.DuplicateShowService;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private static final int REQUEST_CODE_EMAIL = 1;
    private static final int WRITE_REQUEST_CODE = 100;

    SystemInfor systemInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        systemInfor = new SystemInfor();

//        systemInfor.getDeviceId(MainActivity.this);// cung cấp quyền cho android 6.0 trở lên
//        systemInfor.getAndroidId(MainActivity.this);
//        systemInfor.getAndroidVersion();
//        systemInfor.getCarrier(MainActivity.this);// nhà mạng
//        systemInfor.getMyPhoneNumber(MainActivity.this);
//        systemInfor.getLocalIpAddress();// trả null
//        systemInfor.getAccountMail(MainActivity.this);// chưa lấy được
//        systemInfor.getNetworkCountry(MainActivity.this);
//        systemInfor.getWifiName(MainActivity.this);
//        systemInfor.getMacAddress(MainActivity.this);

        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, WRITE_REQUEST_CODE);
        } else {
            startService(new Intent(MainActivity.this, DuplicateShowService.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startService(new Intent(this, DuplicateShowService.class));
                } else {
                    //Permission denied.
                    finish();
                }
                break;
        }
    }
}
