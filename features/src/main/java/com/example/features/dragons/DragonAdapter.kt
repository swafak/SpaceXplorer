package com.example.features.dragons
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.features.R
import com.example.features.databinding.DragonRecViewBinding

import com.example.network.model.data.DragonResponse

class DragonAdapter(

    private val onFavoriteClick: (DragonResponse) -> Unit,
    private val isFavorite: (String) -> Boolean

) : ListAdapter<DragonResponse, DragonAdapter.DragonViewHolder>(DragonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonViewHolder {
        val binding = DragonRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonViewHolder(binding, onFavoriteClick, isFavorite)
    }

    override fun onBindViewHolder(holder: DragonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.binding.favoriteIcon.setOnClickListener {
            onFavoriteClick(item)
            notifyItemChanged(position)
        }
    }

    class DragonViewHolder(
        val binding: DragonRecViewBinding,
        private val onFavoriteClick: (DragonResponse) -> Unit,
        private val isFavorite: (String) -> Boolean)
    : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DragonResponse) {
            binding.apply{
                Name.text = item.name
                type.text =item.type
                summary.text = item.description
                capacity.text = item.crewCapacity.toString()
                firstFlight.text = item.firstFlight.toString()

                Glide.with(ImageLogo.context)
                    .load(item.flickrImages?.first())
                    .placeholder(R.drawable.baseline_rocket_24)
                    .into(ImageLogo)

                updateFavoriteIcon(isFavorite(item.id))

                favoriteIcon.setOnClickListener {
                    onFavoriteClick(item)
                    updateFavoriteIcon(!isFavorite(item.id)) /// TODO: Viewmodel
                }

            }
        }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
            val color = if (isFavorite) com.example.resources.R.color.blue else com.example.resources.R.color.white
            binding.favoriteIcon.setColorFilter(ContextCompat.getColor(binding.root.context, color))
        }
    }

    class DragonDiffCallback : DiffUtil.ItemCallback<DragonResponse>() {
        override fun areItemsTheSame(oldItem:DragonResponse, newItem: DragonResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DragonResponse, newItem: DragonResponse): Boolean {
            return oldItem == newItem
        }
    }
}
