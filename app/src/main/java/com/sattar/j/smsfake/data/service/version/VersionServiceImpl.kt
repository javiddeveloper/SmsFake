package com.sattar.j.smsfake.data.repository.version

import android.annotation.SuppressLint
import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.data.service.ServiceResult

/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:38 PM
 * @summary : --
 */
class VersionServiceImpl(
        private val versionRepository: VersionRepository
) : VersionService {
    @SuppressLint("CheckResult")
    override fun getCurrentVersion(result: (ServiceResult<Version>) -> Unit) {
        result(ServiceResult.Loading())
        versionRepository.getCurrentVersion().subscribe({
            result(ServiceResult.Success(it))
        }, {
            result(ServiceResult.Error(it))
        })
    }
}