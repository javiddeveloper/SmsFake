package com.sattar.j.smsfake.data.entity

import com.google.gson.annotations.SerializedName

/**
 * @author  : javid
 * @since   : 2020/Sep -- 3:36 PM
 * @summary : --
 */
data class Version(
        @SerializedName("versionName")val versionName: String,
        @SerializedName("versionCode")val versionCode: String,
        @SerializedName("isForce")val isForce: String,
        @SerializedName("releaseNote")val releaseNote: String,
        @SerializedName("cafebazaarLink")val cafebazaarLink: String,
        @SerializedName("myketLink")val myketLink: String,
        @SerializedName("directLink")val directLink: String
){
    override fun toString(): String {
        return "version :{$versionName, $versionCode}"
    }
}






