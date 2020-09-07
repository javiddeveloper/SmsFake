package com.sattar.j.smsfake.data.service

import com.sattar.j.smsfake.data.entity.Destination

/**
 * @author  : javid
 * @since   : 2020/Sep -- 2:28 PM
 * @summary : --
 */
interface MessageService {
    /**
     * get all message with manual list in first List
     */
    fun getAllMessage(result: (ServiceResult<List<Destination>>)-> Unit)
}