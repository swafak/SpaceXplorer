package com.example.features.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import com.example.network.model.repository.CompanyRepository
import com.example.network.model.repository.HistoryRepository
import com.example.network.model.repository.LaunchesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExploreViewModel(private val historyRepository: HistoryRepository,
                       private val companyRepository: CompanyRepository,
                       private val launchesRepository: LaunchesRepository) : ViewModel() {
    private val _history = MutableStateFlow<List<HistoryResponseItem>>(emptyList())
    val history: StateFlow<List<HistoryResponseItem>> = _history.asStateFlow()

    private val _companyInfo = MutableStateFlow<CompanyResponse?>(null)
    val companyInfo: StateFlow<CompanyResponse?> = _companyInfo.asStateFlow()

    private val _launches= MutableStateFlow<List<LaunchesResponse?>?>(emptyList())
    val launches: StateFlow<List<LaunchesResponse?>?> = _launches.asStateFlow()

    fun fetchLaunches(){

        viewModelScope.launch {
            launchesRepository.getLaunchesInfo().collectLatest {
                launchResponse ->
                _launches.value = launchResponse
            }
        }
    }

    fun fetchCompanyInfo() {
        viewModelScope.launch {
            companyRepository.getCompanyInfo().collectLatest { response ->
                _companyInfo.value = response
            }
        }
    }
    fun fetchHistory() {
        viewModelScope.launch {
            historyRepository.getHistoryInfo().collectLatest { response ->
                _history.value = response
            }
        }
    }


}