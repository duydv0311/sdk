package com.hitapps.sdk.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hitapps.sdk.service.TransferShortcutService;

/**
 * Created by Duy on 1/7/2017.
 */

public class InstallAndUninstallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // uninstall call
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            //code here on uninstall
            Log.e("Uninstalled:", intent.getDataString());
            Intent intent2 = new Intent(context, TransferShortcutService.class);
            intent2.putExtra("package_uninstalled", intent.getDataString().substring(8));
            context.startService(intent2);

        }
    }
}
