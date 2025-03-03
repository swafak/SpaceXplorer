package com.example.features.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.features.R
import com.example.features.databinding.LaunchesRecViewBinding
import com.example.network.model.data.LaunchesResponse

class LaunchesAdapter (
    private val onClick : (LaunchesResponse) -> Unit
) : ListAdapter<LaunchesResponse, LaunchesAdapter.LaunchesViewHolder>(LaunchesViewHolder.LaunchesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val binding =
            LaunchesRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick(item)
        }

    }

    class LaunchesViewHolder(val binding: LaunchesRecViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LaunchesResponse) {
            binding.apply {
                Name.text = item.name
                date.text = item.dateUtc
//
//
//                Glide.with(ImageLogo.context)
//                    .load(item.links?.flickr?.original)
//                    .into(ImageLogo)
//            }
                val imageUrl = item.links?.patch?.small

                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(ImageLogo.context)
                        .load(imageUrl)
                        .into(ImageLogo)
                } else {
                    Glide.with(ImageLogo.context)
                        .load(R.drawable.baseline_rocket_24) // Replace with your placeholder drawable
                        .into(ImageLogo)
                }

            }
        }

        class LaunchesDiffCallback : DiffUtil.ItemCallback<LaunchesResponse>() {
            override fun areItemsTheSame(
                oldItem: LaunchesResponse,
                newItem: LaunchesResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LaunchesResponse,
                newItem: LaunchesResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
