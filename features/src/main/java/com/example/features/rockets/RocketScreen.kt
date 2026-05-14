package com.example.features.rockets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.features.model.Rocket
@Composable
fun RocketsScreen(
    rockets: List<Rocket>,
    isLoading: Boolean,
) {
    var selectedRocket by remember { mutableStateOf<Rocket?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filtered = remember(searchQuery, rockets) {
        if (searchQuery.isEmpty()) rockets
        else rockets.filter {
            it.name.orEmpty().contains(searchQuery, ignoreCase = true)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {

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
                    placeholder = { Text("Search missions...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    items(filtered) { it ->
                        RocketCard(
                            rocket = it,
                            onClick = {
                                selectedRocket = it
                                showSheet = true

                            })
                    }
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