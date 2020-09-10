package com.sattar.j.smsfake.data.service.version

import android.annotation.SuppressLint
import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.data.repository.version.VersionRepository
import com.sattar.j.smsfake.data.repository.version.VersionService
import com.sattar.j.smsfake.data.service.ServiceResult
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:38 PM
 * @summary : --
 */
class VersionServiceImpl(
        private val versionRepository: VersionRepository
) : VersionService {
    private val diposable = CompositeDisposable()
    @SuppressLint("CheckResult")
    override fun getCurrentVersion(result: (ServiceResult<Version>) -> Unit) {
        result(ServiceResult.Loading())
        diposable.add(versionRepository.getCurrentVersion().subscribe({
            result(ServiceResult.Success(it))
//            diposable.clear()
        }, {
            result(ServiceResult.Error(it))
//            diposable.clear()
        })
        )

    }

    override fun disposeObservable() {
        diposable.dispose()
    }

}