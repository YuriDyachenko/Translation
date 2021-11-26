package yuri.dyachenko.translation.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import yuri.dyachenko.translation.ui.words.WordsFragment

object Screens {

    fun words() = FragmentScreen { WordsFragment.newInstance() }
}