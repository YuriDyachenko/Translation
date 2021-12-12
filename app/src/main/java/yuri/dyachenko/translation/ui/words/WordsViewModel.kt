package yuri.dyachenko.translation.ui.words

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yuri.dyachenko.translation.model.DataProvider
import yuri.dyachenko.translation.model.HistoryDataProvider
import yuri.dyachenko.translation.ui.base.BaseViewModel

class WordsViewModel(
    private val dataProvider: DataProvider,
    private val historyDataProvider: HistoryDataProvider
) : BaseViewModel() {

    private val liveDataToObserve: MutableLiveData<Contract.State> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getData(searchWord: String) {
        liveDataToObserve.value = Contract.State.Loading

        if (searchWord.isEmpty()) {
            liveDataToObserve.value = Contract.State.Success(listOf())
            return
        }

        cancelJob()

        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                historyDataProvider.saveHistory(searchWord)
                liveDataToObserve.postValue(Contract.State.Success(dataProvider.search(searchWord)))
            }
        }
    }

    override fun handlerError(t: Throwable) {
        liveDataToObserve.value = Contract.State.Error(t)
    }

    override fun onCleared() {
        liveDataToObserve.value = Contract.State.Success(listOf())
        super.onCleared()
    }
}