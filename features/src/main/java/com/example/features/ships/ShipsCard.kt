package com.example.features.ships

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.features.R
import com.example.features.model.Ship
import org.jetbrains.annotations.NotNull

@Composable
fun ShipsCard(ship: Ship, onClick: (() -> Unit)? = null ) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(color = Color.Black)
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ship.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Mission patch for ${ship.name}",
                modifier = Modifier
                    .size(150.dp)
                    .background(color = Color.White),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(
                    com.example.resources.R.drawable.baseline_rocket_24),
                error = painterResource(com.example.resources.R.drawable.baseline_rocket_24)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            Text(
                text = ship.name.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = ship.type.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun cardPreview() {
    MaterialTheme {
        ShipsCard(
            ship = Ship(
                active = true,
                homePort = "homeport",
                id = "id",
                image = "com.example.resources.R.drawable.baseline_rocket_24",
                launches = null,
                massKg = null,
                model = null,
                name = "test",
                status = null,
                yearBuilt = null,
                type = null
            ),
            onClick = null
        )
    }
}