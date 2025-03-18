package com.example.features.favorites.rocket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.room.RocketEntity
import com.example.features.R
import com.example.features.databinding.RocketRecViewBinding

class FavoriteRocketAdapter(private val onClick : (RocketEntity) -> Unit
) : ListAdapter<RocketEntity, FavoriteRocketAdapter.RocketViewHolder>(RocketDiffCallback()) {

    private var originalList: List<RocketEntity> = emptyList()

    fun submitFullList(list: List<RocketEntity>) {
        originalList = list
        submitList(list)
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { it.name!!.contains(query, ignoreCase = true) }
        }
        submitList(filteredList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val binding =
            RocketRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RocketViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClick(item)
        }

    }

    class RocketViewHolder(val binding: RocketRecViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RocketEntity) {
            binding.apply {
                Name.text = item.name
                Glide.with(Image1.context)
                    .load(item.flickrImages?.first())
                    .placeholder(R.drawable.baseline_rocket_24)
                    .into(Image1)

                Glide.with(Image2.context)
                    .load(item.flickrImages?.last())
                    .placeholder(R.drawable.baseline_rocket_24)
                    .into(Image2)

                Glide.with(Image3.context)
                    .load(item.flickrImages?.get(1))
                    .placeholder(R.drawable.baseline_rocket_24)
                    .into(Image3)
            }

        }
    }

    class RocketDiffCallback : DiffUtil.ItemCallback<RocketEntity>() {
        override fun areItemsTheSame(oldItem: RocketEntity, newItem: RocketEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RocketEntity,
            newItem: RocketEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}
