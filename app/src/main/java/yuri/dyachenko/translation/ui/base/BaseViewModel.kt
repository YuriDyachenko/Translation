package yuri.dyachenko.translation.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    fun Disposable.autoDispose() {
        disposables.add(this)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}