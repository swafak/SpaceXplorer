package com.example.network.model.repository

import com.example.network.model.api.SpaceXplorerAPI
import com.example.network.model.data.DragonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DragonRepository(private val api: SpaceXplorerAPI) {

//    fun getDragonInfo(): Flow<List<DragonResponse>> = flow {
//        try {
//            val response =api.getDragons()
//            emit(response)
//                   }
//        catch (e: Exception){
//            e.printStackTrace()
//            emit(emptyList())
//        }
//    }

   suspend fun getDragonInfo() = api.getDragons()

}