package com.example.features.favorites.rocket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.data.room.RocketEntity
import com.example.features.model.Rocket
import com.example.features.model.toModel
import com.example.features.rockets.RocketBottomSheet
import com.example.features.rockets.RocketCard


@Composable
fun FavRocketScreen(
    rocket: List<RocketEntity>,
) {
    var selectedRocket by remember { mutableStateOf<Rocket?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn {
                items(rocket) { rocket ->
                    RocketCard(
                        rocket = rocket.toModel(),
                        onClick = {
                            selectedRocket = rocket.toModel()
                            showSheet = true

                        })
                }
            }
        }
    }
    if (showSheet && selectedRocket != null) {
        RocketBottomSheet(
            rocket = selectedRocket!!,
            onDismiss = {
                showSheet = false
                selectedRocket = null
            }
        )
    }

}