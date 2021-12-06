package yuri.dyachenko.translation.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import yuri.dyachenko.translation.ui.timer.TimerFragment
import yuri.dyachenko.translation.ui.words.WordsFragment

class AppScreens : Screens {

    override fun words() = FragmentScreen { WordsFragment.newInstance() }
    override fun timer() = FragmentScreen { TimerFragment.newInstance() }

}