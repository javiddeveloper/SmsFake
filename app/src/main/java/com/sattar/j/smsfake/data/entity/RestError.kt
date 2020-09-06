package com.sattar.j.smsfake.data.entity

import com.google.gson.annotations.SerializedName

/**
 * @author  : javid
 * @since   : 2020/Sep -- 2:30 PM
 * @summary : --
 */
data class RestError(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String = "",
        @SerializedName("descriptionEn") val descriptionEn: String = "",
        @SerializedName("errorType")  val errorType: String = "",
        @SerializedName("UUID")  val UUID: String = "",
        @SerializedName("errorCode") val errorCode: Int = 0,
        @SerializedName("requestType") val requestType: Int = 0
)