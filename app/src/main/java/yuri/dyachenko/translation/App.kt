package yuri.dyachenko.translation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import yuri.dyachenko.translation.di.ciceroneModule
import yuri.dyachenko.translation.di.retrofitModule
import yuri.dyachenko.translation.di.schedulersModule
import yuri.dyachenko.translation.di.viewModelModule

class App : Application() {

    var searchWord = ""

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                schedulersModule,
                ciceroneModule,
                retrofitModule,
                viewModelModule
            )
        }
    }
}