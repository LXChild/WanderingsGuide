package com.cnsoft.lxchild.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by LXChild on 2015/4/14.
 */
public class MyWifiManager {

    private Context cxt;

    public MyWifiManager(Context cxt) {
        this.cxt = cxt;
    }

    public void toggleWiFi(Context context, boolean enabled) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(enabled);
    }

    public void chooseWifi() {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        cxt.startActivity(intent);
    }

    public boolean isWifiOpened() {
        WifiManager wifiManager = (WifiManager) cxt.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    public boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiNetworkInfo.isConnected();
    }
}
