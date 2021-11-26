package yuri.dyachenko.translation.ui.words

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.ktx.moxyPresenter
import yuri.dyachenko.translation.*
import yuri.dyachenko.translation.databinding.FragmentWordsBinding
import yuri.dyachenko.translation.scheduler.DefaultSchedulers
import yuri.dyachenko.translation.ui.base.BaseFragment
import yuri.dyachenko.translation.ui.search.SearchDialogFragment

class WordsFragment : BaseFragment(R.layout.fragment_words), Contract.View {

    private val binding by viewBinding(FragmentWordsBinding::bind)

    private val presenter by moxyPresenter {
        Presenter(
            app.dataProvider,
            DefaultSchedulers()
        )
    }

    private val adapter by lazy { Adapter(presenter) }

    override fun getData() {
        presenter.onDataReady(app.searchWord)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initSearchFab()
    }

    private fun initSearchFab() = with(binding) {
        wordsSearchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance(app.searchWord)
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

    override fun setState(state: Contract.State) =
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
        showErrorSnackBar(wordsRootView, state.e) { presenter.onError() }
    }

    private fun setState() = with(binding) {
        wordsLoadingLayout.show()
        wordsSearchFab.hide()
    }

    companion object {
        fun newInstance() = WordsFragment()
    }
}