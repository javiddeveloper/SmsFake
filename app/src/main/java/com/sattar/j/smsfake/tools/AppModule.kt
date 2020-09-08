package com.sattar.j.smsfake.tools

import androidx.room.Room
import com.sattar.j.smsfake.data.dao.AppDataBase
import com.sattar.j.smsfake.data.network.api.ApiInterface
import com.sattar.j.smsfake.data.network.api.RestConnection
import com.sattar.j.smsfake.data.repository.destination.DestinationRepository
import com.sattar.j.smsfake.data.repository.destination.DestinationRepositoryImpl
import com.sattar.j.smsfake.data.repository.version.VersionRepository
import com.sattar.j.smsfake.data.repository.version.VersionRepositoryImpl
import com.sattar.j.smsfake.data.repository.version.VersionService
import com.sattar.j.smsfake.data.repository.version.VersionServiceImpl
import com.sattar.j.smsfake.data.service.MessageService
import com.sattar.j.smsfake.data.service.messageService.MessageServiceImpl
import com.sattar.j.smsfake.view.navigations.sendMessage.sendMessage.SendMessageVM
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 5:33 PM
 */

val repository = module {
    /**
     * repository
     */
    factory<DestinationRepository> { DestinationRepositoryImpl(get()) }
    factory<VersionRepository> { VersionRepositoryImpl(get()) }

    /**
     * service
     */
    factory<MessageService> { MessageServiceImpl(get()) }
    factory<VersionService> { VersionServiceImpl(get()) }


}
val viewModels = module {
    viewModel { SendMessageVM(get()) }
}

val db = module {
    single {
        Room.databaseBuilder(androidApplication().applicationContext, AppDataBase::class.java, "appDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}

fun retrofit(baseUrl: String) = module {
    single {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    single { get<Retrofit>().create(ApiInterface::class.java) }
    factory { RestConnection(get()) }
}

