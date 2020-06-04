package com.suhail.bsgbudgam.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityHelper {

    private static boolean isConnected;
    private static int type;

    public static boolean isConnected(Context context) {

        NetworkInfo activeNetwork = checkConnection(context);
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }


    private static NetworkInfo checkConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork;
    }

    public static int getConnectionType(Context context) {

        NetworkInfo activeNetwork = checkConnection(context);
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                type = 2;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                type = 1;
            }
        }
        return type;
    }


}
