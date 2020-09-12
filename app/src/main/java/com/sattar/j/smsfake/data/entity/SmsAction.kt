package com.sattar.j.smsfake.data.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sattar.j.smsfake.BR

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Sep -- 4:41 PM
 */
data class SmsAction (
        var isReceive: Boolean = true,
        var phoneNumber: String? = null,
        var message: String? = null,
        var messageType: Int = MESSAGE_TYPE_ALL,
//        @Bindable var time: String? = null,
        var date: String? = null
): BaseObservable() {
    var time: String? = null
    @Bindable get set(value){
        field =value
        notifyPropertyChanged(BR.time)
    }
    companion object {
        const val MESSAGE_TYPE_ALL = 0
        const val MESSAGE_TYPE_INBOX = 1
        const val MESSAGE_TYPE_SENT = 2
        const val MESSAGE_TYPE_DRAFT = 3
        const val MESSAGE_TYPE_OUTBOX = 4
        const val MESSAGE_TYPE_FAILED = 5 // for failed outgoing messages
        const val MESSAGE_TYPE_QUEUED = 6 // for messages to send later
    }
}