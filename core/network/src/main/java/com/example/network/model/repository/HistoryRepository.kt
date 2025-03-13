package com.example.network.model.repository
import com.example.network.model.api.SpaceXplorerAPI

class HistoryRepository(private val api: SpaceXplorerAPI) {

    suspend fun getHistoryInfo() = api.getHistory()
}