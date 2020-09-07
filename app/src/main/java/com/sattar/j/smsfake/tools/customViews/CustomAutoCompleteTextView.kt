package com.sattar.j.smsfake.tools.customViews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.SmsFakeApplication.Companion.getFont

class CustomAutoCompleteTextView : AppCompatAutoCompleteTextView {
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
    override fun enoughToFilter(): Boolean {
        return true
    }

    override fun onFocusChanged(focused: Boolean, direction: Int,
                                 previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused && filter != null) {
            performFiltering(text, 0)
        }
    }
}