package com.sattar.j.smsfake.tools.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.SmsFakeApplication;

public class CustomButton extends AppCompatButton {
    private TypedArray textViewCustomFont;
    private int styleFont;
    public CustomButton(Context context) {
        super(context);
        initFont(null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
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

