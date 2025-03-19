package com.example.features.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.common.BottomDialogFragment
import com.example.features.R
import com.example.features.databinding.FragmentLaunchesDetailDialogBinding
import com.example.network.model.data.LaunchesResponse

class LaunchesDetailDialogFragment(private val response: LaunchesResponse) : BottomDialogFragment(R.layout.fragment_launches_detail_dialog) {

    private lateinit var binding: FragmentLaunchesDetailDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchesDetailDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUpData(){
        binding.apply {
            description.text = response.details
            website.text = response.links?.wikipedia
            Glide.with(Image.context)
                .load(response.links?.patch?.small)
                .placeholder(R.drawable.baseline_rocket_24)
                .into(Image)

        }
    }


}