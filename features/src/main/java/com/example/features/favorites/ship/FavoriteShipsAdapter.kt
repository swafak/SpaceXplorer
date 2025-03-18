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
import com.example.network.model.data.ShipsResponseItem

class FavoriteShipsAdapter (
    private val onClick: (ShipsEntity) -> Unit
) : ListAdapter<ShipsEntity, FavoriteShipsAdapter.ShipsViewHolder>(ShipsViewHolder.ShipsDiffCallback()) {


    private var originalList: List<ShipsEntity> = emptyList()

    fun submitFullList(list: List<ShipsEntity>) {
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

    class ShipsViewHolder(val binding: LaunchesRecViewBinding,  private val onClick: (ShipsEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShipsEntity) {
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

        class ShipsDiffCallback : DiffUtil.ItemCallback<ShipsEntity>() {
            override fun areItemsTheSame(
                oldItem: ShipsEntity,
                newItem: ShipsEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ShipsEntity,
                newItem: ShipsEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
