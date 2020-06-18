package com.sattar.j.smsfake.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.sattar.j.smsfake.SmsFakeApplication;

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        initFont(null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(null);
    }


    public void initFont(@Nullable String type) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), SmsFakeApplication.getFont(type));
        setTypeface(typeface);
    }
}

