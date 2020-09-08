package com.sattar.j.smsfake.data.network.api

import com.sattar.j.smsfake.data.entity.About
import com.sattar.j.smsfake.data.entity.Version
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author  : javid
 * @since   : 2020/Sep -- 3:12 PM
 * @summary : --
 */
interface ApiInterface {

    @GET("${ApiClient.VERSION}tools/getLastVersion.php")
    fun getCurrentVersion(): Single<Version>

    @GET("${ApiClient.VERSION}tools/getAbout.php")
    fun getAbout(): Single<List<About>>

}
