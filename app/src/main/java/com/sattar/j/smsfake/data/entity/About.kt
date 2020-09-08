package com.sattar.j.smsfake.data.entity

import com.google.gson.annotations.SerializedName

/**
 * @author  : javid
 * @since   : 2020/Sep -- 3:42 PM
 * @summary : --
 */
data class About(
    @SerializedName("name")        val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("iconLink")    val iconLink : String,
    @SerializedName("link")        val link : String
){
    companion object{
        const val INSTAGRAM        ="instagram"
        const val SITE             ="site"
        const val TELEGRAM         ="telegram"
        const val LINKEDIN         ="linkedin"
        const val APPLICATION_HELP ="applicationHelp"
        const val DEVELOPER        ="developer"
    }
}