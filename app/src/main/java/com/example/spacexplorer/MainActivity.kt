package com.example.spacexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.common.Screen
import com.example.common.bottomNavItems
import com.example.common.mainScreens
import com.example.features.company.CompanyScreen
import com.example.features.dragons.DragonScreen
import com.example.features.dragons.DragonsViewModel
import com.example.features.explore.ExploreScreen
import com.example.features.explore.ExploreViewModel
import com.example.features.favorites.FavoriteScreen
import com.example.features.favorites.dragon.FavoriteDragonViewModel
import com.example.features.favorites.rocket.FavoriteRocketViewModel
import com.example.features.favorites.ship.ShipsFavoriteViewModel
import com.example.features.history.HistoryScreen
import com.example.features.launches.LaunchesScreen
import com.example.features.model.CompanyModel
import com.example.features.model.History
import com.example.features.model.Launch
import com.example.features.model.toShips
import com.example.features.rockets.RocketViewModel
import com.example.features.rockets.RocketsScreen
import com.example.features.ships.ShipsViewModel
import com.example.features.ships.shipScreen
import com.example.spacexplorer.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SpaceXplorerApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceXplorerApp() {
    val navController = rememberNavController()
    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route

    val showBars = currentRoute in mainScreens

    Scaffold(
        topBar = {
            if (showBars) {
                val title = bottomNavItems
                    .find { it.route == currentRoute }?.label
                    ?: "SpaceXplorer"
                TopAppBar(
                    title = { Text(title, color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black
                    )
                )
            }
        },
        bottomBar = {
            if (showBars) {
                NavigationBar(containerColor = Color.Black) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    val startRoute = navController.graph.startDestinationRoute ?: Screen.Explore.route
                                    popUpTo(startRoute) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.iconRes),                                    contentDescription = item.label,
                                    tint = Color.White
                                )
                            },
                            label = { Text(item.label, color = Color.White) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.White.copy(alpha = 0.2f)
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        navController.navigate(Screen.Explore.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Explore.route) {
                val viewModel: ExploreViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(Unit) { viewModel.getData() }
                ExploreScreen(
                    uiState = uiState,
                    onLaunchesArrowClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "launches_data",
                            value = uiState.launches.toTypedArray()
                        )
                        navController.navigate(Screen.Launches.route)
                    },
                    onHistoryArrowClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "history_data",
                            value = uiState.history?.toTypedArray()
                        )
                        navController.navigate(Screen.History.route)},
                    onCompanyArrowClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "company_data",
                            value = uiState.companyModel
                        )
                        navController.navigate(Screen.Company.route)},
                    onLaunchClick = { }
                )
            }
            composable(Screen.History.route) {
                val history = navController.previousBackStackEntry
                    ?.savedStateHandle
                ?.get<Array<History>>("history_data")

                HistoryScreen(history?.toList() ?: emptyList())
            }

            composable(Screen.Company.route) {

                val company = navController.previousBackStackEntry?.savedStateHandle?.get<CompanyModel?>(
                    "company_data"
                )
                company?.let {
                    CompanyScreen(company = it)
                } ?: run {
                    Text("Company data not available")
                }
            }
            composable(Screen.Launches.route) {
                val launches = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Array<Launch>>("launches_data")
                LaunchesScreen(launches?.toList() ?: emptyList())
            }

            composable(Screen.Rockets.route) {
                val viewModel: RocketViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(Unit) { viewModel.fetchRockets() }

                RocketsScreen(
                    rockets = uiState.rockets,
                    isLoading = uiState.isLoading,
                )
            }

            composable(Screen.Ships.route) {
                val viewModel: ShipsViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(Unit) { viewModel.fetchShips() }

                shipScreen(
                    ship = uiState.ships.toShips(),
                    isLoading = uiState.isLoading,
                )
            }
            composable(Screen.Dragons.route) {
                val viewModel: DragonsViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {viewModel.fetchDragon() }
                DragonScreen(
                    dragon = uiState.dragon,
                    isLoading = uiState.isLoading,
                )
            }
            composable(Screen.Favorites.route) {

                val rocketviewModel : FavoriteRocketViewModel = koinViewModel()
                val shipviewModel : ShipsFavoriteViewModel = koinViewModel()
                val dragonviewModel : FavoriteDragonViewModel = koinViewModel()

                val rocUistate by rocketviewModel.uiState.collectAsState()
                val dragonUistate by dragonviewModel.uiState.collectAsState()
                val shipUistate by shipviewModel.uiState.collectAsState()

                LaunchedEffect(Unit) { rocketviewModel.getFavRocket() }

                LaunchedEffect(Unit) { dragonviewModel.getFavDragon()}

                LaunchedEffect(Unit) { shipviewModel.getFavShip()}

                FavoriteScreen(
                    ships = shipUistate.favoriteShip,
                    dragons = dragonUistate.favoriteDragon,
                    rockets = rocUistate.favoriteRocket,
                )
            }
        }
    }
}