package com.sattar.j.smsfake.CustomViews;

import android.content.Context;
import android.graphics.Typeface;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import android.util.AttributeSet;

import com.sattar.j.smsfake.SmsFakeApplication;

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        initFont(null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(null);
    }


    public void initFont(@Nullable String type) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), SmsFakeApplication.getFont(type));
        setTypeface(typeface);
    }
}

