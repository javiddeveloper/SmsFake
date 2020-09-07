package com.sattar.j.smsfake.tools.customViews

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

class CustomImageView : AppCompatImageView {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {}

    fun setImage(res: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setImageResource(res)
        } else Glide.with(this).load(res).into(this)
    }

    fun setImageBlur(res: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setImageResource(res)
        } else Glide.with(this).load(res)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 5)))
                .into(this)
    }

    fun setImageCircle(res: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setImageResource(res)
        } else Glide.with(this)
                .load(res)
                .apply(RequestOptions.circleCropTransform())
                .into(this)
    }

    fun setImageCircle(res: Drawable) {
        Glide.with(this)
                .load(res)
                .apply(RequestOptions.circleCropTransform())
                .into(this)
    }

    fun setImageCircle(url: String) {
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(this)
    }
}