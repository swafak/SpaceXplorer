package com.example.features.ships

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.features.model.Ship

@Composable
fun shipScreen(
    isLoading: Boolean,
    ship: List<Ship>
) {
    var selectedShip by remember { mutableStateOf<Ship?>(null) }
    var showSheet by remember { mutableStateOf(false) }

    var searchQuery by remember { mutableStateOf("") }

    val filtered = remember(searchQuery, ship) {
        if (searchQuery.isEmpty()) ship
        else ship.filter {
            it.name.orEmpty().contains(searchQuery, ignoreCase = true)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(com.example.resources.R.raw.loader)
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        } else {
            Column {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search Ships...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    items(filtered) { it ->
                        ShipsCard(
                            ship = it,
                            onClick = {
                                selectedShip = it
                                showSheet = true
                            }
                        )
                    }
                }

            }
        }

    }
    if (showSheet && selectedShip != null) {
        ShipBottomSheet(
            ship = selectedShip!!,
            onDismiss = {
                showSheet = false
                selectedShip = null
            }
        )
    }
}
