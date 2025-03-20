package com.example.network.model.repository

import com.example.network.model.api.SpaceXplorerAPI

class CompanyRepository(private val api: SpaceXplorerAPI) {

    suspend fun getCompanyInfo()= api.getCompany()

}