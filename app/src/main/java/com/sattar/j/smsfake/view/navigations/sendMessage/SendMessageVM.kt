package com.sattar.j.smsfake.view.navigations.sendMessage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sattar.j.smsfake.data.entity.Destination
import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.data.repository.version.VersionService
import com.sattar.j.smsfake.data.service.MessageService
import com.sattar.j.smsfake.data.service.ServiceResult

/**
 * @author : SendMessageVM
 * @summary : --
 * @since : 2020/Aug -- 8:00 PM
 */
class SendMessageVM(
        private val messageService: MessageService,
        private val versionService: VersionService
) : ViewModel() {
    private var destinationList = MutableLiveData<List<Destination>>()
    private var currentAppVersion = MutableLiveData<Version>()

    fun getDestinationList(): MutableLiveData<List<Destination>> {
        messageService.getAllMessage {
            when (it) {
                is ServiceResult.Success -> destinationList.value = it.data
                is ServiceResult.Error -> it.throwable.toString()
            }
        }
        return destinationList
    }

    fun getCurrentAppVersion(): MutableLiveData<Version> {
        versionService.getCurrentVersion {
            when (it) {
                is ServiceResult.Success -> currentAppVersion.value = it.data
                is ServiceResult.Error -> it.throwable.toString()
            }
        }
        return currentAppVersion
    }

    override fun onCleared() {
        super.onCleared()
        versionService.disposeObservable()
    }
}