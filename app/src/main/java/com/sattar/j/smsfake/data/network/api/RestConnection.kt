package com.sattar.j.smsfake.data.network.api

import com.sattar.j.smsfake.data.entity.About
import com.sattar.j.smsfake.data.entity.Version
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.core.Single

/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:04 PM
 * @summary : --
 */
class RestConnection(
        private val apiInterface: ApiInterface
) {
//    val disposableSingleObserver = CompositeDisposable()
    fun getCurrentVersion(): Single<Version> = apiInterface.getCurrentVersion()
    fun getAbout(): Single<List<About>> = apiInterface.getAbout()
}