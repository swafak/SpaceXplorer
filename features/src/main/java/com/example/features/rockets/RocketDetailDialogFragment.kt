package com.example.features.rockets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.common.BottomDialogFragment
import com.example.features.R
import com.example.features.databinding.FragmentRocketDetailDialogBinding
import com.example.features.favorites.rocket.FavoriteRocketViewModel
import com.example.features.model.Rocket
import com.example.features.model.toRocketResponse
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
class RocketDetailDialogFragment(
    val rocket: Rocket
) : BottomDialogFragment(
    R.layout.fragment_rocket_detail_dialog
) {
    private lateinit var binding: FragmentRocketDetailDialogBinding
    private val viewModel: FavoriteRocketViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpdata()
        setUpObserver()
        viewModel.isFavRocket(rocket.id)
        binding.favoriteIcon.setOnClickListener {
            viewModel.toggleFavoriteRocket(rocket.toRocketResponse())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
     private fun setUpdata() {
        binding.apply {
            description.text = rocket.description
            website.text = rocket.wikipedia
            height.text =rocket.height?.meters.toString()
            mass.text = rocket.mass?.kg.toString()
            firstFlight.text = rocket.firstFlight
            successRatePct.text = rocket.successRatePct.toString()

                Glide.with(Image)
                    .load(rocket.flickrImages?.first())
                    .fitCenter()
                    .placeholder(com.example.resources.R.drawable.baseline_rocket_24)
                    .into(Image)


        }
    }



    private fun setUpObserver()  {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect { state ->
                        updateFavoriteIcon(state.isFavoriteState)
                    }
                }
            }
        }



    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val color = if (isFavorite) com.example.resources.R.color.blue else com.example.resources.R.color.white
        binding.favoriteIcon.setColorFilter(ContextCompat.getColor(requireContext(), color))
    }

    }


