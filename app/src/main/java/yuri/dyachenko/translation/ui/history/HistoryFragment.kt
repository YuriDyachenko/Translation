package yuri.dyachenko.translation.ui.history

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import yuri.dyachenko.base.BaseFragment
import yuri.dyachenko.translation.*
import yuri.dyachenko.translation.databinding.FragmentHistoryBinding
import yuri.dyachenko.translation.ui.utils.hide
import yuri.dyachenko.translation.ui.utils.show

class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    private val wordsViewModel by viewModel<HistoryViewModel>()

    private val router by inject<Router>()

    private lateinit var onChoiceFromHistory: (String) -> Unit

    private lateinit var adapter: Adapter

    private fun getData() {
        wordsViewModel.getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecycler()
    }

    private fun initViewModel() {
        val observer = Observer<Contract.State> {
            renderData(it)
        }
        wordsViewModel.getLiveData().observe(viewLifecycleOwner, observer)
        getData()
    }

    private fun initRecycler() = with(binding.historyRecyclerView) {
        this@HistoryFragment.adapter = Adapter {
            onChoiceFromHistory.invoke(it)
            router.exit()
        }
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = this@HistoryFragment.adapter
    }

    private fun renderData(state: Contract.State) =
        when (state) {
            is Contract.State.Success -> setState(state)
            is Contract.State.Error -> setState(state)
            Contract.State.Loading -> setState()
        }

    private fun setState(state: Contract.State.Success) = with(binding) {
        historyLoadingLayout.hide()
        adapter.submitList(state.list)
    }

    private fun setState(state: Contract.State.Error) = with(binding) {
        historyLoadingLayout.hide()
        showErrorSnackBar(historyRootView, state.e) { getData() }
    }

    private fun setState() = with(binding) {
        historyLoadingLayout.show()
    }

    companion object {
        fun newInstance(onChoiceFromHistory: (String) -> Unit) =
            HistoryFragment().apply {
                this.onChoiceFromHistory = onChoiceFromHistory
            }
    }
}