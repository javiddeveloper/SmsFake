package com.sattar.j.smsfake.data.repository

import com.sattar.j.smsfake.data.dao.AppDataBase
import com.sattar.j.smsfake.data.entity.UserMessage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author : DestinationRepository
 * @summary : --
 * @since : 2020/Aug -- 4:35 PM
 */
open class DestinationRepositoryImpl(
        val appDataBase: AppDataBase
) : DestinationRepository{

    private fun generatePrimitiveList(): Observable<List<UserMessage>> {
        val list = mutableListOf<UserMessage>()
        list.add(UserMessage(1, "ورود شماره دستی", ""           ,"",""))
        list.add(UserMessage(2, "وزارت بهداشت"   , "V.Behdasht" ,"",""))
        list.add(UserMessage(3, "همراه اول"      , "HAMRAH AVAL","",""))
        list.add(UserMessage(4, "ایرانسل"        , ".IRANCELL." ,"",""))
        list.add(UserMessage(5, "شارژ ایرانسل"   , "E-Charge"   ,"",""))
        list.add(UserMessage(6, "مخابرات"        , "Mokhaberat" ,"",""))
        return Observable.just(list)
    }

    override fun getDestinationListRepo(): Observable<List<UserMessage>> {
        val firstItems = generatePrimitiveList()
        val daoItems   = Observable.fromArray(appDataBase.dao().getAllList())
       return Observable.concat(firstItems,daoItems)
               .observeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addDestinationRepo(userMessage: UserMessage) {
        appDataBase.dao().addDestination(userMessage)
    }


}