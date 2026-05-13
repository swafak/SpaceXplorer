package com.example.features.favorites.ship

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.room.ShipsEntity
import com.example.features.model.Ship
import com.example.features.model.toModel
import com.example.features.ships.ShipsCard



@Composable
fun FavShipScreen(
    ship : List<ShipsEntity>,
    onClick: (Ship) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    val filtered = remember(searchQuery, ship) {
        if (searchQuery.isEmpty()) ship
        else ship.filter {
            it.name.orEmpty().contains(searchQuery, ignoreCase = true)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Search Ships...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filtered) { ship ->
                    ShipsCard(
                        ship = ship.toModel(),
                        onClick = { onClick(ship.toModel()) })
                }
            }
        }

    }
}