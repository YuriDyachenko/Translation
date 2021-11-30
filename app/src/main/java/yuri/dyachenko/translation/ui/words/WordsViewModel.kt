package yuri.dyachenko.translation.ui.words

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import yuri.dyachenko.translation.api.SkyEngApiFactory
import yuri.dyachenko.translation.impl.RetrofitDataProviderImpl
import yuri.dyachenko.translation.model.DataProvider
import yuri.dyachenko.translation.scheduler.DefaultSchedulers
import yuri.dyachenko.translation.scheduler.Schedulers
import yuri.dyachenko.translation.ui.base.BaseViewModel

class WordsViewModel(
    private val liveDataToObserve: MutableLiveData<Contract.State> = MutableLiveData(),
    private val dataProvider: DataProvider = RetrofitDataProviderImpl(SkyEngApiFactory.create()),
    private val schedulers: Schedulers = DefaultSchedulers()
) : BaseViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getData(searchWord: String) {
        liveDataToObserve.value = Contract.State.Loading

        if (searchWord.isEmpty()) {
            liveDataToObserve.value = Contract.State.Success(listOf())
            return
        }

        dataProvider
            .search(searchWord)
            .subscribeOn(schedulers.background())
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = { liveDataToObserve.value = Contract.State.Success(it) },
                onError = { liveDataToObserve.value = Contract.State.Error(it) }
            ).autoDispose()
    }
}