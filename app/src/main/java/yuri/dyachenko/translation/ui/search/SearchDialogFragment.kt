package yuri.dyachenko.translation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import yuri.dyachenko.translation.databinding.FragmentSearchDialogBinding
import yuri.dyachenko.translation.ui.Screens
import yuri.dyachenko.translation.ui.utils.hide
import yuri.dyachenko.translation.ui.utils.show

class SearchDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    private val router by inject<Router>()

    private val screens by inject<Screens>()

    private val oldWord: String by lazy {
        arguments?.getString(ARG_OLD_WORD).orEmpty()
    }

    private var onSearchClickListener: OnSearchClickListener? = null

    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) =
            with(binding) {
                if (searchEditText.text != null && searchEditText.text.toString().isNotEmpty()) {
                    searchButtonTextview.isEnabled = true
                    searchClearTextImageview.show()
                } else {
                    searchButtonTextview.isEnabled = false
                    searchClearTextImageview.hide()
                }
            }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentSearchDialogBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        searchEditText.addTextChangedListener(textWatcher)
        searchEditText.setText(oldWord)
        addOnClearClickListener()

        historyButtonTextview.setOnClickListener {
            router.navigateTo(screens.history())
            dismiss()
        }
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        _binding = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() = with(binding) {
        searchClearTextImageview.setOnClickListener {
            searchEditText.setText("")
            searchButtonTextview.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        private const val ARG_OLD_WORD = "ARG_OLD_WORD"

        fun newInstance(oldWord: String) =
            SearchDialogFragment()
                .arguments(ARG_OLD_WORD to oldWord)
    }
}

fun SearchDialogFragment.arguments(vararg arguments: Pair<String, Any>): SearchDialogFragment {
    this.arguments = bundleOf(*arguments)
    return this
}
