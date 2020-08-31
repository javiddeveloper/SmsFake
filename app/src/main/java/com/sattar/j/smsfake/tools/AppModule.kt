package com.sattar.j.smsfake.tools

import com.sattar.j.smsfake.data.dao.AppDataBase
import com.sattar.j.smsfake.data.repository.DestinationRepository
import com.sattar.j.smsfake.data.repository.DestinationRepositoryImpl
import com.sattar.j.smsfake.view.navigations.sendMessage.SendMessageVM
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 5:33 PM
 */

val repository = module {
    factory<DestinationRepository> { DestinationRepositoryImpl(androidApplication(), get()) }
}
val viewModels = module {
    viewModel { SendMessageVM(get(), get()) }
}
val db = module {
    single { AppDataBase }
}