package com.example.network.model.api

import com.example.network.model.data.CompanyResponse
import retrofit2.http.GET

interface SpaceXplorerAPI  {

    @GET("v4/company")
    suspend fun getCompany(): CompanyResponse
}