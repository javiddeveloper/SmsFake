package com.sattar.j.smsfake.tools

import android.os.Build
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sattar.j.smsfake.view.customViews.CustomImageView

/**
 * @author  : javid
 * @since   : 2020/Sep -- 11:13 AM
 * @summary : --
 */

@BindingAdapter("bind:circleImageSrc")
fun setCircleImageSrc(imageView: CustomImageView, src: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        imageView.setImageResource(src)
    } else Glide.with(imageView.context)
            .load(src)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
}