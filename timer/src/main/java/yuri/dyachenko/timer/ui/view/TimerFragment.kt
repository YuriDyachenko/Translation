package yuri.dyachenko.timer.ui.view

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import yuri.dyachenko.base.BaseFragment
import yuri.dyachenko.base.databinding.FragmentTimerBinding
import yuri.dyachenko.timer.R

class TimerFragment : BaseFragment(R.layout.fragment_timer) {

    private val binding by viewBinding(FragmentTimerBinding::bind)

    private val timerViewModel by viewModel<TimerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initButtons()

        timerViewModel.clearValue()
    }

    private fun initButtons() = with(binding) {
        timerStartButton.setOnClickListener { timerViewModel.start() }
        timerStopButton.setOnClickListener { timerViewModel.stop() }
        timerPauseButton.setOnClickListener { timerViewModel.pause() }
    }

    private fun initViewModel() = with(binding) {
        timerViewModel.initModel {
            timerTimeTextView.text = it
        }
    }

    companion object {
        fun newInstance() = TimerFragment()
    }
}