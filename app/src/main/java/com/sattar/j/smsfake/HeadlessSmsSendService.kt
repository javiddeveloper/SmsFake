package com.sattar.j.smsfake

import android.app.IntentService
import android.content.Intent

class HeadlessSmsSendService : IntentService(HeadlessSmsSendService::class.java.name) {
    override fun onHandleIntent(intent: Intent?) {
        throw UnsupportedOperationException("Not yet implemented")
    }
}