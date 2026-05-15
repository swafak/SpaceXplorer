package com.example.features.dragons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.features.favorites.dragon.FavoriteDragonViewModel
import com.example.features.model.Dragon
import com.example.resources.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun DragonScreen(
    dragon: List<Dragon>,
    isLoading: Boolean,
    favoritesViewModel: FavoriteDragonViewModel = koinViewModel()
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.loader)
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(dragon) { dragon ->

                        val isFavorite by favoritesViewModel
                            .issFavDragon(dragon.id)
                            .collectAsState(initial = false)
                        DragonCard(
                            dragon = dragon,
                            isFavorite = isFavorite,
                            onFavoriteClick = {
                                favoritesViewModel.toggleFavorite(
                                    dragon
                                )
                            }
                        )

                    }
                }
            }

        }

    }

}