package com.sattar.j.smsfake;

import android.app.Application;

import com.onesignal.OneSignal;

public class myApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
