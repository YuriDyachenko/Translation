package yuri.dyachenko.translation.ui.word

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import yuri.dyachenko.model.Word
import yuri.dyachenko.model.meaningsToString
import yuri.dyachenko.translation.R
import yuri.dyachenko.translation.databinding.FragmentWordBinding

class WordFragment : yuri.dyachenko.base.BaseFragment(R.layout.fragment_word) {

    private val binding by viewBinding(FragmentWordBinding::bind)

    private lateinit var word: Word

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        wordTextTextView.text = word.text
        wordMeaningsTextView.text = meaningsToString(word.meanings)
        Picasso
            .get()
            .load(HTTPS + word.meanings?.get(0)?.imageUrl)
            .into(wordMeaningImageView)
    }

    companion object {
        const val HTTPS = "https://"

        fun newInstance(word: Word) = WordFragment()
            .apply {
                this.word = word
            }
    }
}