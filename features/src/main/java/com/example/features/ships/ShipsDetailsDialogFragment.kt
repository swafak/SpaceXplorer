package com.example.features.ships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.common.BottomDialogFragment
import com.example.data.room.FavoriteDB
import com.example.data.room.ShipsEntity
import com.example.features.R
import com.example.features.databinding.FragmentShipsDetailsDialogBinding
import com.example.features.favorites.FavoritesViewModel
import com.example.features.favorites.ship.ShipsFavoriteViewModel
import com.example.features.model.Ship
import com.example.features.model.toRocketResponse
import com.example.features.model.toShipsResponse
import com.example.network.model.data.ShipsResponseItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipsDetailsDialogFragment(
    private val ship: Ship
) :
    BottomDialogFragment(R.layout.fragment_ships_details_dialog) {

    private lateinit var binding: FragmentShipsDetailsDialogBinding
    private val viewModel: ShipsFavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        viewModel.isFavShip(ship.id)
        setUpObserver()

        binding.favoriteIcon.setOnClickListener {
            viewModel.toggleFavoriteShip(ship.toShipsResponse())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShipsDetailsDialogBinding.inflate(inflater, container, false)

        return binding.root

    }

    private fun setUpData() {
        binding.apply {
            "${getString(com.example.resources.R.string.status)} ${ship.active.toString()}".also { active.text = it }
            "${getString(com.example.resources.R.string.Mass)} ${ship.massKg.toString()}".also { mass.text = it }
            "${getString(com.example.resources.R.string.homePort)} ${ship.homePort.toString()}".also { homePort.text = it }
            "${getString(com.example.resources.R.string.year)}${ship.yearBuilt.toString()}".also { year.text = it }
            "${getString(com.example.resources.R.string.model)}${ship.model}".also { model.text = it }
            Launches.text = ship.launches.toString()

            Glide.with(Image.context)
                .load(ship.image)
                .placeholder(R.drawable.baseline_rocket_24)
                .into(Image)

        }
    }

    private fun setUpObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { response ->
                    updateFavoriteIcon(response.isFavoriteState)
                }
            }
        }
    }
    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val color = if (isFavorite) com.example.resources.R.color.blue else com.example.resources.R.color.white
        binding.favoriteIcon.setColorFilter(ContextCompat.getColor(requireContext(), color))
    }

}