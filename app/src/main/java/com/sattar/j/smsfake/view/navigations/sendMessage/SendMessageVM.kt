package com.sattar.j.smsfake.view.navigations.sendMessage.sendMessage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sattar.j.smsfake.data.entity.UserMessage
import com.sattar.j.smsfake.data.service.MessageService
import com.sattar.j.smsfake.data.service.ServiceResult
import io.reactivex.disposables.CompositeDisposable

/**
 * @author : SendMessageVM
 * @summary : --
 * @since : 2020/Aug -- 8:00 PM
 */
class SendMessageVM(
        private val destinationRepositoryImpl: MessageService
) : ViewModel() {
    private var destinationList = MutableLiveData<List<UserMessage>>()
    private val compositeDisposable = CompositeDisposable()

    fun getDestinationList(): MutableLiveData<List<UserMessage>> {
        destinationRepositoryImpl.getAllMessage {
            when (it) {
                is ServiceResult.Success -> destinationList.value = it.data
                is ServiceResult.Error -> it.throwable.toString()
            }
        }
        return destinationList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}