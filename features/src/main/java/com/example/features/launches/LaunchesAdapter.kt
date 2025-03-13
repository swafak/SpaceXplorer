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
import com.example.network.model.data.RocketsResponse

class LaunchesAdapter(
    private val onClick: (LaunchesResponse) -> Unit
) : ListAdapter<LaunchesResponse, LaunchesAdapter.LaunchesViewHolder>(LaunchesViewHolder.LaunchesDiffCallback()) {

    private var originalList : List<LaunchesResponse> = emptyList()
    fun submitFullList(list: List<LaunchesResponse>) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val binding =
            LaunchesRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchesViewHolder(binding,onClick)
    }


    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        /*holder.itemView.setOnClickListener {
            onClick(item)
        }*/

    }

    class LaunchesViewHolder(val binding: LaunchesRecViewBinding,private val onClick: (LaunchesResponse) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LaunchesResponse) {
            binding.apply {
                Name.text = item.name
                date.text = item.dateUtc

//                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(ImageLogo.context)
                        .load(item.links?.patch?.small)
                        .placeholder(R.drawable.baseline_rocket_24)
                        .into(ImageLogo)
                ImageLogo.setOnClickListener {
                    onClick(item)
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
