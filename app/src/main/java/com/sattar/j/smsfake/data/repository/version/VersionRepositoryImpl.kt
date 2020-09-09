package com.sattar.j.smsfake.data.repository.version

import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.data.network.api.ApiInterface
import com.sattar.j.smsfake.data.network.api.RestConnection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:38 PM
 * @summary : --
 */
class VersionRepositoryImpl(
        private val restConnection: RestConnection
) : VersionRepository {
    override fun getCurrentVersion(): Single<Version> {
        return restConnection.getCurrentVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}