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
        fun getCurrentTime():String =
                SimpleDateFormat("HH:mm").format(Calendar.getInstance().time)
        fun getCurrentDate():String =  SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().time)

        fun getPersianCurrentDate():String =
                PersianDateFormat("j/F/Y").format(PersianDate())
        fun getPersianConvertDateForView(year : Int,month : Int,day : Int):String {
                val date= PersianDate().toJalali(year,month,day)
            return PersianDateFormat("j/F/Y").format(PersianDate().setShYear(date[0]).setShMonth(date[1]).setShDay(date[2]))
        }
        fun getPersianConvertDate(year : Int,month : Int,day : Int):String {
                val date= PersianDate().toJalali(year,month,day)
            return "${date[0]}/${date[1]}/${date[2]}"
        }
    }
}