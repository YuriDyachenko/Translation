package yuri.dyachenko.translation.ui

import com.github.terrakok.cicerone.Screen
import yuri.dyachenko.translation.model.Word

interface Screens {

    fun words(): Screen
    fun timer(): Screen
    fun history(onChoiceFromHistory: (String) -> Unit): Screen
    fun word(word: Word): Screen
}