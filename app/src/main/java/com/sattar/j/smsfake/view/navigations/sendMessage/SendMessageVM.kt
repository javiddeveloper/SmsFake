package com.sattar.j.smsfake.view.navigations.sendMessage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sattar.j.smsfake.data.dao.AppDataBase
import com.sattar.j.smsfake.data.entity.UserMessage
import com.sattar.j.smsfake.data.repository.DestinationRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author : SendMessageVM
 * @summary : --
 * @since : 2020/Aug -- 8:00 PM
 */
class SendMessageVM(
        application: Application,
        private val destinationRepositoryImpl: DestinationRepositoryImpl
) : AndroidViewModel(application) {
    private var destinationList = MutableLiveData<List<UserMessage>>()
    private val compositeDisposable = CompositeDisposable()
//    private val appDataBase = AppDataBase.invoke(application.applicationContext)
//    private val appDataBase = AppDataBase.invoke(application.applicationContext)
//    private val destinationRepositoryImpl = DestinationRepositoryImpl(application.applicationContext, appDataBase)


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