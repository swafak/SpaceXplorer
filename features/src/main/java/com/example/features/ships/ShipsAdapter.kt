package com.example.features.ships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.features.R
import com.example.features.databinding.LaunchesRecViewBinding
import com.example.network.model.data.ShipsResponseItem

class ShipsAdapter (
    private val onClick: (ShipsResponseItem) -> Unit
    ) : ListAdapter<ShipsResponseItem, ShipsAdapter.ShipsViewHolder>(ShipsViewHolder.ShipsDiffCallback()) {

    private var originalList: List<ShipsResponseItem> = emptyList()

    fun submitFullList(list: List<ShipsResponseItem>) {
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
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipsViewHolder {
            val binding =
                LaunchesRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ShipsViewHolder(binding, onClick)
        }


        override fun onBindViewHolder(holder: ShipsViewHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item)


        }

        class ShipsViewHolder(val binding: LaunchesRecViewBinding,  private val onClick: (ShipsResponseItem) -> Unit) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ShipsResponseItem) {
                binding.apply {
                    Name.text = item.name
                    date.text = item.type

//                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(ImageLogo.context)
                        .load(item.image)
                        .placeholder(R.drawable.baseline_rocket_24)
                        .into(ImageLogo)

                    ImageLogo.setOnClickListener {
                        onClick(item)
                    }

                }
            }

            class ShipsDiffCallback : DiffUtil.ItemCallback<ShipsResponseItem>() {
                override fun areItemsTheSame(
                    oldItem: ShipsResponseItem,
                    newItem: ShipsResponseItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ShipsResponseItem,
                    newItem: ShipsResponseItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }
