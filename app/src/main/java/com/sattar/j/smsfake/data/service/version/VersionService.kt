package com.sattar.j.smsfake.data.repository.version

import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.data.service.ServiceResult

/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:25 PM
 * @summary : --
 */
interface VersionService {
    /**
     *  This fun return current version from server
     *  field isForce help you for lock application
     *  @return [Version] Model
     */
    fun getCurrentVersion(result: (ServiceResult<Version>) -> Unit)
    fun disposeObservable()
}