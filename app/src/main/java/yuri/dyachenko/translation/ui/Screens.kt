package yuri.dyachenko.translation.ui

import com.github.terrakok.cicerone.Screen

interface Screens {

    fun words(): Screen
    fun timer(): Screen
    fun history(onChoiceFromHistory: (String) -> Unit): Screen
}