package com.sattar.j.smsfake.data.network.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * @author  : javid
 * @since   : 2020/Aug -- 11:20 AM
 * @summary : --
 */
object ApiClient {
    const val BASE_URL = "http://javiddev.ir/webService/sms/"
    const val VERSION = "/v1/"
//    val apiInterface: ApiInterface
//    private val retrofit: Retrofit

//    init {
//        retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        apiInterface = retrofit.create(ApiInterface::class.java)
//    }
}