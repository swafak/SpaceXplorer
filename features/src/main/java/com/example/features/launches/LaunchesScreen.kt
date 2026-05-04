package com.example.features.launches

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.features.model.Launch

@Composable
fun LaunchesScreen(launches: List<Launch>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(launches) { launch ->
            LaunchCard(launch = launch)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchCardPreview() {
    MaterialTheme {
        LaunchCard(
            launch = Launch(
                id = "1",
                name = "Falcon Heavy",
                dateUtc = "Feb 6, 2018",
                imageUrl = null
            )
        )
    }
}