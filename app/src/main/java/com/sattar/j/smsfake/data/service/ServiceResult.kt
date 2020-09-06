package com.sattar.j.smsfake.data.service

import com.sattar.j.smsfake.data.entity.RestError


/**
 * A wrapper for database and network states.
 */
 sealed class ServiceResult<T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    class Loading<T> : ServiceResult<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ServiceResult<T>()

    /**
     * A state to show a [throwable] is thrown .
     */
    data class Error<T>(val throwable: Throwable) : ServiceResult<T>()


    data class ServerError<T>(val restError: RestError?): ServiceResult<T>()
}