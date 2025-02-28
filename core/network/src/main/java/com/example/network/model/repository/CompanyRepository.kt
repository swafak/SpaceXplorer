package com.example.network.model.repository

import android.util.Log
import com.example.network.model.api.SpaceXplorerAPI
import com.example.network.model.data.CompanyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CompanyRepository(private val api: SpaceXplorerAPI) {

    fun getCompanyInfo(): Flow<CompanyResponse> = flow {
        try {
            val response = api.getCompany()
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}