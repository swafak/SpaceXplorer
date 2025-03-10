package com.example.network.model.repository
import com.example.network.model.api.SpaceXplorerAPI

import com.example.network.model.data.HistoryResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepository(private val api: SpaceXplorerAPI) {

//    fun getHistoryInfo(): Flow<List<HistoryResponseItem>> = flow {
//        try {
//            val response = api.getHistory()
//            emit(response)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(emptyList())
//        }
//    }

    suspend fun getHistoryInfo() = api.getHistory()
}