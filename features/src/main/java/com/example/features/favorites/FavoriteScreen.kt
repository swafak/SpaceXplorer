package com.example.features.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.data.room.DragonEntity
import com.example.data.room.RocketEntity
import com.example.data.room.ShipsEntity
import com.example.features.favorites.dragon.FavDragonScreen
import com.example.features.favorites.rocket.FavRocketScreen
import com.example.features.favorites.ship.FavShipScreen
import com.example.features.model.Rocket
import com.example.features.model.Ship
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    ships: List<ShipsEntity>,
    dragons: List<DragonEntity>,
    rockets: List<RocketEntity>,
) {
    val tabs = listOf("Ships", "Dragons", "Rockets")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> FavShipScreen(
                    ship = ships,
                )
                1 -> FavDragonScreen(
                    dragon = dragons
                )
                2 -> FavRocketScreen(
                    rocket = rockets,
                )
            }
        }
    }
}