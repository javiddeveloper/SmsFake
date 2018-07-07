package com.sattar.j.smsfake;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MyImageView extends AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void Load(int url){
        Glide.with(this).load(url).into(this);
    }
    public void LoadBlure(int url){
        Glide.with(this).load(url)
                .apply(bitmapTransform(new BlurTransformation(10, 5)))
                .into((this));
    }
}
