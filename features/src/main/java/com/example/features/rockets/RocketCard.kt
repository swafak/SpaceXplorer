package com.example.features.rockets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.features.R
import com.example.features.model.Rocket

@Composable
fun RocketCard(rocket: Rocket, onClick: () -> Unit) {
    Card(
        shape = RectangleShape,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = rocket.name.toString(),
                color = Color.White

            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(16.dp)
                    .background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(
                    rocket.flickrImages?.getOrNull(0),
                    rocket.flickrImages?.getOrNull(1),
                    rocket.flickrImages?.getOrNull(2)
                ).forEach { imageUrl ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = rocket.name,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(4.dp),
                        contentScale = ContentScale.Crop,
                        error = painterResource(com.example.resources.R.drawable.baseline_rocket_24)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun cardPreview() {
    MaterialTheme {
        RocketCard(
            rocket = Rocket(
                id = "1",
                name = "Falcon Heavy",
                flickrImages = null,
                active = null,
                successRatePct = null,
                firstFlight = null,
                wikipedia = null,
                description = null,
                height = null,
                mass = null,
                type = null,
                stages = null,
                costPerLaunch = null,
                country = null,
                company = null,
            ),
            onClick = {

            }
        )
    }
}