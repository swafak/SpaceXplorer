package com.example.common

sealed class Screen(val route: String) {
    object Splash    : Screen("splash")
    object Explore  : Screen("explore")
    object Rockets  : Screen("rockets")
    object Ships    : Screen("ships")
//    object Launches : Screen("launches/{launches}") {
//        fun createRoute() = "launches"
//    }
    object Dragons  : Screen("dragons")
    object Favorites: Screen("favorites")
    object Launches  : Screen("launches")
    object History   : Screen("history")
    object Company   : Screen("company")
}
val mainScreens = setOf(
    Screen.Explore.route,
    Screen.Rockets.route,
    Screen.Ships.route,
    Screen.Dragons.route,
    Screen.Favorites.route,
    Screen.History.route,
    Screen.Company.route,
    Screen.Launches.route
)