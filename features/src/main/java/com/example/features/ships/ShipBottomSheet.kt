package com.example.features.ships

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.features.favorites.ship.ShipsFavoriteViewModel
import com.example.features.model.Ship
import com.example.features.model.toShipsResponse
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipBottomSheet(
    ship: Ship,
    onDismiss: () -> Unit
) {
    val viewModel: ShipsFavoriteViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(ship.id) {
        viewModel.isFavShip(ship.id)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        ShipDetailContent(
            ship = ship,
            isFavorite = uiState.isFavoriteState,
            onFavoriteClick = {
                viewModel.toggleFavoriteShip(ship.toShipsResponse())
            }
        )
    }
}