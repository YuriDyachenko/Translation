package yuri.dyachenko.translation.ui.words

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip
import yuri.dyachenko.translation.model.Word
import yuri.dyachenko.translation.ui.base.BasePresenter

class Contract {

    sealed class State {

        data class Success(val list: List<Word>) : State()
        data class Error(val e: Throwable) : State()
        object Loading : State()
    }

    interface View : MvpView {

        @AddToEndSingle
        fun setState(state: State)

        @Skip
        fun getData()
    }

    abstract class Presenter : BasePresenter<View>(){

        abstract fun onDataReady(searchWord: String)
    }
}

