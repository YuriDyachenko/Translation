package yuri.dyachenko.translation

import android.app.Application
import yuri.dyachenko.translation.di.AppComponent
import yuri.dyachenko.translation.di.DaggerAppComponent

class App : Application() {

    var searchWord = ""

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        dagger = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}