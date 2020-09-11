package com.sattar.j.smsfake.tools

import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Telephony
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import androidx.fragment.app.Fragment
import com.sattar.j.smsfake.SmsFakeApplication
import com.sattar.j.smsfake.data.entity.SmsAction
import java.lang.reflect.Method
import java.util.*


/**
 * @author : javid
 * @summary : --
 * @since : 2020/Sep -- 1:33 AM
 */
class SmsTools {
    companion object {
        const val SMS_CODE_RESULT: Int = 1000
        var isSmsAppDefaultChanged: Boolean = false
        fun sendSms(smsAction: SmsAction) {
            val contentValues = ContentValues()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                SmsFakeApplication.appContext?.getSystemService(Context.TELEPHONY_SERVICE)
                contentValues.put(Telephony.Sms.ADDRESS, smsAction.phoneNumber)
                contentValues.put(Telephony.Sms.DATE, smsAction.timeMiliSec)
                contentValues.put(Telephony.Sms.BODY, smsAction.message)
//                contentValues.put("value", "1")
                contentValues.put(Telephony.Sms.READ, 0)
                if (smsAction.isReceive)
                    SmsFakeApplication.appContext?.contentResolver?.insert(Telephony.Sms.Inbox.CONTENT_URI, contentValues)
                else
                    SmsFakeApplication.appContext?.contentResolver?.insert(Telephony.Sms.Sent.CONTENT_URI, contentValues)
            } else {
                contentValues.put(Telephony.Sms.ADDRESS, smsAction.phoneNumber)
                contentValues.put(Telephony.Sms.DATE, smsAction.timeMiliSec)
                contentValues.put(Telephony.Sms.BODY, smsAction.message)
//                contentValues.put("value", "1")
                contentValues.put(Telephony.Sms.READ, 0)
                if (smsAction.isReceive)
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

        fun changeDefaultSmsApp(fragment: Fragment?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !isSmsAppDefaultChanged) {
                val myPackageName = fragment?.activity?.packageName
                if (Telephony.Sms.getDefaultSmsPackage(fragment?.context) != myPackageName) {
                    val intent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, myPackageName)
                    fragment?.startActivityForResult(intent, SMS_CODE_RESULT)
                } else {
                    isSmsAppDefaultChanged = true
                }
            }
        }

//        fun getSimCount(): Int {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                return (SmsFakeApplication.appContext?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).phoneCount
//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
//                val subscriptionManager = SubscriptionManager.from(SmsFakeApplication.appContext)
//                val activeSubscriptionInfoList = subscriptionManager.activeSubscriptionInfoList
//                for (subscriptionInfo in activeSubscriptionInfoList) {
//                    val carrierName = subscriptionInfo.carrierName
//                    val displayName = subscriptionInfo.displayName
//                    val mcc = subscriptionInfo.mcc
//                    val mnc = subscriptionInfo.mnc
//                    val subscriptionInfoNumber = subscriptionInfo.number
//                }
//            }
//        }
    }
}
