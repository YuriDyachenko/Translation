package yuri.dyachenko.translation.ui.history

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yuri.dyachenko.translation.model.HistoryDataProvider
import yuri.dyachenko.translation.ui.base.BaseViewModel

class HistoryViewModel(
    private val dataProvider: HistoryDataProvider
) : BaseViewModel() {

    private val liveDataToObserve: MutableLiveData<Contract.State> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getData() {
        liveDataToObserve.value = Contract.State.Loading

        cancelJob()

        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                liveDataToObserve.postValue(Contract.State.Success(dataProvider.getAllHistory()))
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