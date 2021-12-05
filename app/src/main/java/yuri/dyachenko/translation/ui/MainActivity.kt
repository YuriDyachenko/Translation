package yuri.dyachenko.translation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, android.R.id.content)

    private val navigatorHolder by inject<NavigatorHolder>()

    private val router by inject<Router>()

    private val screens by inject<Screens>()

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(screens.words())
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}