package com.sattar.j.smsfake.tools

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.sattar.j.smsfake.BuildConfig
import com.sattar.j.smsfake.SmsFakeApplication

/**
 * @author  : javid
 * @since   : 2020/Sep -- 12:18 PM
 * @summary : --
 */
class Utility {
    companion object {
        fun persianToast(context: Context,message: String): Toast {
            return Toast.makeText(SmsFakeApplication.appContext, message, Toast.LENGTH_SHORT).also {
                val view = it.view as LinearLayout
                val tv = view.getChildAt(0) as TextView
                val typeFace = Typeface.createFromAsset(context.assets, SmsFakeApplication.getFont(4))
                tv.typeface = typeFace
            }
        }

        fun openUrl(url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse(url)
            SmsFakeApplication.appContext?.startActivity(intent)
        }

        fun appTypeFace(type:Int):Typeface{
            return Typeface.createFromAsset(SmsFakeApplication.appContext?.assets, SmsFakeApplication.getFont(type))
        }
    }
}