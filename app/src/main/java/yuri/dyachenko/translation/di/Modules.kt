package yuri.dyachenko.translation.di

import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yuri.dyachenko.timer.impl.ElapsedTimeCalculator
import yuri.dyachenko.timer.impl.StopwatchStateCalculator
import yuri.dyachenko.timer.impl.StopwatchStateHolder
import yuri.dyachenko.timer.impl.TimestampProviderImpl
import yuri.dyachenko.timer.model.TimestampProvider
import yuri.dyachenko.timer.ui.TimestampMillisecondsFormatter
import yuri.dyachenko.timer.ui.view.TimerFragment
import yuri.dyachenko.timer.ui.view.TimerViewModel
import yuri.dyachenko.translation.api.SkyEngApi
import yuri.dyachenko.translation.impl.RetrofitDataProviderImpl
import yuri.dyachenko.translation.impl.RoomHistoryDataProviderImpl
import yuri.dyachenko.translation.model.DataProvider
import yuri.dyachenko.translation.model.HistoryDataProvider
import yuri.dyachenko.translation.room.Storage
import yuri.dyachenko.translation.ui.AppScreens
import yuri.dyachenko.translation.ui.Screens
import yuri.dyachenko.translation.ui.history.HistoryViewModel
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
            dataProvider = get(),
            historyDataProvider = get()
        )
    }

    scope(named<TimerFragment>()) {
        viewModel {
            TimerViewModel(
                stopwatchStateHolder = get()
            )
        }
    }

    viewModel {
        HistoryViewModel(
            dataProvider = get()
        )
    }
}

val timerModule = module {
    single<TimestampProvider> {
        TimestampProviderImpl()
    }

    factory {
        StopwatchStateHolder(
            stopwatchStateCalculator = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }

    factory {
        ElapsedTimeCalculator(
            timestampProvider = get()
        )
    }

    factory {
        StopwatchStateCalculator(
            timestampProvider = get(),
            elapsedTimeCalculator = get()
        )
    }

    factory {
        TimestampMillisecondsFormatter()
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
        RetrofitDataProviderImpl(api = get())
    }
}

val roomModule = module {
    val dbName = "storage.db"

    single {
        Room.databaseBuilder(androidContext(), Storage::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    factory<HistoryDataProvider> {
        RoomHistoryDataProviderImpl(storage = get())
    }
}
