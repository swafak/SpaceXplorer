package com.example.network.model.repository

import com.example.network.model.api.SpaceXplorerAPI

class ShipsRepository(private val api: SpaceXplorerAPI) {

    suspend fun getShipsInfo() = api.getShips()
}