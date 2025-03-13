package com.example.network.model.repository

import com.example.network.model.api.SpaceXplorerAPI
import com.example.network.model.data.RocketsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketRepository(private val api: SpaceXplorerAPI) {

//   fun getRocketsInfo(): Flow<List<RocketsResponse>> = flow {
//       try {
//           val response = api.getRockets()
//           emit(response)
//       }
//        catch (e: Exception) {
//           e.printStackTrace()
//           emit(emptyList())
//       }
//
//    }

   suspend fun getRocketInfo() = api.getRockets()

}