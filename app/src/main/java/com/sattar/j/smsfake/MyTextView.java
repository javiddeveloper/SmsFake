package com.sattar.j.smsfake;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MyTextView  extends AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
        typeYekan();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        typeYekan();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typeYekan();
    }

    public void typeYekan(){
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
                "font/yekan.ttf");
        setTypeface(face);
    }
}

