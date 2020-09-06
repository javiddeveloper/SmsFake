package com.sattar.j.smsfake.view.navigations.sendMessage.sendMessage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sattar.j.smsfake.data.entity.UserMessage
import com.sattar.j.smsfake.data.repository.DestinationRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * @author : SendMessageVM
 * @summary : --
 * @since : 2020/Aug -- 8:00 PM
 */
class SendMessageVM(
        private val destinationRepositoryImpl: DestinationRepositoryImpl
) : ViewModel() {
    private var destinationList = MutableLiveData<List<UserMessage>>()
    private val compositeDisposable = CompositeDisposable()

    fun getDestinationList(): MutableLiveData<List<UserMessage>> {
        compositeDisposable.add(
                destinationRepositoryImpl.getDestinationListRepo().subscribe {
                    destinationList.value = it.toMutableList()
                }
        )
        return destinationList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}