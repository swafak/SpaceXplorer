package com.example.features.favorites.rocket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.data.room.RocketEntity
import com.example.features.model.Rocket
import com.example.features.model.toModel
import com.example.features.rockets.RocketCard


@Composable
fun FavRocketScreen(
    rocket: List<RocketEntity>,
    onClick: (Rocket)-> Unit
){
    Box( modifier = Modifier.fillMaxSize()){
        Column {
            LazyColumn {
                items(rocket){
                    rocket->
                    RocketCard(
                        rocket = rocket.toModel(),
                        onClick = {onClick(rocket.toModel())}
                    )

                }
            }
        }
    }

}