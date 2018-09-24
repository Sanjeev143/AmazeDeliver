package com.sanjeev.er.amazedeliver.model;

import android.content.Context;
import android.net.ConnectivityManager;

/* Author Sanjeev Sangral*/

public class Constant {
    public static final String baseURL = "https://mock-api-mobile.dev.lalamove.com";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lng";
    public static final String ADDRESS = "address";
    public static final String DESCRIPTION = "description";
    public static final String IMAGEURL = "imageUrl";
    public static final String RESPONSE = "response";

    public static boolean isInternetOn(Context context) {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }

}
