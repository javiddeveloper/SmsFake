package com.sattar.j.smsfake.tools

import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.sattar.j.smsfake.SmsFakeApplication

/**
 * @author  : javid
 * @since   : 2020/Sep -- 12:18 PM
 * @summary : --
 */
class Utility {
    companion object {
        fun persianToast(message: String): Toast {
            return Toast.makeText(SmsFakeApplication.appContext, message, Toast.LENGTH_SHORT).also {
                val view = it.view as LinearLayout
                val tv = view.getChildAt(0) as TextView
                val typeFace = Typeface.createFromAsset(SmsFakeApplication.appContext?.assets, SmsFakeApplication.getFont(4))
                tv.typeface = typeFace
            }
        }
    }
}