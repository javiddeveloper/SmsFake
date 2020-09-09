package com.sattar.j.smsfake.data.repository.version

import com.sattar.j.smsfake.data.entity.Version
import io.reactivex.rxjava3.core.Single

/**
 * @author  : javid
 * @since   : 2020/Sep -- 4:25 PM
 * @summary : --
 */
interface VersionRepository {
    /**
     *  This fun return current version from server
     *  field isForce help you for lock application
     *  @return [Version] Model
     */
    fun getCurrentVersion(): Single<Version>

}