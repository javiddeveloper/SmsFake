package com.sattar.j.smsfake.tools

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Telephony
import com.sattar.j.smsfake.SmsFakeApplication
import java.util.*

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Sep -- 1:33 AM
 */
class SmsTools {
    companion object {
        fun sendSms(isReceive: Boolean = true, phoneNumber: String, message: String, timeMiliSec: Long = Calendar.getInstance().timeInMillis) {
            val contentValues = ContentValues()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                contentValues.put(Telephony.Sms.ADDRESS, phoneNumber)
                contentValues.put(Telephony.Sms.DATE, timeMiliSec)
                contentValues.put(Telephony.Sms.BODY, message)
                contentValues.put(Telephony.Sms.READ, 0)
                if (isReceive)
                    SmsFakeApplication.appContext?.contentResolver?.insert(Telephony.Sms.Inbox.CONTENT_URI, contentValues)
                else
                    SmsFakeApplication.appContext?.contentResolver?.insert(Telephony.Sms.Sent.CONTENT_URI, contentValues)
            } else {
                contentValues.put(Telephony.Sms.ADDRESS, phoneNumber)
                contentValues.put(Telephony.Sms.DATE, timeMiliSec)
                contentValues.put(Telephony.Sms.BODY, message)
                contentValues.put(Telephony.Sms.READ, 0)
                if (isReceive)
                    SmsFakeApplication.appContext?.contentResolver?.insert(Uri.parse("content:  sms_icon/inbox"), contentValues)
                else
                    SmsFakeApplication.appContext?.contentResolver?.insert(Uri.parse("content:  sms_icon/sent"), contentValues)

            }
        }

        fun restoreDefaultSmsApp() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val intent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, SmsFakeApplication.lastSmsApp)
                SmsFakeApplication.appContext?.startActivity(intent)
            } else {
                val intent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, SmsFakeApplication.lastSmsApp)
                SmsFakeApplication.appContext?.startActivity(intent)
            }
        }
    }
}
