package com.sattar.j.smsfake.view.customViews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.SmsFakeApplication.Companion.getFont

class CustomTextView : AppCompatTextView {
    private var textViewCustomFont: TypedArray? = null
    private var styleFont = 0

    constructor(context: Context?) : super(context!!) {
        initFont(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        initFont(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        initFont(attrs)
    }

    private fun initFont(attrs: AttributeSet?) {
        textViewCustomFont = context.obtainStyledAttributes(attrs, R.styleable.CustomFont)
        styleFont = textViewCustomFont!!.getInt(R.styleable.CustomFont_styleFont, 0)
        val typeface = Typeface.createFromAsset(context.assets, getFont(styleFont))
        setTypeface(typeface)
    }
}