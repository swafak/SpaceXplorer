package com.example.features.favorites.dragon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.room.DragonEntity
import com.example.features.R
import com.example.features.databinding.DragonRecViewBinding

class FavoriteDragonAdapter(
    private val onClick: (DragonEntity) -> Unit
) : ListAdapter<DragonEntity, FavoriteDragonAdapter.DragonViewHolder>(DragonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonViewHolder {
        val binding = DragonRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: DragonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DragonViewHolder(
        private val binding: DragonRecViewBinding,
        private val onClick: (DragonEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DragonEntity) {
            binding.apply {
                Name.text = item.name
                type.text = item.type

                Glide.with(ImageLogo.context)
                    .load(item.flickrImages?.firstOrNull())
                    .placeholder(R.drawable.baseline_rocket_24)
                    .into(ImageLogo)

                ImageLogo.setOnClickListener { onClick(item) }
            }
        }
    }

    class DragonDiffCallback : DiffUtil.ItemCallback<DragonEntity>() {
        override fun areItemsTheSame(oldItem: DragonEntity, newItem: DragonEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DragonEntity, newItem: DragonEntity): Boolean {
            return oldItem == newItem
        }
    }
}
