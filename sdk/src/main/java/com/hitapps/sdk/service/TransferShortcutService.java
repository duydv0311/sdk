package com.hitapps.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.hitapps.sdk.db.Db_SDK;
import com.hitapps.sdk.helper.AppHelper;
import com.hitapps.sdk.model.AppAttribute;

public class TransferShortcutService extends Service {

    Db_SDK db_sdk;
    AppAttribute app_uninstalled;
    AppAttribute app_near_by_uninstalled;

    public TransferShortcutService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String package_uninstalled = intent.getStringExtra("package_uninstalled");
        Log.e("aaaa", "onStartCommand: " + package_uninstalled);
        db_sdk = new Db_SDK();
        if(!db_sdk.getAllApp().isEmpty()) {
            app_uninstalled = new AppAttribute();
            app_uninstalled = db_sdk.getAppFromPackage(package_uninstalled);
            if (app_uninstalled != null) {
                app_near_by_uninstalled = db_sdk.getAppFromId(app_uninstalled.getIdApp() + 1);
                if (AppHelper.isInstalledPackage(app_near_by_uninstalled.getPackageName(), this)) {
                    app_uninstalled.setIsInstall("0");
                    app_uninstalled.setIsShowing("0");
                    db_sdk.updateApp(app_uninstalled);
                    app_near_by_uninstalled.setIsShowing("1");
                    db_sdk.updateApp(app_near_by_uninstalled);
                    //creat shortcut
                } else {
                    app_near_by_uninstalled.setIsInstall("0");
                    app_near_by_uninstalled.setIsShowing("0");
                    db_sdk.updateApp(app_near_by_uninstalled);
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
