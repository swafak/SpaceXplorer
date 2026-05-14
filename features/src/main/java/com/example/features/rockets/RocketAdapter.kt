package com.example.features.rockets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.features.R
import com.example.features.databinding.RocketRecViewBinding
import com.example.features.model.Rocket


class RocketAdapter(
    private val onClick : (Rocket) -> Unit
) : ListAdapter<Rocket, RocketAdapter.RocketViewHolder>(RocketDiffCallback()) {

    private var originalList: List<Rocket> = emptyList()

    fun submitFullList(list: List<Rocket>) {
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

        fun bind(item: Rocket) {
            binding.apply {
                Name.text = item.name
                Glide.with(Image1.context)
                    .load(item.flickrImages?.first())
                    .placeholder(com.example.resources.R.drawable.baseline_rocket_24)
                    .into(Image1)

                Glide.with(Image2.context)
                    .load(item.flickrImages?.last())
                    .placeholder(com.example.resources.R.drawable.baseline_rocket_24)
                    .into(Image2)

                Glide.with(Image3.context)
                    .load(item.flickrImages?.get(1))
                    .placeholder(com.example.resources.R.drawable.baseline_rocket_24)
                    .into(Image3)
            }

        }
    }

    class RocketDiffCallback : DiffUtil.ItemCallback<Rocket>() {
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Rocket,
            newItem: Rocket
        ): Boolean {
            return oldItem == newItem
        }
    }
}
