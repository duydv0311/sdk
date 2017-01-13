package com.hitapps.sdksystem;

import android.app.Application;
import android.os.Build;

import cn.hiroz.uninstallfeedback.FeedbackUtils;

/**
 * Created by Duy on 1/9/2017.
 */

public class DetectUninstallApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.e("DaemonThread", "Build Brand => " + Build.BRAND);
        FeedbackUtils.openUrlWhenUninstall(this, "http://www.baidu.com");
    }
}
