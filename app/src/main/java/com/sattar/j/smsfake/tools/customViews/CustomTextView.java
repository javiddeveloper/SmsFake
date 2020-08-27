package com.sattar.j.smsfake.tools.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import android.util.AttributeSet;

import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.SmsFakeApplication;

public class CustomTextView extends AppCompatTextView {

    private TypedArray textViewCustomFont;
    private int styleFont;

    public CustomTextView(Context context) {
        super(context);
        initFont(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFont(attrs);
    }

    private void initFont(AttributeSet attrs) {
        textViewCustomFont = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFont);
        styleFont = textViewCustomFont.getInt(R.styleable.CustomFont_styleFont, 0);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets()
                , SmsFakeApplication.getFont(styleFont));
        setTypeface(typeface);
    }

//    public void initFont(@Nullable String type) {
//
//    }
}

