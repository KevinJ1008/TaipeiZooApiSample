package com.kevinj1008.taipeizooapisample;

import android.app.Application;
import android.content.Context;

public class TaipeiZoo extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }

}
