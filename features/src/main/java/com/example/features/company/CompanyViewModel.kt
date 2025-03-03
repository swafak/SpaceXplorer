package com.example.features.company

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.CompanyResponse
import com.example.network.model.repository.CompanyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CompanyViewModel( private val repository: CompanyRepository) : ViewModel() {
//
//    private val _companyInfo = MutableStateFlow<CompanyResponse?>(null)
//    val companyInfo: StateFlow<CompanyResponse?> = _companyInfo.asStateFlow()
//
//
//    fun fetchCompanyInfo() {
//        viewModelScope.launch {
//            repository.getCompanyInfo().collectLatest { response ->
//                _companyInfo.value = response
//            }
//        }
//         }

}
