package yuri.dyachenko.translation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import yuri.dyachenko.translation.ui.utils.app
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, android.R.id.content)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.dagger.inject(this)
        savedInstanceState ?: router.newRootScreen(screens.words())
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}