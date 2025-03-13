package com.example.features.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.RocketsResponse
import com.example.network.model.repository.RocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RocketViewModel(private val repository: RocketRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RocketUiState())

    val uiState = _uiState.asStateFlow()

    fun fetchRockets() {
        viewModelScope.launch {
            val response = repository.getRocketInfo()
            if (response.isEmpty()) {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        rocketData = response,
                        isLoading = false
                    )
                }
            }
        }
    }

}

data class RocketUiState(
    val rocketData :List<RocketsResponse> =emptyList(),
    val isLoading: Boolean = false

)