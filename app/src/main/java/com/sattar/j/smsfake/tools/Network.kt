package com.sattar.j.smsfake.tools

import android.content.Context
import android.net.ConnectivityManager
import com.sattar.j.smsfake.SmsFakeApplication


/**
 * @author  : javid
 * @since   : 2020/Sep -- 9:59 AM
 * @summary : --
 */

//fun isNetworkConnected(): Boolean{
//    val conManager: ConnectivityManager =
//
//}
class Network {
    private val context by lazy {
        SmsFakeApplication.appContext
    }
    private val connectivityManager by lazy {
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    }

    fun isNetworkConnected(): Boolean {

        return connectivityManager?.activeNetworkInfo != null
    }
}