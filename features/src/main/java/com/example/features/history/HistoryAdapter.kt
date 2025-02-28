//package com.example.features.history
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//
//class HistoryAdapter(
//) : ListAdapter<CharacterResult, CharacterAdapter.CharacterViewHolder>(DiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
//        val binding = CharactersListRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CharacterViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
//        val castcharacter = getItem(position)
//        holder.bind(castcharacter)
//        holder.binding.ImageLogo.setOnClickListener {
//            showDetails(castcharacter)
//        }
//    }
//
//
//
//    inner class CharacterViewHolder(val binding: CharactersListRecViewBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(castcharacter: CharacterResult) {
//            binding.apply {
//                Name.text = castcharacter.name
//                CharStatus.text = castcharacter.status
//
//                Glide.with(ImageLogo.context)
//                    .load(castcharacter.image)
//                    .into(ImageLogo)
//
//                root.setOnClickListener {
//                    showDetails(castcharacter)
//                }
//            }
//        }
//    }
//
//    class DiffCallback : DiffUtil.ItemCallback<CharacterResult>() {
//        override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
//            return oldItem == newItem
//        }
//    }
//}
