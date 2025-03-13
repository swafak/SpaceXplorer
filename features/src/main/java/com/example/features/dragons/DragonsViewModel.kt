package com.example.features.dragons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.data.DragonResponse
import com.example.network.model.repository.DragonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DragonsViewModel(private val repository: DragonRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(dragonUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchDragon() {
        viewModelScope.launch() {
            val response =  repository.getDragonInfo()
            if(response.isEmpty()){
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }else{
            _uiState.update {
                it.copy(
                    dragonInfo = response, isLoading = false
                )
            }}
        }
    }
}

data class dragonUiState(
    val dragonInfo: List<DragonResponse>? = emptyList(),
    val isLoading: Boolean = true
)