package com.example.network.model.api

import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.DragonResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import com.example.network.model.data.RocketsResponse
import com.example.network.model.data.ShipsResponseItem
import retrofit2.http.GET

interface SpaceXplorerAPI  {

    @GET("v4/company")
    suspend fun getCompany(): CompanyResponse

    @GET("v4/history")
    suspend fun getHistory(): List<HistoryResponseItem>

    @GET("v5/launches")
    suspend fun getLaunches(): List<LaunchesResponse>

    @GET("v4/rockets")
    suspend fun getRockets(): List<RocketsResponse>

    @GET("v4/dragons")
    suspend fun getDragons(): List<DragonResponse>

    @GET("v4/ships")
    suspend fun getShips(): List<ShipsResponseItem>
}