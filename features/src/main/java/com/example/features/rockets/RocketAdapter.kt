package com.example.features.rockets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.features.R
import com.example.features.databinding.RocketRecViewBinding
import com.example.network.model.data.RocketsResponse


class RocketAdapter(
    private val onClick : (RocketsResponse) -> Unit
) : ListAdapter<RocketsResponse, RocketAdapter.RocketViewHolder>(RocketDiffCallback()) {

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

        fun bind(item: RocketsResponse) {
            binding.apply {
                Name.text = item.name
//                date.text = item.active.toString()

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

    class RocketDiffCallback : DiffUtil.ItemCallback<RocketsResponse>() {
        override fun areItemsTheSame(oldItem: RocketsResponse, newItem: RocketsResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RocketsResponse,
            newItem: RocketsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}
