package com.example.common
data class BottomNavItem(
    val label: String,
    val iconRes: Int,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Dragons",   com.example.resources.R.drawable.baseline_rocket_24, Screen.Dragons.route),
    BottomNavItem("Rockets",  com.example.resources.R.drawable.baseline_rocket_24, Screen.Rockets.route),
    BottomNavItem("Explore",  com.example.resources.R.drawable.launches, Screen.Explore.route),
    BottomNavItem("Ships",    com.example.resources.R.drawable.ships, Screen.Ships.route),
    BottomNavItem("Favorites", com.example.resources.R.drawable.baseline_rocket_24,      Screen.Favorites.route),
)