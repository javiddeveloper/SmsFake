package com.sattar.j.smsfake.customViews;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.bumptech.glide.Glide;
import jp.wasabeef.glide.transformations.BlurTransformation;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
