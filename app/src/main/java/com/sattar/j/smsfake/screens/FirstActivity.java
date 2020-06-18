package com.sattar.j.smsfake.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.customViews.CustomImageView;
import com.sattar.j.smsfake.customViews.CustomTextView;

public class FirstActivity extends AppCompatActivity {
    private CustomImageView background, icon;
    private CustomTextView appName;
    Context  mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }

    private void init() {
        appName = findViewById(R.id.appName);
        appName.setText(getString(R.string.app_name));
        background = findViewById(R.id.background);
        icon = findViewById(R.id.icon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            background.LoadBlure(R.drawable.sms);
        }else{
            background.Load(R.drawable.sms);
        }

        icon.Load(R.drawable.sms_icon);
    }

    @Override
    public void onBackPressed() {

    }
}
