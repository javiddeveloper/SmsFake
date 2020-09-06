package com.sattar.j.smsfake.data.repository

import com.sattar.j.smsfake.data.entity.UserMessage
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 4:36 PM
 */
interface DestinationRepository {
    fun getDestinationListRepo(): Observable<List<UserMessage>>
    fun addDestinationRepo(userMessage: UserMessage)
}