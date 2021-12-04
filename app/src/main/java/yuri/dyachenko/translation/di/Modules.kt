package yuri.dyachenko.translation.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yuri.dyachenko.translation.api.SkyEngApi
import yuri.dyachenko.translation.impl.RetrofitDataProviderImpl
import yuri.dyachenko.translation.model.DataProvider
import yuri.dyachenko.translation.scheduler.DefaultSchedulers
import yuri.dyachenko.translation.scheduler.Schedulers
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

val schedulersModule = module {
    factory<Schedulers> {
        DefaultSchedulers()
    }
}

val viewModelModule = module {
    viewModel {
        WordsViewModel(
            dataProvider = get(),
            schedulers = get()
        )
    }
}

val retrofitModule = module {
    val baseUrl = "https://dictionary.skyeng.ru/api/public/v1/"

    single {
        GsonBuilder()
            .create()
    }

    single {
        GsonConverterFactory
            .create(get())
    }

    single {
        RxJava2CallAdapterFactory
            .create()
    }

    single {
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single {
        get<Retrofit>().create(SkyEngApi::class.java)
    }

    single<DataProvider> {
        RetrofitDataProviderImpl(get())
    }
}
