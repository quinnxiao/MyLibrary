package com.quinn.mylibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Access to the network connection utility class
 */
public class IntenetUtil {  
      
    //not network connect
    public static final int NETWORN_NONE = 0;  
    //wifi connect
    public static final int NETWORN_WIFI = 1;  
    //mobile connect
    public static final int NETWORN_2G = 2;  
    public static final int NETWORN_3G = 3;  
    public static final int NETWORN_4G = 4;  
    public static final int NETWORN_MOBILE = 5;  
  
    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
          
        //No Internet connection
        if (null == connManager)  
            return NETWORN_NONE;  
          
        //Gets the current network type, if is empty, returns without network
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {  
            return NETWORN_NONE;  
        }  
          
        // If judgment is that wifi connection
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
        if (null != wifiInfo) {  
            NetworkInfo.State state = wifiInfo.getState();  
            if (null != state)  
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {  
                    return NETWORN_WIFI;  
                }  
        }  
          
        // If not wifi, then judge the current connection is operator which network 2 g, 3 g, 4 g, etc
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
  
        if (null != networkInfo) {  
            NetworkInfo.State state = networkInfo.getState();  
            String strSubTypeName = networkInfo.getSubtypeName();  
            if (null != state)  
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {  
                    switch (activeNetInfo.getSubtype()) {  
                        //If it is 2G type
                        case TelephonyManager.NETWORK_TYPE_GPRS: // Unicom's 2G
                        case TelephonyManager.NETWORK_TYPE_CDMA: // Telecom 2G
                        case TelephonyManager.NETWORK_TYPE_EDGE: // Mobile 2G
                        case TelephonyManager.NETWORK_TYPE_1xRTT:  
                        case TelephonyManager.NETWORK_TYPE_IDEN:  
                            return NETWORN_2G;  
                        //If it is 3G type
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // Telecom 3G
                        case TelephonyManager.NETWORK_TYPE_UMTS:  
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:  
                        case TelephonyManager.NETWORK_TYPE_HSDPA:  
                        case TelephonyManager.NETWORK_TYPE_HSUPA:  
                        case TelephonyManager.NETWORK_TYPE_HSPA:  
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:  
                        case TelephonyManager.NETWORK_TYPE_EHRPD:  
                        case TelephonyManager.NETWORK_TYPE_HSPAP:  
                            return NETWORN_3G;  
                        //If it is 4G type
                        case TelephonyManager.NETWORK_TYPE_LTE:  
                            return NETWORN_4G;  
                        default:  
                            //China mobile, unicom, telecom three 3G standard
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {  
                                return NETWORN_3G;  
                            } else {  
                                return NETWORN_MOBILE;  
                            }  
                    }  
                }  
        }  
        return NETWORN_NONE;  
    }  
}  