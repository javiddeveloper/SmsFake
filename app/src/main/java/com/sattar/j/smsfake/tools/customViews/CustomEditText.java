package com.sattar.j.smsfake.tools.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import androidx.annotation.Nullable;

import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;
import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.SmsFakeApplication;

public class CustomEditText extends TextInputEditText {
    private TypedArray textViewCustomFont;
    private int styleFont;
    public CustomEditText(Context context) {
        super(context);
        initFont(null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(attrs);
    }

    private void initFont(AttributeSet attrs) {
        textViewCustomFont = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFont);
        styleFont = textViewCustomFont.getInt(R.styleable.CustomFont_styleFont, 0);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets()
                , SmsFakeApplication.getFont(styleFont));
        setTypeface(typeface);
    }
}

