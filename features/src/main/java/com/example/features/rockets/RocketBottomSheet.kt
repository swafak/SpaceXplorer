package com.example.features.rockets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.features.favorites.rocket.FavoriteRocketViewModel
import com.example.features.favorites.ship.ShipsFavoriteViewModel
import com.example.features.model.Rocket
import com.example.features.model.toRocketResponse
import com.example.features.model.toShipsResponse
import com.example.features.ships.ShipDetailContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketBottomSheet(
    rocket: Rocket,
    onDismiss: () -> Unit
    ) {
        val viewModel: FavoriteRocketViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(rocket.id) {
            viewModel.isFavRocket(rocket.id)
        }

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            RocketDetails(
                rocket = rocket,
                isFavorite = uiState.isFavoriteState,
                onFavoriteClick = {
                    viewModel.toggleFavoriteRocket(rocket.toRocketResponse())
                }
            )
        }
    }