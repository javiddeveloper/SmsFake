package com.sattar.j.smsfake.data.service.messageService

import android.annotation.SuppressLint
import com.sattar.j.smsfake.data.entity.Destination
import com.sattar.j.smsfake.data.repository.DestinationRepository
import com.sattar.j.smsfake.data.service.MessageService
import com.sattar.j.smsfake.data.service.ServiceResult

/**
 * @author  : javid
 * @since   : 2020/Sep -- 2:36 PM
 * @summary : --
 */
class MessageServiceImpl(val repository: DestinationRepository) : MessageService {
    @SuppressLint("CheckResult")
    override fun getAllMessage(result: (ServiceResult<List<Destination>>) -> Unit) {
        result(ServiceResult.Loading())
        repository.getDestinationListRepo().subscribe({
            result(ServiceResult.Success(it))
        }, {
            result(ServiceResult.Error(it))
        })
    }

}