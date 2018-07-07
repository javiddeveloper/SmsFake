package com.sattar.j.smsfake;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MyEditText extends AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
        typeYekan();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        typeYekan();
    }


    public void typeYekan(){
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
                "font/yekan.ttf");
        setTypeface(face);
    }
}

