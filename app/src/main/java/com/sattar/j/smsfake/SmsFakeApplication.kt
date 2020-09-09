package com.sattar.j.smsfake

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.sattar.j.smsfake.data.network.api.ApiClient
import com.sattar.j.smsfake.tools.db
import com.sattar.j.smsfake.tools.repository
import com.sattar.j.smsfake.tools.retrofit
import com.sattar.j.smsfake.tools.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SmsFakeApplication : Application() {
    companion object {
        var appContext: Context? = null
        const val NORMAL_FONT = 0
        const val BOLD_FONT = 1
        const val MEDIUM_FONT = 2
        const val LIGHT_FONT = 3
        const val ULTRA_LIGHT_FONT = 4

        @JvmStatic
        fun getFont(type: Int): String {
            return when (type) {
                LIGHT_FONT -> "font/IRANSansLight.ttf"
                BOLD_FONT -> "font/IRANSansBold.ttf"
                MEDIUM_FONT -> "font/IRANSansMedium.ttf"
                ULTRA_LIGHT_FONT -> "font/IRANSansUltraLight.ttf"
                else -> "font/IRANSans.ttf"
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            // Android context
            androidContext(this@SmsFakeApplication)
            // modules
            modules(listOf(
                    viewModels,
                    retrofit(ApiClient.BASE_URL),
                    db,
                    repository))
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}