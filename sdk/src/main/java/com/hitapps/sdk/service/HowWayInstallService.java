package com.hitapps.sdk.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HowWayInstallService extends Service {
    Timer timer;
    TimerTask timerTask;
    Handler handler = new Handler();
    public HowWayInstallService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new Timer();
        Log.e("time", "start ");
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isInstall("com.surpax.ledflashlight.panel", getApplicationContext())) {
                            // post thông tin lên server
                            timer.cancel();
                            stopService(new Intent(getBaseContext(), HowWayInstallService.class));
                            Log.e("time", "isInstall and timer finish");
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 60000);
        return super.onStartCommand(intent, flags, startId);
    }

    public static boolean isInstall(String packageName, Context context) {
        Boolean aBoolean = false;
        final PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager
                .getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : installedApplications) {
            if (appInfo.packageName.trim().equals(packageName)) {
                aBoolean = true;
            }
        }
        return aBoolean;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.e("aaaaa", "onDestroy");
        super.onDestroy();
    }
}
