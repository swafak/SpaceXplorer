package com.example.features.favorites.ship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.room.ShipsEntity
import com.example.features.R
import com.example.features.databinding.LaunchesRecViewBinding
import com.example.features.model.Ship
import com.example.network.model.data.ShipsResponseItem

class FavoriteShipsAdapter (
    private val onClick: (Ship) -> Unit
) : ListAdapter<Ship, FavoriteShipsAdapter.ShipsViewHolder>(ShipsViewHolder.ShipsDiffCallback()) {


    private var originalList: List<Ship> = emptyList()

    fun submitFullList(list: List<Ship>) {
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

    class ShipsViewHolder(val binding: LaunchesRecViewBinding,  private val onClick: (Ship) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Ship) {
            binding.apply {
                Name.text = item.name
                date.text = item.type

//                if (!imageUrl.isNullOrEmpty()) {
                Glide.with(ImageLogo.context)
                    .load(item.image)
                    .placeholder(com.example.resources.R.drawable.baseline_rocket_24)
                    .into(ImageLogo)

                ImageLogo.setOnClickListener {
                    onClick(item)
                }

            }
        }

        class ShipsDiffCallback : DiffUtil.ItemCallback<Ship>() {
            override fun areItemsTheSame(
                oldItem: Ship,
                newItem: Ship
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Ship,
                newItem: Ship
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
