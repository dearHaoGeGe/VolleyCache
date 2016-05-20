package com.my.volleycachedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/3/17.
 */
public class BaseApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        BaseApplication.context = getBaseContext();
    }
}
