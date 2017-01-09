package com.hitapps.sdk.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Duy on 12/24/2016.
 */

public class AppHelper {
    public static void openMarket(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return;
        }
        Intent openMarketIntent = new Intent(Intent.ACTION_VIEW)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isInstalledPackage("com.android.vending", context)) {
            openMarketIntent
                    .setClassName("com.android.vending",
                            "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
            openMarketIntent.setData(Uri.parse("market://details?id="
                    + packageName));
        } else if (isInstalledPackage("com.google.market", context)) {
            openMarketIntent
                    .setClassName("com.google.market",
                            "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
            openMarketIntent.setData(Uri.parse("market://details?id="
                    + packageName));
        } else {
            openMarketIntent.setData(Uri
                    .parse("https://play.google.com/store/apps/details?id="
                            + packageName));
        }
        try {
            context.startActivity(openMarketIntent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri
                    .parse("https://play.google.com/store/apps/details?id="
                            + packageName));
            context.startActivity(intent);
            e.printStackTrace();
        }
    }

    public static boolean isInstalledPackage(String packageName, Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.MATCH_UNINSTALLED_PACKAGES) != null)
                return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
