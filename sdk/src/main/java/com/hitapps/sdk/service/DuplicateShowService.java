package com.hitapps.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hitapps.sdk.db.Db_SDK;
import com.hitapps.sdk.model.AppAttribute;

import java.util.ArrayList;

import static com.hitapps.sdk.helper.AppHelper.isInstalledPackage;

public class DuplicateShowService extends Service {

    Db_SDK dbSDK;
    ArrayList<AppAttribute> arrayList = new ArrayList<>();
    AppAttribute newApp;

    public DuplicateShowService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbSDK = new Db_SDK();
        if (dbSDK.getAllApp().isEmpty()) {      //db rỗng
            AppAttribute appAttribute = new AppAttribute(getPackageName(), "1", "1");
            dbSDK.insertApp(appAttribute);
            // tạo shortcut và gift ở đây
        } else { // db không rỗng
            newApp = new AppAttribute();
            newApp = dbSDK.getAppFromPackage(getPackageName());
            if (newApp == null) {
                arrayList = dbSDK.getAllApp();
                AppAttribute appAttribute;
                for (int position = 0; position < arrayList.size(); position++) {
                    appAttribute = arrayList.get(position);
                    // check app đang giữ shortcut và gift
                    if (appAttribute.getIsInstall().equals("1") && appAttribute.getIsShowing().equals("1")) {
                        if (isInstalledPackage(appAttribute.getPackageName(), this)) {
                            newApp = new AppAttribute(getPackageName(), "1", "0");
                            dbSDK.insertApp(newApp);
                        } else {
                            appAttribute.setIsInstall("0");
                            appAttribute.setIsShowing("0");
                            dbSDK.updateApp(appAttribute);
                            newApp = new AppAttribute(getPackageName(), "1", "1");
                            dbSDK.insertApp(newApp);
                            //create shortcut
                        }
                    }
                }
            } else {
                if (newApp.getIsInstall().equals("1") && newApp.getIsShowing().equals("1")) {
                    if (!isInstalledPackage(newApp.getPackageName(), this)) {
                        //create shortcut
                    } else{
                        // trường hợp không bao giờ xảy ra
                    }
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
