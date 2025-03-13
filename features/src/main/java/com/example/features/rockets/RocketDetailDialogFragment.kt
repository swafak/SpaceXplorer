package com.example.features.rockets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import coil.load
import com.bumptech.glide.Glide
import com.example.common.BottomDialogFragment
import com.example.features.R
import com.example.features.databinding.FragmentRocketDetailDialogBinding
import com.example.network.model.data.RocketsResponse
import kotlinx.coroutines.launch

class RocketDetailDialogFragment(private val response: RocketsResponse) : BottomDialogFragment(
    R.layout.fragment_rocket_detail_dialog
) {

    private lateinit var binding: FragmentRocketDetailDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpdata()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketDetailDialogBinding.inflate(inflater, container, false)

        return binding.root
    }
    private fun setUpdata(){
        binding.apply {
            description.text = response.description
            website.text = response.wikipedia
            height.text = response.height?.meters.toString()
            mass.text = response.mass?.kg.toString()
            firstFlight.text = response.firstFlight
            successRatePct.text = response.successRatePct.toString()
            active.text = response.active.toString()

            Log.d("IMAGE", "SEE: ${response.flickrImages?.first()}")
//
            viewLifecycleOwner.lifecycleScope.launch{
                Glide.with(dialog?.context ?: requireContext())
                    .load(response.flickrImages?.first())
                    .fitCenter()
                    .placeholder(R.drawable.baseline_rocket_24)
//                .dontAnimate()
//                .error(R.drawable.baseline_rocket_24)
                    .into(Image)

            }

//            Image.load(response.flickrImages?.first()) {
//                crossfade(true)
//                placeholder(R.drawable.baseline_rocket_24)
//                error(R.drawable.baseline_rocket_24)
//            }

        }

    }

}