package yuri.dyachenko.translation

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    var searchWord = ""

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
}