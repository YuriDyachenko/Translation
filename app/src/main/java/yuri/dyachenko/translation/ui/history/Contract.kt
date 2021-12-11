package yuri.dyachenko.translation.ui.history

import yuri.dyachenko.translation.model.HistoryEntity

class Contract {

    sealed class State {

        data class Success(val list: List<HistoryEntity>) : State()
        data class Error(val e: Throwable) : State()
        object Loading : State()
    }
}

