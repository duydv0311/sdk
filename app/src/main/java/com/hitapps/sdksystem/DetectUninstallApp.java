package com.hitapps.sdksystem;

import android.app.Application;

import com.coolerfall.watcher.Watcher;

/**
 * Created by Duy on 1/9/2017.
 */

public class DetectUninstallApp extends Application {
    private static final String URL = "http://www.baidu.com";

    @Override
    public void onCreate() {
        super.onCreate();
        Watcher.run(this, URL, true);
    }
}
