package yuri.dyachenko.translation.ui.words

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
import yuri.dyachenko.translation.databinding.FragmentWordsBinding
import yuri.dyachenko.translation.ui.Screens
import yuri.dyachenko.translation.ui.search.SearchDialogFragment
import yuri.dyachenko.translation.ui.utils.app
import yuri.dyachenko.translation.ui.utils.hide
import yuri.dyachenko.translation.ui.utils.show

class WordsFragment : BaseFragment(R.layout.fragment_words) {

    private val binding by viewBinding(FragmentWordsBinding::bind)

    private val wordsViewModel by viewModel<WordsViewModel>()

    private val router by inject<Router>()

    private val screens by inject<Screens>()

    private val adapter = Adapter {
        router.navigateTo(screens.word(it))
    }

    fun getData() {
        wordsViewModel.getData(app.searchWord)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecycler()
        initSearchFab()
    }

    private fun initViewModel() {
        val observer = Observer<Contract.State> {
            renderData(it)
        }
        wordsViewModel.getLiveData().observe(viewLifecycleOwner, observer)
    }

    private fun initSearchFab() = with(binding) {
        wordsSearchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance(
                app.searchWord
            ) {
                app.searchWord = it
                getData()
            }

            searchDialogFragment.setOnSearchClickListener(
                object : SearchDialogFragment.OnSearchClickListener {
                    override fun onClick(searchWord: String) {
                        app.searchWord = searchWord
                        getData()
                    }
                })
            searchDialogFragment.show(childFragmentManager, null)
        }
    }

    private fun initRecycler() = with(binding.wordsRecyclerView) {
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = this@WordsFragment.adapter
    }

    private fun renderData(state: Contract.State) =
        when (state) {
            is Contract.State.Success -> setState(state)
            is Contract.State.Error -> setState(state)
            Contract.State.Loading -> setState()
        }

    private fun setState(state: Contract.State.Success) = with(binding) {
        wordsLoadingLayout.hide()
        wordsSearchFab.show()
        adapter.submitList(state.list)
    }

    private fun setState(state: Contract.State.Error) = with(binding) {
        wordsLoadingLayout.hide()
        wordsSearchFab.hide()
        showErrorSnackBar(wordsRootView, state.e) { getData() }
    }

    private fun setState() = with(binding) {
        wordsLoadingLayout.show()
        wordsSearchFab.hide()
    }

    companion object {
        fun newInstance() = WordsFragment()
    }
}