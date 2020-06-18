package com.sattar.j.smsfake.CustomViews;

import android.content.Context;
import android.graphics.Typeface;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.sattar.j.smsfake.SmsFakeApplication;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        initFont(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(null);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFont(null);
    }

    public void initFont(@Nullable String type) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), SmsFakeApplication.getFont(type));
        setTypeface(typeface);
    }
}

