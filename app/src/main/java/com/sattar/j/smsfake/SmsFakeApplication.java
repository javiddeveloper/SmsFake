package com.sattar.j.smsfake;
import android.app.Application;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import com.onesignal.OneSignal;

public class SmsFakeApplication extends Application {
    public static final String NORMAL_FONT = "";
    public static final String LIGHT_FONT  = "_light";
    public static final String BOLD_FONT   = "_bold";
    public static final String MEDIUM_FONT = "_medium";
    
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
    
    public static String getFont(@Nullable String type) {
        if (type != null)
            switch (type) {
                case SmsFakeApplication.NORMAL_FONT :return  "font/shabnam" + SmsFakeApplication.NORMAL_FONT + ".ttf";
                case SmsFakeApplication.LIGHT_FONT  :return  "font/shabnam" + SmsFakeApplication.LIGHT_FONT  + ".ttf";
                case SmsFakeApplication.BOLD_FONT   :return  "font/shabnam" + SmsFakeApplication.BOLD_FONT   + ".ttf";
                case SmsFakeApplication.MEDIUM_FONT :return  "font/shabnam" + SmsFakeApplication.MEDIUM_FONT + ".ttf";
            }
        return  "font/shabnam" + SmsFakeApplication.NORMAL_FONT + ".ttf";
    }
}
