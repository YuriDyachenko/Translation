package yuri.dyachenko.translation.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yuri.dyachenko.translation.R
import yuri.dyachenko.translation.databinding.HistoryItemLayoutBinding
import yuri.dyachenko.translation.model.HistoryEntity
import yuri.dyachenko.translation.ui.utils.format

class Adapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var entities: MutableList<HistoryEntity> = mutableListOf()

    fun submitList(list: List<HistoryEntity>) {
        entities.clear()
        entities.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item_layout, parent, false) as View
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    override fun getItemCount() = entities.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HistoryItemLayoutBinding.bind(view)

        fun bind(entity: HistoryEntity) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.apply {
                    val text = "${entity.searchDate.format()}: ${entity.word}"
                    historyItemTextView.text = text
                    setOnClickListener { onClick(entity.word) }
                }
            }
        }
    }
}