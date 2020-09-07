package com.sattar.j.smsfake.data.repository

import com.sattar.j.smsfake.data.dao.AppDataBase
import com.sattar.j.smsfake.data.entity.Destination
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

    private fun generatePrimitiveList(): Observable<List<Destination>> {
        val list = mutableListOf<Destination>()
        list.add(Destination(2, "وزارت بهداشت"   , "V.Behdasht" ,"",""))
        list.add(Destination(3, "همراه اول"      , "HAMRAH AVAL","",""))
        list.add(Destination(4, "ایرانسل"        , ".IRANCELL." ,"",""))
        list.add(Destination(5, "شارژ ایرانسل"   , "E-Charge"   ,"",""))
        list.add(Destination(6, "مخابرات"        , "Mokhaberat" ,"",""))
        return Observable.just(list)
    }

    override fun getDestinationListRepo(): Observable<List<Destination>> {
        return generatePrimitiveList()
//        val firstItems = generatePrimitiveList()
//        val daoItems   = Observable.just(appDataBase.dao().getAllList())
//       return Observable.concat(firstItems,daoItems)
//               .subscribeOn(Schedulers.io())
//               .observeOn(AndroidSchedulers.mainThread())

    }

    override fun addDestinationRepo(destination: Destination) {
        appDataBase.dao().addDestination(destination)
    }


}