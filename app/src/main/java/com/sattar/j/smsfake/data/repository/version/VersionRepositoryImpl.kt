package com.sattar.j.smsfake.data.repository.version

import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.data.network.api.ApiInterface
import com.sattar.j.smsfake.tools.Network
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:38 PM
 * @summary : --
 */
class VersionRepositoryImpl(
        private val apiInterface: ApiInterface,
        private val network: Network
) : VersionRepository {
    override fun getCurrentVersion(): Single<Version> {
        return apiInterface.getCurrentVersion()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
    }

}