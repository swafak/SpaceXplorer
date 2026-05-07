package com.example.features.launches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.features.model.Launch

@Composable
fun LaunchesScreen(launches: List<Launch>) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredLaunches = remember(searchQuery, launches) {
        if (searchQuery.isEmpty()) launches
        else launches.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

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
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
//            items(launches) { launch ->
//                LaunchCard(launch = launch)
//            }
            items(filteredLaunches) { launch ->
                LaunchCard(launch = launch)
            }
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