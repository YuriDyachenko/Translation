package yuri.dyachenko.translation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.ext.android.inject
import yuri.dyachenko.translation.R
import yuri.dyachenko.translation.databinding.FragmentSearchDialogBinding
import yuri.dyachenko.translation.ui.Screens
import yuri.dyachenko.translation.ui.utils.hide
import yuri.dyachenko.translation.ui.utils.show
import yuri.dyachenko.translation.ui.utils.viewById

class SearchDialogFragment : BottomSheetDialogFragment() {

    private val router by inject<Router>()

    private val screens by inject<Screens>()

    private val oldWord: String by lazy {
        arguments?.getString(ARG_OLD_WORD).orEmpty()
    }

    private lateinit var onChoiceFromHistory: (String) -> Unit

    private var onSearchClickListener: OnSearchClickListener? = null

    private val searchButtonTextView: TextView by viewById(R.id.search_button_text_view)
    private val historyButtonTextView: TextView by viewById(R.id.history_button_text_view)
    private val timerButtonTextView: TextView by viewById(R.id.timer_button_text_view)
    private val searchEditText: TextInputEditText by viewById(R.id.search_edit_text)
    private val searchClearTextImageView: ImageView by viewById(R.id.search_clear_text_image_view)

    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) =
            if (searchEditText.text != null && searchEditText.text.toString().isNotEmpty()) {
                searchButtonTextView.isEnabled = true
                searchClearTextImageView.show()
            } else {
                searchButtonTextView.isEnabled = false
                searchClearTextImageView.hide()
            }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchDialogBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        searchButtonTextView.setOnClickListener(onSearchButtonClickListener)
        searchEditText.addTextChangedListener(textWatcher)
        searchEditText.setText(oldWord)
        addOnClearClickListener()

        historyButtonTextView.setOnClickListener {
            router.navigateTo(screens.history(onChoiceFromHistory))
            dismiss()
        }

        timerButtonTextView.setOnClickListener {
            router.navigateTo(screens.timer())
            dismiss()
        }
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        searchClearTextImageView.setOnClickListener {
            searchEditText.setText("")
            searchButtonTextView.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        private const val ARG_OLD_WORD = "ARG_OLD_WORD"

        fun newInstance(oldWord: String, onChoiceFromHistory: (String) -> Unit) =
            SearchDialogFragment()
                .apply {
                    this.onChoiceFromHistory = onChoiceFromHistory
                }
                .arguments(ARG_OLD_WORD to oldWord)
    }
}

fun SearchDialogFragment.arguments(vararg arguments: Pair<String, Any>): SearchDialogFragment {
    this.arguments = bundleOf(*arguments)
    return this
}
