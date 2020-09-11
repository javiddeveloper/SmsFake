package com.sattar.j.smsfake.data.entity

import java.util.*

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Sep -- 4:41 PM
 */
data class SmsAction(
        var isReceive: Boolean = true,
        var phoneNumber: String? = null,
        var message: String? = null,
        var timeMiliSec: Long = Calendar.getInstance().timeInMillis
)