package com.sattar.j.smsfake.tools

import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author  : javid
 * @since   : 2020/Sep -- 12:31 PM
 * @summary : --
 */
class DateTools {
    companion object{
        fun getCurrentTime():String =  SimpleDateFormat("HH:mm").format(Calendar.getInstance().time)
        fun getCurrentDate():String =  SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().time)

        fun getPersianCurrentDate():String =   PersianDateFormat("j/F/Y").format(PersianDate())
    }
}