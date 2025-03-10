package com.example.network.model.repository

import com.example.network.model.api.SpaceXplorerAPI
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LaunchesRepository(private val api: SpaceXplorerAPI) {

//        fun getLaunchesInfo(): Flow<List<LaunchesResponse>> = flow {
//            try {
//                val response = api.getLaunches()
//                emit(response)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                emit(emptyList())
//            }
//        }

    suspend fun getLaunchesInfo() = api.getLaunches()

}