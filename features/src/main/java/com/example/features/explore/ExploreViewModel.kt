package com.example.features.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import com.example.network.model.repository.CompanyRepository
import com.example.network.model.repository.HistoryRepository
import com.example.network.model.repository.LaunchesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExploreViewModel(
    private val historyRepository: HistoryRepository,
    private val companyRepository: CompanyRepository,
    private val launchesRepository: LaunchesRepository
) : ViewModel() {
    private val _history = MutableStateFlow<List<HistoryResponseItem>>(emptyList())
    val history: StateFlow<List<HistoryResponseItem>> = _history.asStateFlow()

    private val _companyInfo = MutableStateFlow<CompanyResponse?>(null)
    val companyInfo: StateFlow<CompanyResponse?> = _companyInfo.asStateFlow()

    private val _launches = MutableStateFlow<List<LaunchesResponse>>(emptyList())
    val launches= _launches.asStateFlow()

    private val _uiState : MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState= _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update {
            it.copy(
                error = throwable.message ?: "Something went wrong"
            )
        }
    }

    fun fetchLaunches() {
        viewModelScope.launch(handler) {
           val response = withContext(Dispatchers.IO) {launchesRepository.getLaunchesInfo()}
            _uiState.update {
                it.copy(
                    launches = response
                )
            }
        }
    }

    fun fetchCompanyInfo() {
        viewModelScope.launch(handler) {
          val response = companyRepository.getCompanyInfo()
          _uiState.update {
             it.copy(
                 companyResponse = response
             )
          }
        }
    }

    fun fetchHistory() {
        viewModelScope.launch(handler) {
           val response = withContext(Dispatchers.IO) {
               historyRepository.getHistoryInfo()
           }
          _uiState.update {
            it.copy(
                history = response
            )
           }

        }

    }
}
data class ExploreUiState(
    val companyResponse: CompanyResponse? = null,
    val history : List<HistoryResponseItem>? = emptyList(),
    val launches : List<LaunchesResponse> = emptyList(),
    val isLoading :  Boolean = false,
    val error: String? = null
)