package com.example.features.dragons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.features.R
import com.example.features.model.Dragon

@Composable
fun DragonCard(
    onFavoriteClick:() -> Unit,
    isFavorite: Boolean,
    dragon: Dragon,
){
    Card(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.Black)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Black)
                    .align(Alignment.CenterHorizontally)
            ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dragon.flickrImages.firstOrNull())
                    .crossfade(true)
                    .build(),
                contentDescription = "Mission patch for ${dragon.name}",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.baseline_rocket_24),
                error = painterResource(R.drawable.baseline_rocket_24)
            )
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite)
                        Icons.Filled.Star
                    else
                        Icons.Outlined.Star,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Blue else Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            }
            Text(
                text = dragon.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = dragon.description,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = "First Flight: ",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )

                Text(
                    text = dragon.firstFlight,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "Type: ",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )

                Text(
                    text = dragon.type,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = "Crew Capacity: ",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )

                Text(
                    text = dragon.crewCapacity?.toString().orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }

        }

    }
}