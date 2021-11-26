package yuri.dyachenko.translation.ui.words

import io.reactivex.rxkotlin.subscribeBy
import yuri.dyachenko.translation.model.DataProvider

class Presenter(
    private val dataProvider: DataProvider,
    private val schedulers: yuri.dyachenko.translation.scheduler.Schedulers
) : Contract.Presenter() {

    private lateinit var searchWord: String

    override fun onFirstViewAttach() = viewState.getData()

    override fun onDataReady(searchWord: String) {
        this.searchWord = searchWord
        onUpdate()
    }

    override fun onError() = getData()

    override fun onUpdate() = getData()

    private fun getData() {
        viewState.setState(Contract.State.Loading)

        if (searchWord.isEmpty()) {
            viewState.setState(Contract.State.Success(listOf()))
            return
        }

        dataProvider
            .search(searchWord)
            .subscribeOn(schedulers.background())
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = { viewState.setState(Contract.State.Success(it)) },
                onError = { viewState.setState(Contract.State.Error(it)) }
            ).autoDispose()
    }
}