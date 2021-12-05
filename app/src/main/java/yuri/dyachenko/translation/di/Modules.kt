package yuri.dyachenko.translation.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yuri.dyachenko.translation.api.SkyEngApi
import yuri.dyachenko.translation.impl.RetrofitDataProviderImpl
import yuri.dyachenko.translation.model.DataProvider
import yuri.dyachenko.translation.ui.AppScreens
import yuri.dyachenko.translation.ui.Screens
import yuri.dyachenko.translation.ui.words.WordsViewModel

val ciceroneModule = module {
    single {
        Cicerone.create()
    }

    single {
        get<Cicerone<Router>>().router
    }

    single {
        get<Cicerone<Router>>().getNavigatorHolder()
    }

    single<Screens> {
        AppScreens()
    }
}

val viewModelModule = module {
    viewModel {
        WordsViewModel(
            dataProvider = get()
        )
    }
}

val retrofitModule = module {
    val baseUrl = "https://dictionary.skyeng.ru/api/public/v1/"

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    .build()
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    single {
        get<Retrofit>().create(SkyEngApi::class.java)
    }

    single<DataProvider> {
        RetrofitDataProviderImpl(get())
    }
}
