package com.hitapps.sdk.helper;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class SystemInfor {
    TelephonyManager tm;

    public String getAndroidId(Context context) {
        String android_id = Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
        Log.e("a", "getAndroidId: " + android_id);
        return android_id;
    }

    public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        Log.e("a", "Android SDK: " + sdkVersion + " (" + release + ")");

        return "Android SDK: " + sdkVersion + " (" + release + ")";
    }

    public String getDeviceId(Context context) {
        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice;
        tmDevice = "" + tm.getDeviceId();
        Log.e("a", "getDeviceId: " + tmDevice);
        return tmDevice;
    }

    public String getMyPhoneNumber(Context context) {
        tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        Log.e("a", "getMyPhoneNumber: " + tm.getLine1Number());

        return tm.getLine1Number();
    }

    public String getCarrier(Context context) {
        tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        Log.e("a", "getCarrier: " + tm.getSimOperatorName());
        return tm.getSimOperatorName();
    }

    public String getNetworkCountry(Context context) {
        String locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0).getDisplayCountry();
        } else {
            locale = context.getResources().getConfiguration().locale.getDisplayCountry();
        }
        Log.e("a", "getNetworkCountry: " + locale);
        return locale;
    }

    public String getWifiName(Context context) {
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        String name = wifiInfo.getSSID();
        Log.e("a", "getWifiName: " + name);
        return name;
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        Log.e("a", "getLocalIpAddress: " + inetAddress.getHostAddress());
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }
        Log.e("a", "getMacAddress: " + macAddress);
        return macAddress;
    }

    public String getAccountMail(Context context) {
        String possibleEmail = "";
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            final Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
            Log.e("accounts","->"+accounts.length);
            for (Account account : accounts) {
                if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                    possibleEmail = account.name;
                    Log.e("a", "getAccountMail: "+possibleEmail );
                }
            }
        }
        return possibleEmail;
    }
    
    public void aaaaa(){
    }

}
