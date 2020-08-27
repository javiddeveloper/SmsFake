package com.sattar.j.smsfake;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;


public class SmsFakeApplication extends Application {
    public static final int NORMAL_FONT = 0;
    public static final int BOLD_FONT = 1;
    public static final int MEDIUM_FONT = 2;
    public static final int LIGHT_FONT = 3;
    public static final int ULTRA_LIGHT_FONT = 4;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    public static String getFont(int type) {
        switch (type) {
            case SmsFakeApplication.LIGHT_FONT:
                return "font/IRANSansLight.ttf";
            case SmsFakeApplication.BOLD_FONT:
                return "font/IRANSansBold.ttf";
            case SmsFakeApplication.MEDIUM_FONT:
                return "font/IRANSansMedium.ttf";
            case SmsFakeApplication.ULTRA_LIGHT_FONT:
                return "font/IRANSansUltraLight.ttf";
            default:
                return "font/IRANSans.ttf";
        }
    }
}
