package com.example.network.model.repository

import com.example.network.model.api.SpaceXplorerAPI


class DragonRepository(private val api: SpaceXplorerAPI) {

   suspend fun getDragonInfo() = api.getDragons()

}