package com.example.features.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.airbnb.lottie.compose.*
import com.example.features.model.Launch
import com.example.features.R
import com.example.features.model.History


@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onLaunchesArrowClick: () -> Unit,
    onHistoryArrowClick: () -> Unit,
    onCompanyArrowClick: () -> Unit,
    onLaunchClick: (Launch) -> Unit

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .let { if (uiState.isLoading) it.then(Modifier) else it }
        ) {
            if (!uiState.isLoading) {
                SectionHeader(
                    title = "About SpaceX",
                    showArrow = uiState.companyResponse != null,
                    onArrowClick = onCompanyArrowClick
                )

                Image(
                    painter = painterResource(R.drawable.rocket_launch),
                    contentDescription = "SpaceX rocket",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Black),
                    contentScale = ContentScale.Fit
                )
                uiState.companyResponse?.let { company ->
                    CompanyLinks(
                        website = company.links.website,
                        twitter = company.links.twitter
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SectionHeader(
                    title = "Launches",
                    showArrow = uiState.launches.isNotEmpty(),
                    onArrowClick = onLaunchesArrowClick
                )
                LaunchesPreviewRow(
                    launches = uiState.launches,
                    onLaunchClick = onLaunchClick
                )

                Spacer(modifier = Modifier.height(8.dp))

                SectionHeader(
                    title = "History",
                    showArrow = uiState.history != null,
                    onArrowClick = onHistoryArrowClick
                )
                uiState.history?.let { HistoryRow(history = it) }


                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        if (uiState.isLoading) {
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
        }

        uiState.error?.let {
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    showArrow: Boolean,
    onArrowClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (showArrow) {
            IconButton(onClick = onArrowClick) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "See all $title"
                )
            }
        }
    }
}

@Composable
fun CompanyLinks(website: String, twitter: String) {
    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            Text("Website : ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(website, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, fontSize = 20.sp)
        }
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            Text("Twitter : ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(twitter, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, fontSize = 20.sp)
        }
    }
}

@Composable
fun LaunchesPreviewRow(launches: List<Launch>, onLaunchClick: (Launch) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(launches) { launch ->
            LaunchPreviewCard(launch = launch, onClick = { onLaunchClick(launch) })
        }
    }
}

@Composable
fun LaunchPreviewCard(launch: Launch, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        modifier = Modifier
            .width(140.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(launch.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = launch.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(com.example.resources.R.drawable.baseline_rocket_24),
                error = painterResource(
                    com.example.resources.R.drawable.baseline_rocket_24),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = launch.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun HistoryRow(history: List<History>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(history) { item ->
            HistoryCard(item = item)
        }
    }
}

@Composable
fun HistoryCard(item: History) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        modifier = Modifier.width(240.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White

            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.details,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.articleUrl,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,

            )
        }
    }
}