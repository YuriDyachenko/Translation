package yuri.dyachenko.translation.ui.words

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yuri.dyachenko.translation.R
import yuri.dyachenko.translation.databinding.WordsItemLayoutBinding
import yuri.dyachenko.translation.model.Word
import yuri.dyachenko.translation.ui.utils.meaningsToString

class Adapter(
    private val onChoiceItem: (Word) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var words: MutableList<Word> = mutableListOf()

    fun submitList(list: List<Word>) {
        words.clear()
        words.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.words_item_layout, parent, false) as View
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount() = words.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = WordsItemLayoutBinding.bind(view)

        fun bind(word: Word) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.apply {
                    wordsTextTextView.text = word.text
                    wordsMeaningsTextView.text = meaningsToString(word.meanings)
                    setOnClickListener { onChoiceItem(word) }
                }
            }
        }
    }
}