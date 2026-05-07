package com.example.features.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.model.History
import com.example.features.model.Launch
import com.example.features.model.toModel
import com.example.features.model.toUiModel
import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import com.example.network.model.repository.CompanyRepository
import com.example.network.model.repository.HistoryRepository
import com.example.network.model.repository.LaunchesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class ExploreViewModel(
    private val historyRepository: HistoryRepository,
    private val companyRepository: CompanyRepository,
    private val launchesRepository: LaunchesRepository
) : ViewModel() {

    private val _uiState : MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState= _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update {
            it.copy(
                error = throwable.message ?: "Something went wrong"
            )
        }
    }

    fun getData(){
        setLoading()
        viewModelScope.launch (handler){
            val launchesDeferred = async { fetchLaunches() }
            val companyDeferred = async { fetchCompanyInfo() }
            val historyDeferred = async { fetchHistory() }

            val launches = launchesDeferred.await()
            val companyInfo = companyDeferred.await()
            val history = historyDeferred.await()

            _uiState.update {
                it.copy(
                    launches = launches.map { it.toModel() },
                    companyResponse = companyInfo,
                    history = history.map { it.toUiModel() },

                    rawLaunches = launches,
                    rawCompany = companyInfo,
                    rawHistory = history,
                    isLoading = false
                )
            }
        }
    }

    private fun setLoading(){
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
    }

    private suspend fun fetchLaunches():List<LaunchesResponse> {
           return launchesRepository.getLaunchesInfo()
    }

    private suspend fun fetchCompanyInfo() :CompanyResponse{
         return companyRepository.getCompanyInfo()
    }

    private suspend fun fetchHistory():List<HistoryResponseItem> {
           return historyRepository.getHistoryInfo()

    }
}
data class ExploreUiState(
    val companyResponse: CompanyResponse? = null,
    val history : List<History>? = emptyList(),
    val launches: List<Launch> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val rawCompany: CompanyResponse? = null,
    val rawHistory: List<HistoryResponseItem> = emptyList(),
    val rawLaunches: List<LaunchesResponse> = emptyList(),
)