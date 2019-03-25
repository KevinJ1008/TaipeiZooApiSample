package com.kevinj1008.taipeizooapisample.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtil {

    private static final int TYPE_NOT_CONNECTED = 0;
    private static final int TYPE_CONNECTED = 1;

    public static final int NETWORK_STATUS_NOT_CONNECTED = 2;
    public static final int NETWORK_STATUS_CONNECTED = 3;

    private Context mContext;

    /**
     * Public constructor that takes mContext for later use
     */
    public NetworkUtil(Context context) {
        mContext = context;
    }

    //This is a method to Check if the device internet connection is currently on
    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        Log.d(Constants.TAG, "Network check: " + connectivityManager.isActiveNetworkMetered());

        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && connectivityManager.isActiveNetworkMetered();

    }

    private static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && connectivityManager.isActiveNetworkMetered()) {
            return TYPE_CONNECTED;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static int getConnectivityStatusString(Context context) {
        int connectivityStatus = NetworkUtil.getConnectivityStatus(context);
        int statusCode = 0;
        if (connectivityStatus == NetworkUtil.TYPE_CONNECTED) {
            statusCode = NETWORK_STATUS_CONNECTED;
        } else if (connectivityStatus == NetworkUtil.TYPE_NOT_CONNECTED) {
            statusCode = NETWORK_STATUS_NOT_CONNECTED;
        }
        return statusCode;
    }

}
