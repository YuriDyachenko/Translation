package yuri.dyachenko.translation.ui.timer

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import yuri.dyachenko.translation.impl.timer.StopwatchStateHolder
import yuri.dyachenko.translation.ui.base.BaseViewModel
import yuri.dyachenko.translation.ui.utils.TimestampMillisecondsFormatter

class TimerViewModel(
    private val stopwatchStateHolder: StopwatchStateHolder
) : BaseViewModel() {

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow(EMPTY_STRING)
    private val ticker: StateFlow<String> = mutableTicker

    fun initModel(action: (it: String) -> Unit) {
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        ).launch {
            ticker.collect {
                action(it)
            }
        }
    }

    fun start() {
        if (job == null) startJob()
        stopwatchStateHolder.start()
    }

    private fun startJob() {
        viewModelCoroutineScope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(TIMER_INTERVAL)
            }
        }
    }

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        cancelJob()
        job = null
    }

    fun clearValue() {
        mutableTicker.value = TimestampMillisecondsFormatter.DEFAULT_TIME
    }

    override fun handlerError(t: Throwable) {
    }

    companion object {
        const val TIMER_INTERVAL = 20L
        const val EMPTY_STRING = ""
    }
}