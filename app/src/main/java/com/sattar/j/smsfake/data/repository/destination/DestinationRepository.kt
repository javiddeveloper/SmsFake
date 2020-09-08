package com.sattar.j.smsfake.data.repository.destination

import com.sattar.j.smsfake.data.entity.Destination
import io.reactivex.Observable

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 4:36 PM
 */
interface DestinationRepository {
    fun getDestinationListRepo(): Observable<List<Destination>>
    fun addDestinationRepo(destination: Destination)
}