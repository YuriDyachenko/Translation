package yuri.dyachenko.translation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import yuri.dyachenko.translation.di.*

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
                ciceroneModule,
                retrofitModule,
                viewModelModule,
                timerModule,
                roomModule
            )
        }
    }
}