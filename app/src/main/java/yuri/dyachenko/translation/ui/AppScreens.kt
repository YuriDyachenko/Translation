package yuri.dyachenko.translation.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import yuri.dyachenko.model.Word
import yuri.dyachenko.timer.ui.view.TimerFragment
import yuri.dyachenko.translation.ui.history.HistoryFragment
import yuri.dyachenko.translation.ui.word.WordFragment
import yuri.dyachenko.translation.ui.words.WordsFragment

class AppScreens : Screens {

    override fun words() = FragmentScreen { WordsFragment.newInstance() }
    override fun timer() = FragmentScreen { TimerFragment.newInstance() }
    override fun history(onChoiceFromHistory: (String) -> Unit) =
        FragmentScreen { HistoryFragment.newInstance(onChoiceFromHistory) }

    override fun word(word: Word) = FragmentScreen { WordFragment.newInstance(word) }
}