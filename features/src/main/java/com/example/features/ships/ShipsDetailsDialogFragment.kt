package com.example.features.ships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
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
    private val viewModel: ShipsFavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isFavShip(ship.id)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            return ComposeView(requireContext()).apply {
                setContent {
                    MaterialTheme {
                        val uiState by viewModel.uiState.collectAsState()

                        ShipDetailContent(
                            ship = ship,
                            isFavorite = uiState.isFavoriteState,
                            onFavoriteClick = {
                                viewModel.toggleFavoriteShip(ship.toShipsResponse())
                            }
                        )
                    }
                }
            }
        }
    }