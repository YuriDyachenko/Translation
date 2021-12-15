package yuri.dyachenko.translation.ui.words

import yuri.dyachenko.model.Word

class Contract {

    sealed class State {

        data class Success(val list: List<Word>) : State()
        data class Error(val e: Throwable) : State()
        object Loading : State()
    }
}

