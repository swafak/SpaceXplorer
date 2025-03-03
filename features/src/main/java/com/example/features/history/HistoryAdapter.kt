package com.example.features.history
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.features.databinding.HistoryRecViewBinding
import com.example.network.model.data.HistoryResponseItem

class HistoryAdapter(
    private val onClick : (HistoryResponseItem) -> Unit
) : ListAdapter<HistoryResponseItem, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick(item)
        }

    }

    class HistoryViewHolder(val binding: HistoryRecViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryResponseItem) {
            binding.apply{
                linkArticle.text = item.links.article
                details.text = item.details
                eventDate.text = item.event_date_utc
                articleTitle.text = item.title

            }

        }
    }

    class HistoryDiffCallback : DiffUtil.ItemCallback<HistoryResponseItem>() {
        override fun areItemsTheSame(oldItem: HistoryResponseItem, newItem: HistoryResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryResponseItem, newItem: HistoryResponseItem): Boolean {
            return oldItem == newItem
        }
    }
}
