package yuri.dyachenko.translation

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import yuri.dyachenko.translation.api.SkyEngApiFactory
import yuri.dyachenko.translation.impl.RetrofitDataProviderImpl
import yuri.dyachenko.translation.model.DataProvider

class App : Application() {

    var searchWord = ""

    val dataProvider: DataProvider = RetrofitDataProviderImpl(SkyEngApiFactory.create())

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
}